package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayableMS61DaysVo;

import java.util.List;

/**
 * Posted from FranchisePayableMS61DaysDao
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableMS61DaysDao extends BaseDao<FranchisePayableMS61DaysVo> {
    public List<FranchisePayableMS61DaysVo> get61DaysPaymentCreditDetails(FranchisePayableFilter filter) throws DaoException {
        return selectList(filter, "FranchisePayableMS61Days.get61DaysPaymentCreditDetails");
    }

    public FranchisePayableMS61DaysVo get61DaysPaymentCreditDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableMS61Days.get61DaysPaymentCreditDetailsTotal");
    }

    public Integer get61DaysPaymentCreditDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "FranchisePayableMS61Days.get61DaysPaymentCreditDetailsRecordCount");
    }
}
