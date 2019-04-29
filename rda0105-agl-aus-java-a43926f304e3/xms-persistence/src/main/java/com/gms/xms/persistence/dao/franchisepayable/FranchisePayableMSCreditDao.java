package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayableMSCreditVo;

import java.util.List;

/**
 * Posted from FranchisePayableMSCreditDao
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableMSCreditDao extends BaseDao<FranchisePayableMSCreditVo> {
    // Franchise Payable - Carrier credit details (Margin Share)

    public List<FranchisePayableMSCreditVo> getCarrierCreditDetails(FranchisePayableFilter filter) throws DaoException {
        return selectList(filter, "FranchisePayableMSCredit.getCarrierCreditDetails");
    }

    public FranchisePayableMSCreditVo getCarrierCreditDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableMSCredit.getCarrierCreditDetailsTotal");
    }

    public Integer getCarrierCreditDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "FranchisePayableMSCredit.getCarrierCreditDetailsRecordCount");
    }
}
