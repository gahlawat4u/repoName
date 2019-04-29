package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayablePeriodVo;
import com.gms.xms.txndb.vo.FranchisePayableRptTxnIdVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from FranchisePayableTaskDao
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableTaskDaoService {

    private static final Log log = LogFactory.getLog(FranchisePayableTaskDaoService.class);

    private FranchisePayableTaskDao payableTaskDao;
    private FranchisePayableRptTxnIdDao payableRptTxnIdDao;
    private FranchisePayablePeriodDao payablePeriodDao;

    // Prepare data for franchise payable report.
    public void prepareDataForReportFromLive(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        // SqlSessionClient sessionClient = new SqlSessionClient();
        // sessionClient.startTransaction();

        payableTaskDao = new FranchisePayableTaskDao();
        payableRptTxnIdDao = new FranchisePayableRptTxnIdDao();
        try {
            // Insert report transaction id into the tmp_xms_tbl_rpt_txn_id
            FranchisePayableRptTxnIdVo rptTxnIdVo = new FranchisePayableRptTxnIdVo();
            rptTxnIdVo.setRptTxnId(filter.getRptTxnId());
            rptTxnIdVo.setDescription("Franchise Payable Report from " + filter.getStartDate() + " to " + filter.getEndDate());

            long beginStep = System.currentTimeMillis();
            long startElapsed = beginStep;
            payableRptTxnIdDao.saveRptTxnId(context, rptTxnIdVo);
            log.info("prepare rpt_txn_id:" + rptTxnIdVo.getRptTxnId() + "|ElaspedTime: " + (System.currentTimeMillis() - beginStep));
            // Prepare data for Payment Margin Details and 61+ Days Payment
            // Credit Details.
            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareDataForMarginAnd61Days(context, filter);
            log.info("prepareDataForMarginAnd61Days| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareDataForMargin(context, filter);
            log.info("prepareDataForMargin| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareDataForMargin61(context, filter);
            log.info("prepareDataForMargin61| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

            // Prepare data for Carrier Cost Deduction.
            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareDataForDeduct(context, filter);
            log.info("prepareDataForDeduct| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

            // Prepare data for Non Centralized Details.
            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareDataForNonCentral(context, filter);
            log.info("prepareDataForNonCentral| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

            // Prepare data for Carrier Credit Details.
            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareDataForCredit(context, filter);
            log.info("prepareDataForCredit| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

            // Prepare data for Over payment.
            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareDataForOverpayment(context, filter);
            log.info("prepareDataForCredit| ElaspedTime: " + (System.currentTimeMillis() - beginStep));
            log.info("prepareDataForReportFromLive| ElaspedTime: " + (System.currentTimeMillis() - startElapsed));
            // sessionClient.endTransaction();
        } catch (DaoException ex) {
            log.error(ex);
            // sessionClient.rollback();
            throw ex;
        }
    }

    public void prepareFranchisePayableDataV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        payableTaskDao = new FranchisePayableTaskDao(sessionClient);
        try {
            // Prepare data for Payment Margin Details.
            payableTaskDao.prepareDataForMSMarginV2(context, filter);

            // Prepare data for 61+ days payment.
            payableTaskDao.prepareDataFor61DaysV2(context, filter);

            // Prepare data for Carrier Cost Deduction.
            payableTaskDao.prepareDataForDeductV2(context, filter);

            // Prepare data for Non Centralized Details.
            payableTaskDao.prepareDataForNonCentralV2(context, filter);

            // Prepare data for Carrier Credit Details.
            payableTaskDao.prepareDataForCreditV2(context, filter);

            // Prepare data for Over payment.
            payableTaskDao.prepareDataForOverpaymentV2(context, filter);

            sessionClient.endTransaction();
        } catch (DaoException ex) {
            log.error(ex);
            sessionClient.rollback();
            throw ex;
        }
    }

    // Prepare data for franchise payable report from the frozen tables.
    public void prepareDataForReportFromFrozen(Map<String, String> context, String rptTxnId) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        payableTaskDao = new FranchisePayableTaskDao(sessionClient);
        payableRptTxnIdDao = new FranchisePayableRptTxnIdDao(sessionClient);
        try {
            // Insert report transaction id into the tmp_xms_tbl_rpt_txn_id
            FranchisePayableRptTxnIdVo rptTxnIdVo = new FranchisePayableRptTxnIdVo();
            rptTxnIdVo.setRptTxnId(rptTxnId);
            rptTxnIdVo.setDescription("FROZEN: Franchise Payable Report");
            payableRptTxnIdDao.saveRptTxnId(context, rptTxnIdVo);

            // Copy margin report data
            payableTaskDao.copyFrozenMarginByRptTxnIdV2(context, rptTxnId);

            // Copy 61 days report data
            payableTaskDao.copyFrozen61DaysByRptTxnIdV2(context, rptTxnId);

            // Copy credit report data
            payableTaskDao.copyFrozenCreditByRptTxnIdV2(context, rptTxnId);

            // Copy deduct report data
            payableTaskDao.copyFrozenDeductByRptTxnIdV2(context, rptTxnId);

            // Copy non central report data
            payableTaskDao.copyFrozenNonCentralByRptTxnIdV2(context, rptTxnId);

            // Copy over payment report data
            payableTaskDao.copyFrozenOverpaymentByRptTxnIdV2(context, rptTxnId);

            sessionClient.endTransaction();
        } catch (DaoException ex) {
            log.error(ex);
            sessionClient.rollback();
            throw ex;
        }
    }

    // Freeze report by date range
    public void freezeReportByDateRange(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        payableTaskDao = new FranchisePayableTaskDao(sessionClient);
        payablePeriodDao = new FranchisePayablePeriodDao(sessionClient);
        try {
            String rptTxnId = filter.getRptTxnId();

            // Copy margin report data v2
            payableTaskDao.copyMarginToFrozenV2(context, rptTxnId);

            // Copy 61 days report data v2
            payableTaskDao.copy61DaysToFrozenV2(context, rptTxnId);

            // Copy credit report data v2
            payableTaskDao.copyCreditToFrozenV2(context, rptTxnId);

            // Copy deduct report data v2
            payableTaskDao.copyDeductToFrozenV2(context, rptTxnId);

            // Copy non central report data v2
            payableTaskDao.copyNonCentralToFrozenV2(context, rptTxnId);

            // Copy over payment report data v2
            payableTaskDao.copyOverpaymentToFrozenV2(context, rptTxnId);

            // Get xms_tbl_period record
            FranchisePayablePeriodVo periodVo = payablePeriodDao.getFranchisePayablePeriodByDateRange(filter);

            // Update record from xms_tbl_period
            periodVo.setRptTxnId(rptTxnId);
            periodVo.setFranchisePayableStatus(1);
            payablePeriodDao.updateFranchisePayablePeriod(context, periodVo);

            sessionClient.endTransaction();
        } catch (DaoException e) {
            log.error(e);
            sessionClient.rollback();
            throw e;
        }
    }

    // Reconcile invoice payment air bill number level.
    public void reconcileInvoicePaymentAwbLevel(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        payableTaskDao = new FranchisePayableTaskDao();
        payableTaskDao.reconcileInvoicePaymentAwbLevel(context, filter);
    }

    // Reconcile franchise tax amount.
    public void reconcileFranchiseTaxAmount(Map<String, String> context) throws DaoException {
        payableTaskDao = new FranchisePayableTaskDao();
        payableTaskDao.reconcileFranchiseTaxAmount(context);
    }
}
