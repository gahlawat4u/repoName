package com.gms.xms.persistence.dao.franchisepayable.sc;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayablePeriodDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayableRptTxnIdDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayablePeriodVo;
import com.gms.xms.txndb.vo.FranchisePayableRptTxnIdVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from FranchisePayableTaskSCDaoService
 * <p>
 * Author DatTV Oct 29, 2015
 */
public class FranchisePayableSCTaskDaoService {

    private static final Log log = LogFactory.getLog(FranchisePayableSCTaskDaoService.class);

    private FranchisePayableSCTaskDao payableTaskDao;
    private FranchisePayableRptTxnIdDao payableRptTxnIdDao;
    private FranchisePayablePeriodDao payablePeriodDao;

    // Prepare data for franchise receivable report.
    public void prepareDataForReportFromLive(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        // SqlSessionClient sessionClient = new SqlSessionClient();
        // sessionClient.startTransaction();

        payableTaskDao = new FranchisePayableSCTaskDao();
        payableRptTxnIdDao = new FranchisePayableRptTxnIdDao();
        try {
            // Insert report transaction id into the tmp_xms_tbl_rpt_txn_id
            FranchisePayableRptTxnIdVo rptTxnIdVo = new FranchisePayableRptTxnIdVo();
            rptTxnIdVo.setRptTxnId(filter.getRptTxnId());
            rptTxnIdVo.setDescription("Franchise Receivable Report from " + filter.getStartDate() + " to " + filter.getEndDate());

            long beginStep = System.currentTimeMillis();
            long startElapsed = beginStep;
            payableRptTxnIdDao.saveRptTxnId(context, rptTxnIdVo);
            log.info("prepare rpt_txn_id:" + rptTxnIdVo.getRptTxnId() + "|ElaspedTime: " + (System.currentTimeMillis() - beginStep));
            // Prepare data for Payment Margin Details and 61+ Days Payment
            // Credit Details.
            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareDataForMarginAnd61Days(context, filter);
            log.info("prepareDataForMarginAnd61Days| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

            // Prepare data for Carrier Credit Details.
            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareDataForCredit(context, filter);
            log.info("prepareDataForCredit| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

            // Prepare data for Over payment.
            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareDataForOverpayment(context, filter);
            log.info("prepareDataForOverpayment| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

            // Prepare data for Shipment Raw.
            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareShipmentRawData(context, filter);
            log.info("prepareDataForShipmentRaw| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

            // Prepare data for Shipment Detail.
            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareShipmentDetailData(context, filter);
            log.info("prepareDataForShipmentDetail| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

            // Prepare data for Technology Fee Detail.
            beginStep = System.currentTimeMillis();
            payableTaskDao.prepareTechnologyFeeDetailData(context, filter);
            log.info("prepareDataForTechnologyFeeDetail| ElaspedTime: " + (System.currentTimeMillis() - beginStep));

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

        payableTaskDao = new FranchisePayableSCTaskDao(sessionClient);
        try {
            // Prepare data for Carrier Credit Details.
            payableTaskDao.prepareDataForCreditV2(context, filter);

            // Prepare data for Over payment.
            payableTaskDao.prepareDataForOverpaymentV2(context, filter);

            // Prepare data for sc overview.
            for (String franchiseCode : filter.getFranchiseCodeList()) {
                FranchisePayableFilter newFilter = new FranchisePayableFilter();
                newFilter.setRptTxnId(filter.getRptTxnId());
                newFilter.setStartDate(filter.getStartDate());
                newFilter.setEndDate(filter.getEndDate());
                newFilter.setFranchiseCode(franchiseCode);
                payableTaskDao.prepareSCOverviewDataForFranchise(context, newFilter);
            }

            sessionClient.endTransaction();
        } catch (DaoException ex) {
            log.error(ex);
            sessionClient.rollback();
            throw ex;
        }
    }

    // Prepare data for franchise receivable report from the frozen tables.
    public void prepareDataForReportFromFrozen(Map<String, String> context, String rptTxnId) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        payableTaskDao = new FranchisePayableSCTaskDao(sessionClient);
        payableRptTxnIdDao = new FranchisePayableRptTxnIdDao(sessionClient);
        try {
            // Insert report transaction id into the tmp_xms_tbl_rpt_txn_id
            FranchisePayableRptTxnIdVo rptTxnIdVo = new FranchisePayableRptTxnIdVo();
            rptTxnIdVo.setRptTxnId(rptTxnId);
            rptTxnIdVo.setDescription("FROZEN: Franchise Receivable Report");
            payableRptTxnIdDao.saveRptTxnId(context, rptTxnIdVo);

            // Copy credit report data
            payableTaskDao.copyFrozenCreditByRptTxnIdV2(context, rptTxnId);

            // Copy over payment report data
            payableTaskDao.copyFrozenOverpaymentByRptTxnIdV2(context, rptTxnId);

            // Copy shipment detail report data
            payableTaskDao.copyShipmentDetailFromFrozen(context, rptTxnId);

            // Copy technology fee detail data
            payableTaskDao.copyTechFeeDetailFromFrozen(context, rptTxnId);

            // Copy sc overview data
            payableTaskDao.copySCOverviewFromFrozen(context, rptTxnId);

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

        payableTaskDao = new FranchisePayableSCTaskDao(sessionClient);
        payablePeriodDao = new FranchisePayablePeriodDao(sessionClient);
        try {
            String rptTxnId = filter.getRptTxnId();

            // Copy credit report data v2
            payableTaskDao.copyCreditToFrozenV2(context, rptTxnId);

            // Copy over payment report data v2
            payableTaskDao.copyOverpaymentToFrozenV2(context, rptTxnId);

            // Copy shipment detail report data
            payableTaskDao.copyShipmentDetailToFrozen(context, rptTxnId);

            // Copy technology fee report data
            payableTaskDao.copyTechFeeDetailToFrozen(context, rptTxnId);

            // Copy sc overview report data
            payableTaskDao.copySCOverviewToFrozen(context, rptTxnId);

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
        payableTaskDao = new FranchisePayableSCTaskDao();
        payableTaskDao.reconcileInvoicePaymentAwbLevel(context, filter);
    }

    // Reconcile franchise tax amount.
    public void reconcileFranchiseTaxAmount(Map<String, String> context) throws DaoException {
        payableTaskDao = new FranchisePayableSCTaskDao();
        payableTaskDao.reconcileFranchiseTaxAmount(context);
    }
}
