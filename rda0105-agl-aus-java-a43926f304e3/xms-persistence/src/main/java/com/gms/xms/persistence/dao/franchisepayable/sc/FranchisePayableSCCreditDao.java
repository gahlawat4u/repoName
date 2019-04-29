package com.gms.xms.persistence.dao.franchisepayable.sc;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCCreditTotalVo;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCCreditVo;

import java.util.List;

/**
 * Posted from FranchisePayableSCCreditDao
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableSCCreditDao extends BaseDao<FranchisePayableSCCreditVo> {
    // Franchise Payable - Carrier credit details (Service Charge)

    public List<FranchisePayableSCCreditVo> getCarrierCreditDetails(FranchisePayableFilter filter) throws DaoException {
        return selectList(filter, "FranchisePayableSCCredit.getCarrierCreditDetails");
    }

    public FranchisePayableSCCreditTotalVo getCarrierCreditDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        return (FranchisePayableSCCreditTotalVo) selectObject(filter, "FranchisePayableSCCredit.getCarrierCreditDetailsTotal");
    }

    public FranchisePayableSCCreditVo getTaxableCarrierCreditDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableSCCredit.getTaxableCarrierCreditDetailsTotal");
    }

    public FranchisePayableSCCreditVo getNonTaxableCarrierCreditDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableSCCredit.getNonTaxableCarrierCreditDetailsTotal");
    }

    public Integer getCarrierCreditDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "FranchisePayableSCCredit.getCarrierCreditDetailsRecordCount");
    }
}
