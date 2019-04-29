package com.gms.xms.persistence.dao;

import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.DateUtils.Type;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.txndb.vo.AirbillPausingDeductVo;
import com.gms.xms.txndb.vo.CreditNoteDetailVo;
import com.gms.xms.txndb.vo.InvoiceVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Posted from AdjustmentDaoService
 * <p>
 * Author DatTV Date Jun 22, 2015 2:41:05 PM
 */
public class AdjustmentDaoService {
    private static final Log logger = LogFactory.getLog(AdjustmentDaoService.class);

    public void updateAdjustment(Map<String, String> context, AirbillAdjustmentVo adjustment) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao(sessionClient);
        CreditNoteDetailDao creditNoteDetailDao = new CreditNoteDetailDao(sessionClient);
        CreditNoteDao creditNoteDao = new CreditNoteDao(sessionClient);
        AirbillPausingDeductDao pausingDeductDao = new AirbillPausingDeductDao(sessionClient);

        Long adjustmentId = adjustment.getAdjustmentId();
        try {
            // Get original adjustment to check status to update pausing deduct
            AirbillAdjustmentVo orgAdjustmentVo = adjustmentDao.selectById(adjustmentId);

            AirbillPausingDeductVo airbillPausingDeductVo = pausingDeductDao.selectByAirbillNumber(adjustment.getAirbillNumber());

            // If this adjustment has Pausing Deduct record
            // and Credit Type = 0 (Is Carrier Credit Note)
            if (airbillPausingDeductVo != null && orgAdjustmentVo.getCreditType() == 0) {
                switch (orgAdjustmentVo.getStatus()) {
                    case 1:
                        switch (adjustment.getStatus()) {
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                                if (adjustment.getCreditType() != 2 && checkStartAdjustmentDate(orgAdjustmentVo, airbillPausingDeductVo)) {
                                    orgAdjustmentVo.setPausingDay(plusPausingDay(airbillPausingDeductVo.getStartDate(), orgAdjustmentVo.getStartPausingDate(), new Date(), airbillPausingDeductVo.getPausingDay()));
                                    pausingDeductDao.updatePausingDay(context, orgAdjustmentVo);
                                }
                                break;
                        }
                        break;
                    case 2:
                        switch (adjustment.getStatus()) {
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                                if (adjustment.getCreditType() != 2 && checkStartAdjustmentDate(orgAdjustmentVo, airbillPausingDeductVo)) {
                                    orgAdjustmentVo.setPausingDay(plusPausingDay(airbillPausingDeductVo.getStartDate(), orgAdjustmentVo.getStartPausingDate(), new Date(), airbillPausingDeductVo.getPausingDay()));
                                    pausingDeductDao.updatePausingDay(context, orgAdjustmentVo);
                                }
                                break;
                        }
                        break;
                    case 3:
                        if (adjustment.getStatus() == 2 && adjustment.getCreditType() == 2) {
                            adjustment.setStartPausingDate(Calendar.getInstance().getTime());
                        }
                        break;
                }
            }
            // End check

            // Update adjustment
            adjustmentDao.update(context, adjustment);
            if (adjustment.getStatus() != null && adjustment.getStatus() == 6) {
                // Get credit note detail by adjustment id
                CreditNoteDetailVo creditNoteDetailVo = creditNoteDetailDao.selectByAdjustmentId(adjustmentId);

                // Delete credit note detail
                creditNoteDetailDao.deleteCreditDetailByAdjustmentId(context, adjustmentId);

                // Delete credit note if has no credit note detail
                if (creditNoteDetailVo != null && creditNoteDetailDao.countByCreditNoteId(creditNoteDetailVo.getCreditNoteId()) == 0) {
                    creditNoteDao.deleteById(context, creditNoteDetailVo.getCreditNoteId());
                }
            }

            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Log database action error
            logger.error(e);

            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }
    }

    private long plusPausingDay(Date minDate, Date startPausingDate, Date aprovalDate, Long currentPausingDay) {
        if (currentPausingDay == null || minDate == null || startPausingDate == null) {
            return 0L;
        }
        Date currentPausingDate = org.apache.commons.lang.time.DateUtils.addDays(minDate, currentPausingDay.intValue());

        if (DateUtils.dateDiff(Type.BY_DAY, startPausingDate, currentPausingDate) >= 0) {
            if (DateUtils.dateDiff(Type.BY_DAY, aprovalDate, currentPausingDate) >= 0) {
                return 0;
            } else {
                return DateUtils.dateDiff(Type.BY_DAY, currentPausingDate, aprovalDate);
            }
        } else {
            return DateUtils.dateDiff(Type.BY_DAY, startPausingDate, aprovalDate);
        }
    }

    private boolean checkStartAdjustmentDate(AirbillAdjustmentVo adjustmentVo, AirbillPausingDeductVo airbillPausingDeductVo) throws Exception {
        Date startPausingDate = adjustmentVo.getStartPausingDate();
        InvoiceDao invoiceDao = new InvoiceDao();
        InvoiceVo invoiceVo = invoiceDao.getInvoiceByAirbill(adjustmentVo);
        Calendar cal = Calendar.getInstance();
        cal.setTime(invoiceVo.getInvoiceDate());
        cal.add(Calendar.DAY_OF_YEAR, airbillPausingDeductVo.getPausingDay().intValue() + 60);
        Date fromDate = invoiceVo.getInvoiceDate();
        Date toDate = cal.getTime();
        if (startPausingDate.before(fromDate) || startPausingDate.after(toDate)) {
            return false;
        }
        return true;
    }
}
