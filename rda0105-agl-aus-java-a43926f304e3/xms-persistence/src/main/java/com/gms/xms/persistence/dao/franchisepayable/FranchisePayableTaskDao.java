package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;

import java.util.Map;

/**
 * Posted from FranchisePayableTaskDao
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableTaskDao extends BaseDao<Object> {

    public FranchisePayableTaskDao() {
        super();
    }

    public FranchisePayableTaskDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    // Reconcile tasks
    public void reconcileInvoicePaymentAwbLevel(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        update(context, filter, "FranchisePayableTask.reconcileInvoicePaymentAwbLevel");
    }

    public void reconcileFranchiseTaxAmount(Map<String, String> context) throws DaoException {
        update(context, null, "FranchisePayableTask.reconcileFranchiseTaxAmount");
    }

    // Prepare data for live report
    public void prepareDataForMarginAnd61Days(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForMarginAnd61Days");
    }

    public void prepareDataForMargin(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForMargin");
    }

    public void prepareDataForMargin61(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForMargin61");
    }

    public void prepareDataForDeduct(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForDeduct");
    }

    public void prepareDataForNonCentral(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForNonCentral");
    }

    public void prepareDataForCredit(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForCredit");
    }

    public void prepareDataForOverpayment(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForOverpayment");
    }

    // Save live data to frozen report

    public void prepareDataForMSMarginV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForMSMarginV2");
    }

    public void prepareDataFor61DaysV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataFor61DaysV2");
    }

    public void prepareDataForCreditV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForCreditV2");
    }

    public void prepareDataForDeductV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForDeductV2");
    }

    public void prepareDataForNonCentralV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForNonCentralV2");
    }

    public void prepareDataForOverpaymentV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableTask.prepareDataForOverpaymentV2");
    }

    // COPY LIVE TO FROZEN V2
    public void copy61DaysToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copy61DaysToFrozenV2");

    }

    public void copyMarginToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copyMarginToFrozenV2");
    }

    public void copyCreditToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copyCreditToFrozenV2");
    }

    public void copyDeductToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copyDeductToFrozenV2");
    }

    public void copyNonCentralToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copyNonCentralToFrozenV2");
    }

    public void copyOverpaymentToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copyOverpaymentToFrozenV2");
    }

    // END OF COPY LIVE TO FROZEN V2

    // COPY FROM FROZEN TO LIVE
    public void copyFrozenMarginByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copyFrozenMarginByRptTxnIdV2");
    }

    public void copyFrozen61DaysByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copyFrozen61DaysByRptTxnIdV2");
    }

    public void copyFrozenCreditByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copyFrozenCreditByRptTxnIdV2");
    }

    public void copyFrozenDeductByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copyFrozenDeductByRptTxnIdV2");
    }

    public void copyFrozenNonCentralByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copyFrozenNonCentralByRptTxnIdV2");
    }

    public void copyFrozenOverpaymentByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableTask.copyFrozenOverpaymentByRptTxnIdV2");
    }
    // END OF COPY FROM FROZEN TO LIVE
}
