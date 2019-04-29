package com.gms.xms.persistence.service.importbilling;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.invoice.DuplicateAirbillDao;
import com.gms.xms.persistence.service.customer.IManageCustomerService;
import com.gms.xms.persistence.service.customer.ManageCustomerServiceImp;
import com.gms.xms.persistence.service.utils.ShipmentBillingUtils;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.persistence.utils.ImportBillingServiceUtils;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.imports.CheckDuplicateAirbillVo;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.invoicing.DuplicateAirbillVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from May 26, 2016 11:21:19 AM
 * <p>
 * Author huynd
 */
public class ImportBillingServiceImp implements IImportBillingService {
    private static final Log logger = LogFactory.getLog(ImportBillingServiceImp.class);

    @Override
    public void saveImportBilling(Map<String, String> context, SaveImportBillingVo saveImport, ShipmentVo shipmentVo, CheckDuplicateAirbillVo checkDuplicateAirbillVo, ShipmentInvoiceVo shipmentInvoice, InvoiceVo invoice, IRecalculateCharge iRecalculateCharge) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        // Insert shipment billing
        sessionClient.startTransaction();
        try {
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
            ShipmentBillingVo shipmentBillingBaseCharge = saveImport.getBillingBaseCharge();
            String airbillNumber = shipmentBillingBaseCharge.getAirbillNumber();
            Long carrierIdCheck = shipmentBillingBaseCharge.getCarrier();
            if (shipmentBillingBaseCharge != null && !StringUtils.isBlank(airbillNumber) && carrierIdCheck != null) {
                // Check duplicate airbill
                ShipmentBillingVo shipmentBillingCheck = shipmentBillingDao.checkDuplicateShipmentBilling(shipmentBillingBaseCharge);
                if (shipmentBillingCheck != null) {
                    // Save duplicate airbill
                    DuplicateAirbillVo duplicateAirbillVo = new DuplicateAirbillVo();
                    duplicateAirbillVo.setShipmentId(shipmentBillingCheck.getShipmentId());
                    duplicateAirbillVo.setAirbillNumber(shipmentBillingCheck.getAirbillNumber());
                    duplicateAirbillVo.setCarrier(shipmentBillingCheck.getCarrier());
                    duplicateAirbillVo.setImportDate(new Date());
                    DuplicateAirbillDao duplicateAirbillDao = new DuplicateAirbillDao(sessionClient);
                    duplicateAirbillDao.saveDuplicateAirbill(context, duplicateAirbillVo);
                } else {
                    ShipmentDao shipmentDao = new ShipmentDao(sessionClient);
                    Long carrierId = saveImport.getBillingBaseCharge().getCarrier();
                    // Insert Sender & Receiver Address
                    AddressDao addressDao = new AddressDao(sessionClient);
                    AddressVo senderAddress = new AddressVo();
                    AddressVo receiverAddress = new AddressVo();
                    if (carrierId == 72 && shipmentVo == null) { // Startrack
                        ShipmentVo shipmentStartrack = shipmentDao.selectById(saveImport.getBillingBaseCharge().getShipmentId());
                        senderAddress = addressDao.selectById(shipmentStartrack.getSenderAddressId());
                    } else {
                        senderAddress = saveImport.getSenderAddress();
                        addressDao.insert(context, senderAddress);
                    }
                    receiverAddress = saveImport.getReceiverAddress();
                    addressDao.insert(context, receiverAddress);

                    // Create new invoice
                    Long invoiceId = 0L;
                    if (invoice != null) {
                        InvoiceDao invoiceDao = new InvoiceDao(sessionClient);

                        InvoiceVo invoiceVo = invoiceDao.selectByCode(invoice.getInvoiceCode());
                        if (invoiceVo == null) {
                            invoiceDao.insert(context, invoice);
                            // Update customer activation date.
                            IManageCustomerService manageCustomerService = new ManageCustomerServiceImp();
                            manageCustomerService.updateCustomerActivationDate(context, String.valueOf(invoice.getCustomerCode()), sessionClient);
                            invoiceId = invoice.getInvoiceId();
                        } else {
                            invoiceId = invoiceVo.getInvoiceId();
                        }

                        // Update invoice numbering counter.
                        // InvoiceNumberingService numberingService = new
                        // InvoiceNumberingService();
                        // numberingService.updateCounter(context, invoice,
                        // sessionClient);

                    }

                    // Insert shipment
                    Long shipmentId = 0L;
                    if (shipmentVo != null) {
                        shipmentVo.setReceiverAddressId(receiverAddress.getAddressId());
                        shipmentVo.setSenderAddressId(senderAddress.getAddressId());
                        shipmentDao.insertShipment(context, shipmentVo);
                        shipmentId = shipmentVo.getShipmentId();
                    }

                    // Insert shipment invoice
                    if (shipmentInvoice != null) {
                        ShipmentInvoiceDao shipmentInvoiceDao = new ShipmentInvoiceDao(sessionClient);
                        if (invoice != null) {
                            shipmentInvoice.setInvoiceId(invoiceId);
                        }
                        if (shipmentVo != null) {
                            shipmentInvoice.setShipmentId(shipmentVo.getShipmentId());
                        } else {
                            shipmentInvoice.setShipmentId(saveImport.getBillingBaseCharge().getShipmentId());
                        }
                        shipmentInvoiceDao.insert(context, shipmentInvoice);
                    }

                    Long customerCode = saveImport.getCustomerCode();
                    // Insert shipment billing base charge
                    if (shipmentId != 0) {
                        shipmentBillingBaseCharge.setShipmentId(shipmentId);
                    }
                    shipmentBillingBaseCharge.setSenderAddressId(senderAddress.getAddressId());
                    shipmentBillingBaseCharge.setReceiverAddressId(receiverAddress.getAddressId());
                    shipmentBillingDao = new ShipmentBillingDao(sessionClient);
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
            if (checkDuplicateAirbillVo == null) {
                ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
                ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
                if (shipmentVo != null) {
                    shipmentBillingFilter.setAirbillNumber(shipmentVo.getAirbillNumber());
                    shipmentBillingFilter.setShipmentId(shipmentVo.getShipmentId());
                } else {
                    shipmentBillingFilter.setAirbillNumber(saveImport.getBillingBaseCharge().getAirbillNumber());
                    shipmentBillingFilter.setShipmentId(saveImport.getBillingBaseCharge().getShipmentId());
                }
                List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectByFilter(shipmentBillingFilter);
                ShipmentBillingVo shipmentBillingBaseCharge = new ShipmentBillingVo(), shipmentBillingSurcharge = new ShipmentBillingVo();
                for (ShipmentBillingVo shipmentBilling : shipmentBillingVos) {
                    if (shipmentBilling.getRequireCalculate()) {
                        if (shipmentBilling.getIsBaseCharge()) {
                            shipmentBillingBaseCharge = ShipmentBillingUtils.recalculateBaseCharge4Import(saveImport.getCustomerCode(), shipmentBilling, iRecalculateCharge);
                            shipmentBillingDao.updateShipmentBilling(context, shipmentBillingBaseCharge);
                        } else {
                            shipmentBillingSurcharge = ShipmentBillingUtils.recalculateAccessorialCharge4Import(saveImport.getCustomerCode(), shipmentBillingBaseCharge, shipmentBilling);
                            shipmentBillingDao.updateShipmentBilling(context, shipmentBillingSurcharge);
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
    }
}
