package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.txndb.vo.CreditNoteDetailVo;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.txndb.vo.adjustment.AdjustmentHistoryVo;
import com.gms.xms.txndb.vo.adjustment.CarrierAdjustmentVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from CarrierAdjustmentAcceptSubmittedDaoService
 * <p>
 * Author TanDT Date May 28, 2015
 */
public class CarrierAdjustmentAcceptSubmittedDaoService {

    private static final Log logger = LogFactory.getLog(CarrierAdjustmentAcceptSubmittedDaoService.class);

    public void updateMultiCarrierAdjustmentById(Map<String, String> context, Long userId, List<Long> listAdjustmentId) throws Exception {
        for (Long adjustmentId : listAdjustmentId) {
            this.updateCarrierAdjustmentById(context, userId, adjustmentId);
        }
    }

    /**
     * Action Accept for a one AdjustmentId
     *
     * @param adjustmentId
     * @throws DaoException
     */
    public void updateCarrierAdjustmentById(Map<String, String> context, Long userId, Long adjustmentId) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        AirbillAdjustmentDao airbillAdjustmentDao = new AirbillAdjustmentDao(sessionClient);
        CarrierAdjustmentDao carrierAdjustmentDao = new CarrierAdjustmentDao(sessionClient);
        AdjustmentHistoryDao adjustmentHistoryDao = new AdjustmentHistoryDao(sessionClient);

        CreditNoteDao creditNoteDao = new CreditNoteDao(sessionClient);
        try {
            // Get Detail Carrier Adjustment Detail from AdjId
            CarrierAdjustmentVo carrierAdjustmentVo = new CarrierAdjustmentVo();
            carrierAdjustmentVo = carrierAdjustmentDao.selectCarrierAdjustmentDetail(adjustmentId);
            // Update AdjustmentVo
            AirbillAdjustmentVo airbillAdjustmentVo = new AirbillAdjustmentVo();
            airbillAdjustmentVo.setStatus((byte) 4);
            airbillAdjustmentVo.setAdjustmentId(adjustmentId);
            airbillAdjustmentDao.update(context, airbillAdjustmentVo);
            // Insert AdjustmentHistory
            AdjustmentHistoryVo adjustmentHistoryVo = new AdjustmentHistoryVo();
            adjustmentHistoryVo.setAdjustmentId(adjustmentId);
            adjustmentHistoryVo.setStatus(4);
            adjustmentHistoryVo.setUserId(userId);
            adjustmentHistoryVo.setActionDate(new Date());
            adjustmentHistoryDao.insert(context, adjustmentHistoryVo);

            // Insert to creditnote and creditnot detail
            Long creditNoteId = null;
            String newCreditCode = GenCodeUtils.generateCreditCode(carrierAdjustmentVo.getInvoiceCode(), Calendar.getInstance().getTime());
            CreditNoteVo creditNoteVo = creditNoteDao.selectByCreditCode(newCreditCode);
            if (creditNoteVo != null) {
                creditNoteId = creditNoteVo.getCreditNoteId();
                // If the credit code existed then check it's status
                if (creditNoteVo.getStatus() == 1) {
                    // If it have been frozen
                    throw new CustomException("The credit code [" + creditNoteVo.getCreditCode() + "] was frozen.");
                } else if (creditNoteVo.getStatus() == 2) {
                    // If it have been send email
                    throw new CustomException("The credit code [" + creditNoteVo.getCreditCode() + "] was send email.");
                }
            } else {
                CreditNoteVo creditNoteInserVo = new CreditNoteVo();
                creditNoteInserVo.setCreditCode(newCreditCode);
                creditNoteInserVo.setCreateDate(Calendar.getInstance().getTime());
                creditNoteInserVo.setInvoiceCode(carrierAdjustmentVo.getInvoiceCode());
                creditNoteInserVo.setStatus(0);
                creditNoteDao.insert(context, creditNoteInserVo);
                creditNoteId = creditNoteInserVo.getCreditNoteId();
            }
            this.insertCreditNoteDetail(context, adjustmentId, creditNoteId, carrierAdjustmentVo, sessionClient);
            sessionClient.endTransaction();
        } catch (Exception ex) {
            logger.error(ex);
            sessionClient.rollback();
            throw ex;
        }
    }

    private void insertCreditNoteDetail(Map<String, String> context, Long adjustmentId, Long creditNoteId, CarrierAdjustmentVo carrierAdjustmentVo, SqlSessionClient sessionClient) throws DaoException {
        CreditNoteDetailDao creditNoteDetailDao = new CreditNoteDetailDao(sessionClient);
        CreditNoteDetailVo creditNoteDetailVo = new CreditNoteDetailVo();
        creditNoteDetailVo.setReason(carrierAdjustmentVo.getAdjustmentType());
        creditNoteDetailVo.setCreditNoteId(creditNoteId);
        creditNoteDetailVo.setAmount(carrierAdjustmentVo.getCustomerAmountNonGst());
        creditNoteDetailVo.setGstAmount(carrierAdjustmentVo.getCustomerAmountNonGst());
        creditNoteDetailVo.setAdjustmentid(adjustmentId);
        creditNoteDetailVo.setCusPaymentid(0L);
        creditNoteDetailVo.setCreditAirbillNumber("");
        creditNoteDetailDao.insert(context, creditNoteDetailVo);
    }
}
