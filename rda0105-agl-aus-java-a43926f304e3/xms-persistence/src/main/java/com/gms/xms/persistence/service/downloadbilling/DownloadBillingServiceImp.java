package com.gms.xms.persistence.service.downloadbilling;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.admin.receivables.payments.PaymentDao;
import com.gms.xms.persistence.dao.downloadbilling.DownloadBillingDao;
import com.gms.xms.persistence.service.InvoiceNumberingService;
import com.gms.xms.persistence.service.customer.IManageCustomerService;
import com.gms.xms.persistence.service.customer.ManageCustomerServiceImp;
import com.gms.xms.persistence.service.utils.ShipmentBillingUtils;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.persistence.utils.ImportBillingServiceUtils;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from Jun 8, 2016 9:52:32 AM
 * <p>
 * Author huynd
 */
public class DownloadBillingServiceImp implements IDownloadBillingService {
    private static final Log logger = LogFactory.getLog(DownloadBillingServiceImp.class);

    @Override
    public void saveDownloadBilling(Map<String, String> context, SaveImportBillingVo saveImport, ShipmentVo newShipmentVo, ShipmentInvoiceVo shipmentInvoice, InvoiceVo invoice, ShipmentBillingVo billingOverPaymentVo, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        if (billingOverPaymentVo != null) {
            try {
                DownloadBillingDao downloadBillingDao = new DownloadBillingDao(sessionClient);
                // 1. Delete shipment billing
                downloadBillingDao.deleteShipmentBilling(context, billingOverPaymentVo);
                // 2. Delete shipment invoice
                downloadBillingDao.deleteShipmentInvoice(context, billingOverPaymentVo);
                // 3. Save customer payments
                CustomerPaymentDao customerPaymentDao = new CustomerPaymentDao(sessionClient);
                CustomerPaymentVo customerPaymentVo = new CustomerPaymentVo();
                Long customerCode = downloadBillingDao.selectCustomerCodeFromShipment(billingOverPaymentVo.getShipmentId());
                customerPaymentVo.setCustomerCode(customerCode);
                Double carrierCost = billingOverPaymentVo.getCarrierCost();
                Double taxAmount = billingOverPaymentVo.getTaxAmount();
                if ("-".equalsIgnoreCase(String.valueOf(taxAmount).substring(0, 1))) {
                    taxAmount = Double.valueOf(String.valueOf(taxAmount).substring(1));
                }
                BigDecimal amount = new BigDecimal(carrierCost + taxAmount);
                customerPaymentVo.setAmount(amount);
                String cheque = "Carrier Credit Note";
                customerPaymentVo.setCheque(cheque);
                customerPaymentVo.setPaymentDate(new Date());
                customerPaymentVo.setBankId(0L);
                customerPaymentDao.insert(context, customerPaymentVo);
                Long cusPaymentId = customerPaymentVo.getCusPaymentId();

                InvoiceVo invoiceVo = downloadBillingDao.selectInvoiceByAirbillNumber(billingOverPaymentVo.getAirbillNumberEdi());
                if (invoiceVo != null) {
                    String invoiceCode = invoiceVo.getInvoiceCode();
                    Long invoiceId = invoiceVo.getInvoiceId();
                    Integer invoiceStatus = invoiceVo.getStatus();

                    String creditCode = GenCodeUtils.generateCreditCode(invoiceCode, Calendar.getInstance().getTime());
                    // Check credit code
                    CreditNoteDao creditNoteDao = new CreditNoteDao(sessionClient);
                    CreditNoteVo creditNoteVo = creditNoteDao.selectByCreditCode(creditCode);
                    CreditNoteDetailDao creditNoteDetailDao = new CreditNoteDetailDao(sessionClient);
                    if (creditNoteVo != null) { // Credit note is exist
                        if (creditNoteVo.getStatus() == 1) {
                            // If credit note freeze, change status to unfreeze
                            creditNoteVo.setStatus(0);
                            creditNoteDao.updateCreditNote(context, creditNoteVo);
                        }
                        // Save credit note detail
                        CreditNoteDetailVo creditNoteDetailVo = new CreditNoteDetailVo();
                        creditNoteDetailVo.setCreditNoteId(creditNoteVo.getCreditNoteId());
                        creditNoteDetailVo.setReason("Carrier Credit Note");
                        creditNoteDetailVo.setAdjustmentid(0L);
                        creditNoteDetailVo.setCusPaymentid(cusPaymentId);
                        creditNoteDetailVo.setCreditAirbillNumber(billingOverPaymentVo.getAirbillNumberEdi());
                        creditNoteDetailVo.setAmount(carrierCost);
                        creditNoteDetailVo.setGstAmount(taxAmount);
                        creditNoteDetailDao.insert(context, creditNoteDetailVo);
                    } else { // Credit note is not exist
                        // Create new credit note
                        creditNoteVo = new CreditNoteVo();
                        creditNoteVo.setCreditCode(creditCode);
                        creditNoteVo.setInvoiceCode(invoiceCode);
                        creditNoteVo.setCreateDate(new Date());
                        creditNoteVo.setStatus(0);
                        creditNoteDao.insert(context, creditNoteVo);
                        Long creditNoteId = creditNoteVo.getCreditNoteId();
                        if (creditNoteId != 0) {
                            // Save credit note detail
                            CreditNoteDetailVo creditNoteDetailVo = new CreditNoteDetailVo();
                            creditNoteDetailVo.setCreditNoteId(creditNoteId);
                            creditNoteDetailVo.setReason("Carrier Credit Note");
                            creditNoteDetailVo.setAdjustmentid(0L);
                            creditNoteDetailVo.setCusPaymentid(cusPaymentId);
                            creditNoteDetailVo.setCreditAirbillNumber(billingOverPaymentVo.getAirbillNumberEdi());
                            creditNoteDetailVo.setAmount(carrierCost);
                            creditNoteDetailVo.setGstAmount(taxAmount);
                            creditNoteDetailDao.insert(context, creditNoteDetailVo);
                        }
                    }

                    // Save over payment
                    OverpaymentDao overpaymentDao = new OverpaymentDao(sessionClient);
                    if (cusPaymentId != 0) {
                        OverpaymentVo overPayment = new OverpaymentVo();
                        overPayment.setCusPaymentId(cusPaymentId);
                        overPayment.setOverAmount(amount);
                        overpaymentDao.insert(context, overPayment);
                    }

                    // If invoice is paid or unfreeze , only save for
                    // overpayment
                    PaymentDao paymentDao = new PaymentDao(sessionClient);
                    InvoicePaymentDao invoicePaymentDao = new InvoicePaymentDao(sessionClient);
                    Double owedAmount = paymentDao.getPaymentForCredit(invoiceCode);
                    if (owedAmount != 0 && invoiceStatus != 0) {
                        InvoiceDao invoiceDao = new InvoiceDao();
                        ReceiveInvoicelistVo receiveInvoicelistVo = invoiceDao.selectReceiveInvoicelistByInvoiceid(invoiceId);
                        if (receiveInvoicelistVo != null) {
                            BigDecimal remain = receiveInvoicelistVo.getRemain();
                            OverpaymentVo overPay = overpaymentDao.selectOverPayByCustomerPaymentId(cusPaymentId);
                            if (overPay != null) {
                                BigDecimal overAmount = overPay.getOverAmount();
                                if (remain.compareTo(overAmount) == 0) {
                                    // remain = overAmount
                                    overAmount = new BigDecimal("0");
                                } else if (remain.compareTo(overAmount) == 1) {
                                    // remain > overAmount
                                    overAmount = remain.subtract(overAmount);
                                } else if (remain.compareTo(overAmount) == -1) {
                                    // remain < overAmount
                                    overAmount = overAmount.subtract(remain);
                                }
                                if (overAmount.compareTo(new BigDecimal("0")) == 0) {
                                    overpaymentDao.delete(context, cusPaymentId);
                                } else {
                                    OverpaymentVo overpaymentUpdate = new OverpaymentVo();
                                    overpaymentUpdate.setCusPaymentId(cusPaymentId);
                                    overpaymentUpdate.setOverAmount(overAmount);
                                    overpaymentDao.update(context, overpaymentUpdate);
                                }

                                InvoicePaymentVo invoicePayment = new InvoicePaymentVo();
                                invoicePayment.setCusPaymentId(cusPaymentId);
                                if (remain.compareTo(amount) == 0 || remain.compareTo(amount) == -1) {
                                    // remain <= amount
                                    invoicePayment.setAmount(remain);
                                    saveInvoicePaymentDetail(context, invoiceId, sessionClient);
                                } else {
                                    // remain > amount
                                    invoicePayment.setAmount(amount);
                                }
                                invoicePayment.setInvoiceId(invoiceId);
                                invoicePayment.setApplyDate(new Date());
                                invoicePaymentDao.insert(context, invoicePayment);
                            }
                        }
                    }
                }
                // Commit transaction
                sessionClient.endTransaction();
            } catch (Exception e) {
                // Roll back transaction
                logger.error(e);
                sessionClient.rollback();
                throw e;
            }
        } else {
            // Insert shipment billing
            try {
                // Insert Sender & Receiver Address
                AddressDao addressDao = new AddressDao(sessionClient);
                AddressVo senderAddress = new AddressVo();
                AddressVo receiverAddress = new AddressVo();
                senderAddress = saveImport.getSenderAddress();
                receiverAddress = saveImport.getReceiverAddress();
                addressDao.insert(context, senderAddress);
                addressDao.insert(context, receiverAddress);

                // Create new invoice
                Long invoiceId = 0L;
                if (invoice != null) {
                    InvoiceDao invoiceDao = new InvoiceDao(sessionClient);
                    invoiceDao.insert(context, invoice);
                    // Update customer activation date.
                    IManageCustomerService manageCustomerService = new ManageCustomerServiceImp();
                    manageCustomerService.updateCustomerActivationDate(context, String.valueOf(invoice.getCustomerCode()), sessionClient);
                    // Update invoice numbering counter.
                    InvoiceNumberingService numberingService = new InvoiceNumberingService();
                    numberingService.updateCounter(context, invoice, sessionClient);
                    invoiceId = invoice.getInvoiceId();
                }

                // Insert shipment
                Long shipmentId = 0L;
                ShipmentVo shipmentVo = new ShipmentVo();
                if (newShipmentVo != null) {
                    ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
                    newShipmentVo.setReceiverAddressId(receiverAddress.getAddressId());
                    newShipmentVo.setSenderAddressId(senderAddress.getAddressId());
                    shipmentDao.insertShipment(context, newShipmentVo);
                    shipmentId = newShipmentVo.getShipmentId();
                } else {
                    ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
                    shipmentVo = shipmentDao.selectById(saveImport.getBillingBaseCharge().getShipmentId());
                }

                // Insert shipment invoice
                if (shipmentInvoice != null) {
                    ShipmentInvoiceDao shipmentInvoiceDao = new ShipmentInvoiceDao(sessionClient);
                    if (invoice != null) {
                        shipmentInvoice.setInvoiceId(invoiceId);
                    }
                    if (newShipmentVo != null) {
                        shipmentInvoice.setShipmentId(newShipmentVo.getShipmentId());
                    } else {
                        shipmentInvoice.setShipmentId(saveImport.getBillingBaseCharge().getShipmentId());
                    }
                    shipmentInvoiceDao.insert(context, shipmentInvoice);
                }

                Long customerCode = (newShipmentVo != null) ? newShipmentVo.getCustomerCode() : shipmentVo.getCustomerCode();
                // Insert shipment billing base charge
                ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
                ShipmentBillingVo shipmentBillingBaseCharge = saveImport.getBillingBaseCharge();
                if (shipmentId != 0) {
                    shipmentBillingBaseCharge.setShipmentId(shipmentId);
                }
                shipmentBillingBaseCharge.setSenderAddressId(senderAddress.getAddressId());
                shipmentBillingBaseCharge.setReceiverAddressId(receiverAddress.getAddressId());
                shipmentBillingDao.insertShipmentBillingSurcharge(context, shipmentBillingBaseCharge);

                // Insert shipment billing surcharge
                List<ShipmentBillingVo> billingAccessorials = saveImport.getBillingAccessorials();
                if (saveImport.getBillingAccessorials() != null && saveImport.getBillingAccessorials().size() > 0) {
                    for (ShipmentBillingVo shipmentBillingSurcharge : billingAccessorials) {
                        if (shipmentId != 0) {
                            shipmentBillingSurcharge.setShipmentId(shipmentId);
                        }
                        shipmentBillingSurcharge.setSenderAddressId(senderAddress.getAddressId());
                        shipmentBillingSurcharge.setReceiverAddressId(receiverAddress.getAddressId());
                        shipmentBillingDao.insertShipmentBillingSurcharge(context, shipmentBillingSurcharge);
                    }
                }

                Date startDate = DateUtils.convertStringToDate(AppConstants.APP_SETTINGS.getSelfInsuranceStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
                Date shipDate = shipmentBillingBaseCharge.getShipDate();
                Date importDate = shipmentBillingBaseCharge.getImportDate();
                if (shipDate == null || DateUtils.convertDateToString(shipDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null).equalsIgnoreCase("00-00-0000")) {
                    if (importDate.after(startDate)) {
                        ImportBillingServiceUtils.determineWarrantySerice(context, customerCode, shipmentBillingBaseCharge.getShipmentId(), shipmentBillingBaseCharge.getAirbillNumber(), shipmentBillingBaseCharge.getCarrier().intValue(), shipmentBillingBaseCharge.getDescription(), sessionClient);
                    }
                } else {
                    if (shipDate.after(startDate)) {
                        ImportBillingServiceUtils.determineWarrantySerice(context, customerCode, shipmentBillingBaseCharge.getShipmentId(), shipmentBillingBaseCharge.getAirbillNumber(), shipmentBillingBaseCharge.getCarrier().intValue(), shipmentBillingBaseCharge.getDescription(), sessionClient);
                    }
                }
                // Commit transaction
                sessionClient.endTransaction();
            } catch (Exception e) {
                // Roll back transaction
                logger.error(e);
                sessionClient.rollback();
                throw e;
            }
            // Update shipment billing
            sessionClient.startTransaction();
            try {
                ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
                ShipmentVo shipmentVo = (newShipmentVo != null) ? newShipmentVo : shipmentDao.selectById(saveImport.getBillingBaseCharge().getShipmentId());
                ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
                ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
                shipmentBillingFilter.setAirbillNumber(saveImport.getBillingBaseCharge().getAirbillNumber());
                shipmentBillingFilter.setShipmentId(saveImport.getBillingBaseCharge().getShipmentId());
                List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectByFilter(shipmentBillingFilter);
                ShipmentBillingVo shipmentBillingBaseCharge = new ShipmentBillingVo(), shipmentBillingSurcharge = new ShipmentBillingVo();
                for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {
                    if (shipmentBilling.getRequireCalculate()) {
                        if (shipmentBilling.getIsBaseCharge()) {
                            shipmentBillingBaseCharge = ShipmentBillingUtils.recalculateBaseCharge(shipmentVo.getCustomerCode(), shipmentBilling, iRecalculateCharge);
                            shipmentBillingDao.updateShipmentBilling(context, shipmentBillingBaseCharge);
                        } else {
                            shipmentBillingSurcharge = ShipmentBillingUtils.recalculateAccessorialCharge(shipmentVo.getCustomerCode(), shipmentBillingBaseCharge, shipmentBilling);
                            shipmentBillingDao.updateShipmentBilling(context, shipmentBillingSurcharge);
                        }
                    }
                }
                // Commit transaction
                sessionClient.endTransaction();
            } catch (Exception e) {
                // Roll back transaction
                logger.error(e);
                sessionClient.rollback();
                throw e;
            }
        }

    }

    private void saveInvoicePaymentDetail(Map<String, String> context, Long invoiceId, SqlSessionClient sessionClient) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao(sessionClient);
        List<ShipmentDetailInvoiceVo> detailInvoiceVo = invoiceDao.selectShipmentDetailForInvoice(invoiceId);
        if (detailInvoiceVo != null) {
            for (ShipmentDetailInvoiceVo shipmentDetailInvoice : detailInvoiceVo) {
                BigDecimal remain = shipmentDetailInvoice.getRemain();
                if (remain.compareTo(new BigDecimal("0")) == 0) {
                    reconcilePayment(context, remain, shipmentDetailInvoice.getInvoiceId(), shipmentDetailInvoice.getShipmentId(), shipmentDetailInvoice.getAirbillNumber(), sessionClient);
                }
            }
        }
    }

    private void reconcilePayment(Map<String, String> context, BigDecimal remain, Long invoiceId, Long shipmentId, String airbillNumber, SqlSessionClient sessionClient) throws DaoException {
        InvoicePaymentDao invoicePaymentDao = new InvoicePaymentDao(sessionClient);
        List<InvoicePaymentVo> invoicePayment = invoicePaymentDao.selectPaymentByInvoiceId(invoiceId);
        if (invoicePayment != null) {
            BigDecimal payAmount = BigDecimal.ZERO, paidAmount = BigDecimal.ZERO;
            Integer newFlag = 0;
            for (InvoicePaymentVo invoicePaymentVo : invoicePayment) {
                if (remain.compareTo(new BigDecimal("0")) == 1) {
                    payAmount = invoicePaymentVo.getAmount();
                    // Select invoice payment detail
                    InvoicePaymentDetailDao invoicePaymentDetailDao = new InvoicePaymentDetailDao(sessionClient);
                    List<InvoicePaymentDetailVo> invoicePaymentDetail = invoicePaymentDetailDao.selectByInvoicePayemntId(invoicePaymentVo.getInvoicePaymentId());
                    InvoicePaymentDetailVo saveInvoicePaymentDetail = new InvoicePaymentDetailVo();
                    if (invoicePaymentDetail == null) {
                        // Not yet reconcile shipment
                        // Reconcile
                        if (remain.compareTo(payAmount) == 1) {
                            paidAmount = payAmount;
                        } else {
                            paidAmount = remain;
                        }
                        // Save invoice payment detail
                        saveInvoicePaymentDetail.setInvoicePaymentId(invoicePaymentVo.getInvoicePaymentId());
                        saveInvoicePaymentDetail.setShipmentId(shipmentId);
                        saveInvoicePaymentDetail.setAirbillNumber(airbillNumber);
                        saveInvoicePaymentDetail.setAmount(paidAmount);
                        invoicePaymentDetailDao.insert(context, saveInvoicePaymentDetail);

                        remain = remain.subtract(paidAmount);
                        newFlag++;
                    } else if (newFlag >= 0) {
                        BigDecimal existAmount = BigDecimal.ZERO;
                        for (InvoicePaymentDetailVo invoicePaymentDetailVo : invoicePaymentDetail) {
                            existAmount = existAmount.add(invoicePaymentDetailVo.getAmount());
                        }
                        if (existAmount.compareTo(payAmount) == -1) {
                            paidAmount = payAmount.subtract(existAmount);
                            if (remain.compareTo(paidAmount) == -1) {
                                payAmount = remain;
                            }
                            // Save invoice payment detail
                            saveInvoicePaymentDetail.setInvoicePaymentId(invoicePaymentVo.getInvoicePaymentId());
                            saveInvoicePaymentDetail.setShipmentId(shipmentId);
                            saveInvoicePaymentDetail.setAirbillNumber(airbillNumber);
                            saveInvoicePaymentDetail.setAmount(paidAmount);
                            invoicePaymentDetailDao.insert(context, saveInvoicePaymentDetail);

                            remain = remain.subtract(paidAmount);
                            newFlag++;
                        }
                    }
                } else {
                    break;
                }
            }
        }
    }
}
