package com.gms.xms.persistence.dao;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.adjustmentrequest.AirbillAdjustmentRequestDao;
import com.gms.xms.persistence.service.InvoiceNumberingService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.adjustment.AdjustmentHistoryVo;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * Posted from CreditNoteDaoService
 * <p>
 * Author DatTV Date May 20, 2015 10:35:06 PM
 */
public class CreditNoteDaoService {

    private static final Log logger = LogFactory.getLog(CreditNoteDaoService.class);

    private CreditNoteDao creditNoteDao;
    private CreditNoteDetailDao creditNoteDetailDao;
    private AirbillAdjustmentDao adjustmentDao;
    private AirbillAdjustmentRequestDao adjustmentRequestDao;
    private InvoiceDao invoiceDao;
    private AirbillPausingDeductDao pausingDeductDao;
    private AdjustmentHistoryDao adjustmentHistoryDao;

    public void saveAdjustmentRequests(Map<String, String> context, CreditNoteVo creditNote, List<AirbillAdjustmentVo> airbillAdjustmentList) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        creditNoteDao = new CreditNoteDao(sessionClient);
        creditNoteDetailDao = new CreditNoteDetailDao(sessionClient);
        adjustmentDao = new AirbillAdjustmentDao(sessionClient);
        pausingDeductDao = new AirbillPausingDeductDao(sessionClient);
        adjustmentHistoryDao = new AdjustmentHistoryDao(sessionClient);

        try {
            // Split airbill adjustment list
            List<AirbillAdjustmentVo> creditNowList = new ArrayList<AirbillAdjustmentVo>();
            List<AirbillAdjustmentVo> carrierCreditList = new ArrayList<AirbillAdjustmentVo>();

            for (AirbillAdjustmentVo adjustment : airbillAdjustmentList) {
                // Insert adjustment
                adjustment.setStartPausingDate(new Date());
                adjustmentDao.insert(context, adjustment);

                if (adjustment.getCreditType() == 1) {
                    creditNowList.add(adjustment);
                } else if (adjustment.getCreditType() == 2) {
                    carrierCreditList.add(adjustment);
                } else {
                    carrierCreditList.add(adjustment);
                    AirbillPausingDeductVo pausingDeductVo = pausingDeductDao.selectByAirbillNumber(adjustment.getAirbillNumber());
                    if (pausingDeductVo == null) {
                        pausingDeductVo = new AirbillPausingDeductVo();
                        pausingDeductVo.setAirbillNumber(adjustment.getAirbillNumber());
                        pausingDeductVo.setPausingDay(0L);
                        pausingDeductVo.setStartDate(new Date());
                        pausingDeductDao.insert(context, pausingDeductVo);
                    }
                }
            }

            // Check if credit now list has content or not?
            if (creditNowList.size() > 0) {
                // Insert credit note if it's not null
                Long creditNoteId = null;
                if (creditNote != null) {
                    // Check if the existing of credit code
                    CreditNoteVo currentCreditNote = creditNoteDao.selectByCreditCode(creditNote.getCreditCode());
                    if (currentCreditNote != null) {
                        // If the credit code existed then check it's status
                        if (currentCreditNote.getStatus() == 1) {
                            // If it have been frozen
                            throw new DaoException("The credit code [" + currentCreditNote.getCreditCode() + "] was frozen.");
                        } else if (currentCreditNote.getStatus() == 2) {
                            // If it have been send email
                            throw new DaoException("The credit code [" + currentCreditNote.getCreditCode() + "] was send email.");
                        } else {
                            creditNoteId = currentCreditNote.getCreditNoteId();
                        }
                    } else {
                        creditNoteDao.insert(context, creditNote);
                        creditNoteId = creditNote.getCreditNoteId();
                    }
                }
                Long userId = Long.valueOf(context.get(Attributes.ADD_INFO_EXT_USER_ID));
                // Insert credit note detail
                for (AirbillAdjustmentVo adjustment : creditNowList) {
                    CreditNoteDetailVo creditNoteDetailVo = new CreditNoteDetailVo();
                    creditNoteDetailVo.setCreditNoteId(creditNoteId);
                    creditNoteDetailVo.setReason(adjustment.getAdjustmentType());
                    creditNoteDetailVo.setAdjustmentid(adjustment.getAdjustmentId());
                    creditNoteDetailVo.setCusPaymentid(0L);
                    creditNoteDetailVo.setCreditAirbillNumber("");
                    creditNoteDetailVo.setAmount(adjustment.getCustomerAmount());
                    creditNoteDetailVo.setGstAmount(adjustment.getGstCustomerAmount());
                    creditNoteDetailDao.insert(context, creditNoteDetailVo);

                    // Update airbill adjustment history
                    AdjustmentHistoryVo adjustmentHistoryVo = new AdjustmentHistoryVo();
                    adjustmentHistoryVo.setAdjustmentId(adjustment.getAdjustmentId());
                    adjustmentHistoryVo.setStatus(4);
                    adjustmentHistoryVo.setUserId(userId);
                    adjustmentHistoryVo.setActionDate(new Date());
                    adjustmentHistoryDao.insert(context, adjustmentHistoryVo);
                }
            }

            sessionClient.endTransaction();
        } catch (DaoException ex) {
            logger.error(ex);
            sessionClient.rollback();
            throw ex;
        }
    }

    public void doFreezeCreditNotes(Map<String, String> context, Long creditNoteId) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        CustomerPaymentDao customerPaymentDao = new CustomerPaymentDao(sessionClient);
        InvoicePaymentDao invoicePaymentDao = new InvoicePaymentDao(sessionClient);
        InvoicePaymentDetailDao detailDao = new InvoicePaymentDetailDao(sessionClient);
        OverpaymentDao overpaymentDao = new OverpaymentDao(sessionClient);
        CreditNoteDao creditNoteDao = new CreditNoteDao();
        CreditNoteDetailDao creditNoteDetailDao = new CreditNoteDetailDao(sessionClient);

        AirbillCreditNoteDao airbillCreditNoteDao = new AirbillCreditNoteDao();
        List<AirbillCreditNoteVo> airbillCreditNoteList = airbillCreditNoteDao.getAdjustmentAirbillInfo(creditNoteId);

        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();

        // Create hash map to keep track owed of the airbill
        Map<String, Double> owedList = new HashMap<String, Double>();
        Double owed = null;
        for (AirbillCreditNoteVo airbillCreditNoteVo : airbillCreditNoteList) {
            owed = airbillCreditNoteVo.getAwbTotal() - airbillCreditNoteVo.getAwbPaid();
            owedList.put(airbillCreditNoteVo.getAirbillNumber(), owed);
        }

        Double total = null;
        String airbillNumber;
        Long customerCode = 0L;
        try {
            for (AirbillCreditNoteVo airbillCreditNoteVo : airbillCreditNoteList) {
                // Ignore the credit note detail with customer payment id is not
                // 0
                customerCode = airbillCreditNoteVo.getCustomerCode();
                if (airbillCreditNoteVo.getCusPaymentId() == 0) {
                    airbillNumber = airbillCreditNoteVo.getAirbillNumber();
                    owed = owedList.get(airbillNumber);
                    total = airbillCreditNoteVo.getTotalAmount();

                    // Create customer payment info
                    CustomerPaymentVo customerPaymentVo = new CustomerPaymentVo();
                    customerPaymentVo.setCustomerCode(airbillCreditNoteVo.getCustomerCode());
                    String cheque = "";
                    switch (airbillCreditNoteVo.getCreditType()) {
                        case 0:
                            cheque = "Carrier Credit Note";
                            break;
                        case 1:
                            cheque = "Credit Note";
                            break;
                        case 2:
                            cheque = "Agl Warranty Credit";
                            break;
                    }
                    customerPaymentVo.setCheque(cheque);
                    customerPaymentVo.setBankId(0L);
                    customerPaymentVo.setPaymentDate(now);
                    customerPaymentVo.setPaymentType(0); // Cheque

                    // Insert customer payment
                    customerPaymentVo.setAmount(new BigDecimal(total));
                    customerPaymentDao.insert(context, customerPaymentVo);
                    Long cusPaymentId = customerPaymentVo.getCusPaymentId();

                    // Update customer payment id for credit note detail of this
                    // credit note
                    CreditNoteDetailVo creditNoteDetailVo = new CreditNoteDetailVo();
                    creditNoteDetailVo.setCreditNoteId(creditNoteId);
                    creditNoteDetailVo.setAdjustmentid(airbillCreditNoteVo.getAdjustmentId());

                    CreditNoteDetailVo creditNoteDetail = creditNoteDetailDao.selectByVo(creditNoteDetailVo);
                    creditNoteDetail.setCusPaymentid(cusPaymentId);
                    creditNoteDetailDao.updateCustomerPaymentId(context, creditNoteDetail);

                    // If this invoice has no owed so customer payment need to
                    // be convert to over payment
                    if (owed <= 0 && total > 0) {
                        // Insert over payment
                        OverpaymentVo overpaymentVo = new OverpaymentVo();
                        overpaymentVo.setOverAmount(new BigDecimal(total));
                        overpaymentVo.setCusPaymentId(cusPaymentId);
                        overpaymentDao.insert(context, overpaymentVo);
                        continue;
                    }

                    // If the owed of this invoice less than the total payment
                    if (total > owed) {
                        // Insert invoice payment
                        InvoicePaymentVo invoicePaymentVo = new InvoicePaymentVo();
                        invoicePaymentVo.setInvoiceId(airbillCreditNoteVo.getInvoiceId());
                        invoicePaymentVo.setCusPaymentId(cusPaymentId);
                        invoicePaymentVo.setLateFee(BigDecimal.ZERO);
                        invoicePaymentVo.setAmount(new BigDecimal(owed));
                        invoicePaymentVo.setReverseFlag((byte) 0);
                        invoicePaymentVo.setRevInvoicePaymentId(0L);
                        invoicePaymentVo.setApplyDate(now);
                        invoicePaymentDao.insert(context, invoicePaymentVo);

                        Long invoicePaymentId = invoicePaymentVo.getInvoicePaymentId();

                        // Insert invoice payment detail
                        InvoicePaymentDetailVo detailVo = new InvoicePaymentDetailVo();
                        detailVo.setInvoicePaymentId(invoicePaymentId);
                        detailVo.setAirbillNumber(airbillCreditNoteVo.getAirbillNumber());
                        detailVo.setShipmentId(airbillCreditNoteVo.getShipmentId());
                        detailVo.setAmount(new BigDecimal(owed));
                        detailDao.insert(context, detailVo);

                        owedList.put(airbillNumber, 0D);

                        // Insert over payment
                        OverpaymentVo overpaymentVo = new OverpaymentVo();
                        overpaymentVo.setOverAmount(new BigDecimal(total - owed));
                        overpaymentVo.setCusPaymentId(cusPaymentId);
                        overpaymentDao.insert(context, overpaymentVo);
                    } else {
                        // Insert invoice payment
                        InvoicePaymentVo invoicePaymentVo = new InvoicePaymentVo();
                        invoicePaymentVo.setInvoiceId(airbillCreditNoteVo.getInvoiceId());
                        invoicePaymentVo.setCusPaymentId(cusPaymentId);
                        invoicePaymentVo.setLateFee(BigDecimal.ZERO);
                        invoicePaymentVo.setAmount(new BigDecimal(total));
                        invoicePaymentVo.setReverseFlag((byte) 0);
                        invoicePaymentVo.setRevInvoicePaymentId(0L);
                        invoicePaymentVo.setApplyDate(now);
                        invoicePaymentDao.insert(context, invoicePaymentVo);

                        Long invoicePaymentId = invoicePaymentVo.getInvoicePaymentId();

                        // Insert invoice payment detail
                        InvoicePaymentDetailVo detailVo = new InvoicePaymentDetailVo();
                        detailVo.setInvoicePaymentId(invoicePaymentId);
                        detailVo.setAirbillNumber(airbillCreditNoteVo.getAirbillNumber());
                        detailVo.setShipmentId(airbillCreditNoteVo.getShipmentId());
                        detailVo.setAmount(new BigDecimal(total));
                        detailDao.insert(context, detailVo);

                        owed -= total;
                        owedList.put(airbillNumber, owed);
                    }
                }
            }

            // Update status for credit note (Frozen)
            CreditNoteVo creditNoteVo = creditNoteDao.selectById(creditNoteId);
            creditNoteVo.setStatus(1);
            creditNoteDao.update(context, creditNoteVo);

            // Update counter
            SystemSettingDao settingDao = new SystemSettingDao();
            SystemSettingVo settingVo = settingDao.getSystemSettingByName(AppConstants.DEFAULT_ORIGIN_COUNTRY);
            String defaultCountryId = settingVo.getSettingValue();
            if (defaultCountryId.equalsIgnoreCase("70")) { // FR invoice
                // numbering
                Date creditNoteDate = creditNoteVo.getCreateDate();
                InvoiceNumberingService invoiceNumberingService = new InvoiceNumberingService();
                invoiceNumberingService.updateCounter(context, creditNoteDate, customerCode, creditNoteVo.getCreditCode(), sessionClient);
            }

            // Get current max unique number

            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll-back transaction if it has error
            sessionClient.rollback();
            throw e;
        }
    }

    public void doUnFreezeCreditNotes(Map<String, String> context, Long creditNoteId) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        CustomerPaymentDao customerPaymentDao = new CustomerPaymentDao(sessionClient);
        InvoicePaymentDao invoicePaymentDao = new InvoicePaymentDao(sessionClient);
        InvoicePaymentDetailDao invoicePaymentDetailDao = new InvoicePaymentDetailDao(sessionClient);
        OverpaymentDao overpaymentDao = new OverpaymentDao(sessionClient);
        CreditNoteDetailDao creditNoteDetailDao = new CreditNoteDetailDao(sessionClient);
        CreditNoteDao creditNoteDao = new CreditNoteDao(sessionClient);

        AirbillCreditNoteDao airbillCreditNoteDao = new AirbillCreditNoteDao();
        List<AirbillCreditNoteVo> airbillCreditNoteList = airbillCreditNoteDao.getAdjustmentAirbillInfo(creditNoteId);

        try {
            for (AirbillCreditNoteVo airbillCreditNoteVo : airbillCreditNoteList) {
                Long cusPaymentId = airbillCreditNoteVo.getCusPaymentId();

                List<InvoicePaymentVo> invoicePaymentVos = invoicePaymentDao.selectInvoicePaymentByCustomerPaymentId(cusPaymentId);
                if (!invoicePaymentVos.isEmpty()) {
                    for (InvoicePaymentVo invoicePaymentVo : invoicePaymentVos) {
                        List<InvoicePaymentDetailVo> detailVos = invoicePaymentDetailDao.selectByInvoicePayemntId(invoicePaymentVo.getInvoicePaymentId());
                        if (!detailVos.isEmpty()) {
                            for (InvoicePaymentDetailVo invoicePaymentDetailVo : detailVos) {
                                invoicePaymentDetailDao.delete(context, invoicePaymentDetailVo);
                            }
                        }
                        invoicePaymentDao.delete(context, invoicePaymentVo.getInvoicePaymentId());
                    }
                }

                OverpaymentVo overpaymentVo = overpaymentDao.selectByCustomerPaymentId(cusPaymentId);
                if (overpaymentVo != null) {
                    overpaymentDao.delete(context, cusPaymentId);
                }

                CustomerPaymentVo customerPaymentVo = customerPaymentDao.selectCustomerPayment(cusPaymentId);
                if (customerPaymentVo != null) {
                    customerPaymentDao.delete(context, cusPaymentId);
                }

            }
            creditNoteDetailDao.resetCustomerPaymentId(context, creditNoteId);
            CreditNoteVo creditNoteVo = creditNoteDao.selectById(creditNoteId);
            creditNoteVo.setStatus(0);
            creditNoteDao.update(context, creditNoteVo);
            sessionClient.endTransaction();
        } catch (Exception e) {
            sessionClient.rollback();
            throw e;
        }
    }

    public void editAdjustment(Map<String, String> context, AirbillAdjustmentVo adjustmentVo) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        creditNoteDao = new CreditNoteDao(sessionClient);
        creditNoteDetailDao = new CreditNoteDetailDao(sessionClient);
        adjustmentDao = new AirbillAdjustmentDao(sessionClient);
        invoiceDao = new InvoiceDao(sessionClient);

        try {
            // Get old adjustment info
            AirbillAdjustmentVo oldAdjustmentVo = adjustmentDao.selectById(adjustmentVo.getAdjustmentId());
            ShipmentVo shipmentVo;
            // Check Disputed Denied Status
            if (oldAdjustmentVo.getStatus() == 5) {
                // Only update this adjustment if it's Credit Type has change to
                // Credit Now
                if (oldAdjustmentVo.getCreditType() == 0 && adjustmentVo.getCreditType() == 1) {
                    // Change it's status to Approved
                    adjustmentVo.setStatus((byte) 4);
                    adjustmentDao.update(context, adjustmentVo);

                    // Get invoice of the airbill
                    shipmentVo = new ShipmentVo();
                    shipmentVo.setAirbillNumber(oldAdjustmentVo.getAirbillNumber());
                    shipmentVo.setShipmentId(oldAdjustmentVo.getShipmentId());
                    InvoiceVo invoiceVo = invoiceDao.selectByAirbillNumber(shipmentVo);
                    if (invoiceVo == null) {
                        throw new Exception("No invoice for this airbill adjustment.");
                    }

                    // Generate Credit Code
                    Date now = Calendar.getInstance().getTime();
                    String creditCode = GenCodeUtils.generateCreditCode(invoiceVo.getInvoiceCode(), now);

                    // Check if the existing of Credit Note
                    Long creditNoteId = null;
                    CreditNoteVo currentCreditNote = creditNoteDao.selectByCreditCode(creditCode);
                    if (currentCreditNote != null) {
                        // If the credit code existed then check it's status
                        if (currentCreditNote.getStatus() == 1) {
                            // If it have been frozen
                            throw new Exception("The credit code [" + currentCreditNote.getCreditCode() + "] was frozen.");
                        } else if (currentCreditNote.getStatus() == 2) {
                            // If it have been send email
                            throw new Exception("The credit code [" + currentCreditNote.getCreditCode() + "] was send email.");
                        } else {
                            creditNoteId = currentCreditNote.getCreditNoteId();
                        }
                    } else {
                        // Insert new Credit Note
                        CreditNoteVo creditNote = new CreditNoteVo();
                        creditNote.setInvoiceCode(invoiceVo.getInvoiceCode());
                        creditNote.setCreateDate(now);
                        creditNote.setCreditCode(creditCode);
                        creditNote.setStatus(0);

                        creditNoteDao.insert(context, creditNote);
                        creditNoteId = creditNote.getCreditNoteId();
                    }

                    // Check if this adjustment existed in credit note detail?
                    CreditNoteDetailVo filter = new CreditNoteDetailVo();
                    filter.setAdjustmentid(adjustmentVo.getAdjustmentId());
                    filter.setCreditNoteId(creditNoteId);
                    CreditNoteDetailVo checkCreditNoteDetail = creditNoteDetailDao.selectByVo(filter);

                    // Insert Credit Note Detail if it doesn't exists
                    if (checkCreditNoteDetail == null) {
                        CreditNoteDetailVo creditNoteDetailVo = new CreditNoteDetailVo();
                        creditNoteDetailVo.setCreditNoteId(creditNoteId);
                        creditNoteDetailVo.setReason(adjustmentVo.getAdjustmentType());
                        creditNoteDetailVo.setAdjustmentid(adjustmentVo.getAdjustmentId());
                        creditNoteDetailVo.setCusPaymentid(0L);
                        creditNoteDetailVo.setCreditAirbillNumber("");
                        creditNoteDetailVo.setAmount(adjustmentVo.getCustomerAmount());
                        creditNoteDetailVo.setGstAmount(adjustmentVo.getGstCustomerAmount());
                        creditNoteDetailDao.insert(context, creditNoteDetailVo);
                    } else {
                        // Update if it exists
                        checkCreditNoteDetail.setAmount(adjustmentVo.getCustomerAmount());
                        checkCreditNoteDetail.setGstAmount(adjustmentVo.getGstCustomerAmount());
                        creditNoteDetailDao.update(context, checkCreditNoteDetail);
                    }
                } else {
                    throw new Exception("Please change credit type to Credit Now to continue.");
                }
            } else {
                // If change Credit Type from Upon carrier approval to Credit
                // Now
                if (oldAdjustmentVo.getCreditType() == 0 && adjustmentVo.getCreditType() == 1) {
                    // Change it's status to Approved
                    adjustmentVo.setStatus((byte) 4);
                    adjustmentDao.update(context, adjustmentVo);

                    // Get invoice of the airbill
                    shipmentVo = new ShipmentVo();
                    shipmentVo.setAirbillNumber(oldAdjustmentVo.getAirbillNumber());
                    shipmentVo.setShipmentId(oldAdjustmentVo.getShipmentId());
                    InvoiceVo invoiceVo = invoiceDao.selectByAirbillNumber(shipmentVo);
                    if (invoiceVo == null) {
                        throw new Exception("No invoice for this airbill adjustment.");
                    }

                    // Generate Credit Code
                    Date now = Calendar.getInstance().getTime();
                    String creditCode = GenCodeUtils.generateCreditCode(invoiceVo.getInvoiceCode(), now);

                    // Check if the existing of Credit Note
                    Long creditNoteId = null;
                    CreditNoteVo currentCreditNote = creditNoteDao.selectByCreditCode(creditCode);
                    if (currentCreditNote != null) {
                        // If the credit code existed then check it's status
                        if (currentCreditNote.getStatus() == 1) {
                            // If it have been frozen
                            throw new Exception("The credit code [" + currentCreditNote.getCreditCode() + "] was frozen.");
                        } else if (currentCreditNote.getStatus() == 2) {
                            // If it have been send email
                            throw new Exception("The credit code [" + currentCreditNote.getCreditCode() + "] was send email.");
                        } else {
                            creditNoteId = currentCreditNote.getCreditNoteId();
                        }
                    } else {
                        // Insert new Credit Note
                        CreditNoteVo creditNote = new CreditNoteVo();
                        creditNote.setInvoiceCode(invoiceVo.getInvoiceCode());
                        creditNote.setCreateDate(now);
                        creditNote.setCreditCode(creditCode);
                        creditNote.setStatus(0);

                        creditNoteDao.insert(context, creditNote);
                        creditNoteId = creditNote.getCreditNoteId();
                    }

                    // Check if this adjustment existed in credit note detail?
                    CreditNoteDetailVo filter = new CreditNoteDetailVo();
                    filter.setAdjustmentid(adjustmentVo.getAdjustmentId());
                    filter.setCreditNoteId(creditNoteId);
                    CreditNoteDetailVo checkCreditNoteDetail = creditNoteDetailDao.selectByVo(filter);

                    // Insert Credit Note Detail if it doesn't exists
                    if (checkCreditNoteDetail == null) {
                        CreditNoteDetailVo creditNoteDetailVo = new CreditNoteDetailVo();
                        creditNoteDetailVo.setCreditNoteId(creditNoteId);
                        creditNoteDetailVo.setReason(adjustmentVo.getAdjustmentType());
                        creditNoteDetailVo.setAdjustmentid(adjustmentVo.getAdjustmentId());
                        creditNoteDetailVo.setCusPaymentid(0L);
                        creditNoteDetailVo.setCreditAirbillNumber("");
                        creditNoteDetailVo.setAmount(adjustmentVo.getCustomerAmount());
                        creditNoteDetailVo.setGstAmount(adjustmentVo.getGstCustomerAmount());
                        creditNoteDetailDao.insert(context, creditNoteDetailVo);
                    } else {
                        // Update if it exists
                        checkCreditNoteDetail.setAmount(adjustmentVo.getCustomerAmount());
                        checkCreditNoteDetail.setGstAmount(adjustmentVo.getGstCustomerAmount());
                        creditNoteDetailDao.update(context, checkCreditNoteDetail);
                    }
                } else if (oldAdjustmentVo.getCreditType() == 1 && adjustmentVo.getCreditType() == 0) {
                    // Change Credit Type from Credit Now to Upon Carrier
                    // Approval
                    // Then update the adjustment info
                    adjustmentDao.update(context, adjustmentVo);

                    // Get credit note detail by adjustment id
                    CreditNoteDetailVo creditNoteDetailVo = creditNoteDetailDao.selectByAdjustmentId(adjustmentVo.getAdjustmentId());

                    // Delete credit note detail
                    creditNoteDetailDao.deleteCreditDetailByAdjustmentId(context, adjustmentVo.getAdjustmentId());

                    // Delete credit note if has no credit note detail
                    if (creditNoteDetailVo != null && creditNoteDetailDao.countByCreditNoteId(creditNoteDetailVo.getCreditNoteId()) == 0) {
                        creditNoteDao.deleteById(context, creditNoteDetailVo.getCreditNoteId());
                    }
                } else {
                    // Only update the adjustment info
                    adjustmentDao.update(context, adjustmentVo);

                    // Get credit note detail by adjustment id
                    CreditNoteDetailVo creditNoteDetailVo = creditNoteDetailDao.selectByAdjustmentId(adjustmentVo.getAdjustmentId());

                    // Update amount and gst amount if credit note detail for
                    // this adjustment existed
                    if (creditNoteDetailVo != null) {
                        creditNoteDetailVo.setAmount(adjustmentVo.getCustomerAmount());
                        creditNoteDetailVo.setGstAmount(adjustmentVo.getGstCustomerAmount());
                        creditNoteDetailDao.update(context, creditNoteDetailVo);
                    }
                }
            }
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }
    }

    public void editAdjustmentRequest(Map<String, String> context, AirbillAdjustmentRequestVo adjustmentVo) throws Exception {
        adjustmentRequestDao = new AirbillAdjustmentRequestDao();
        try {
            adjustmentRequestDao.update(context, adjustmentVo);
        } catch (Exception e) {
            throw e;
        }
    }
}
