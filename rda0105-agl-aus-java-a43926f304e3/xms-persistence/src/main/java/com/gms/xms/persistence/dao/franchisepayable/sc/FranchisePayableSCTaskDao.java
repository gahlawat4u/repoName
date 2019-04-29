package com.gms.xms.persistence.dao.franchisepayable.sc;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;

import java.util.Map;

/**
 * Posted from FranchisePayableSCTaskDao
 * <p>
 * Author DatTV Oct 29, 2015
 */
public class FranchisePayableSCTaskDao extends BaseDao<Object> {

    public FranchisePayableSCTaskDao() {
        super();
    }

    public FranchisePayableSCTaskDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    // Reconcile tasks
    public void reconcileInvoicePaymentAwbLevel(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        update(context, filter, "FranchisePayableSCTask.reconcileInvoicePaymentAwbLevel");
    }

    public void reconcileFranchiseTaxAmount(Map<String, String> context) throws DaoException {
        update(context, null, "FranchisePayableSCTask.reconcileFranchiseTaxAmount");
    }

    // Prepare data for live report
    public void prepareDataForMarginAnd61Days(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForMarginAnd61Days");
    }

    public void prepareDataForMargin(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForMargin");
    }

    public void prepareDataForMargin61(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForMargin61");
    }

    public void prepareDataForDeduct(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForDeduct");
    }

    public void prepareDataForNonCentral(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForNonCentral");
    }

    public void prepareDataForCredit(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForCredit");
    }

    public void prepareDataForOverpayment(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForOverpayment");
    }

    public void prepareShipmentRawData(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareShipmentRawData");
    }

    public void prepareShipmentDetailData(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareShipmentDetailData");
    }

    public void prepareTechnologyFeeDetailData(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareTechnologyFeeDetailData");
    }

    public void prepareSCOverviewData(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareSCOverviewData");
    }

    public void prepareSCOverviewDataForFranchise(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareSCOverviewDataForFranchise");
    }
    // Save live data to frozen report

    public void prepareDataForMSMarginV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForMSMarginV2");
    }

    public void prepareDataFor61DaysV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataFor61DaysV2");
    }

    public void prepareDataForCreditV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForCreditV2");
    }

    public void prepareDataForDeductV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForDeductV2");
    }

    public void prepareDataForNonCentralV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForNonCentralV2");
    }

    public void prepareDataForOverpaymentV2(Map<String, String> context, FranchisePayableFilter filter) throws DaoException {
        insert(context, filter, "FranchisePayableSCTask.prepareDataForOverpaymentV2");
    }

    // COPY LIVE TO FROZEN V2
    public void copy61DaysToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copy61DaysToFrozenV2");
    }

    public void copyMarginToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyMarginToFrozenV2");
    }

    public void copyCreditToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyCreditToFrozenV2");
    }

    public void copyDeductToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyDeductToFrozenV2");
    }

    public void copyNonCentralToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyNonCentralToFrozenV2");
    }

    public void copyOverpaymentToFrozenV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyOverpaymentToFrozenV2");
    }

    public void copyShipmentDetailToFrozen(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyShipmentDetailToFrozen");
    }

    public void copyTechFeeDetailToFrozen(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyTechFeeDetailToFrozen");
    }

    public void copySCOverviewToFrozen(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copySCOverviewToFrozen");
    }
    // END OF COPY LIVE TO FROZEN V2

    // COPY FROM FROZEN TO LIVE
    public void copyFrozenMarginByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyFrozenMarginByRptTxnIdV2");
    }

    public void copyFrozen61DaysByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyFrozen61DaysByRptTxnIdV2");
    }

    public void copyFrozenCreditByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyFrozenCreditByRptTxnIdV2");
    }

    public void copyFrozenDeductByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyFrozenDeductByRptTxnIdV2");
    }

    public void copyFrozenNonCentralByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyFrozenNonCentralByRptTxnIdV2");
    }

    public void copyFrozenOverpaymentByRptTxnIdV2(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyFrozenOverpaymentByRptTxnIdV2");
    }

    public void copyShipmentDetailFromFrozen(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyShipmentDetailFromFrozen");
    }

    public void copyTechFeeDetailFromFrozen(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copyTechFeeDetailFromFrozen");
    }

    public void copySCOverviewFromFrozen(Map<String, String> context, String rptTxnId) throws DaoException {
        insert(context, rptTxnId, "FranchisePayableSCTask.copySCOverviewFromFrozen");
    }
    // END OF COPY FROM FROZEN TO LIVE
}
