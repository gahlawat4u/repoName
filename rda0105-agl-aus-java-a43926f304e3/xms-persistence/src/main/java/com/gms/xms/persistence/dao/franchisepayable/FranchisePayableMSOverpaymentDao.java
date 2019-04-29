package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayableMSOverpaymentVo;

import java.util.List;

/**
 * Posted from FranchisePayableMSOverpaymentDao
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableMSOverpaymentDao extends BaseDao<FranchisePayableMSOverpaymentVo> {
    // Franchise Payable - Over payment (Margin Share)

    public List<FranchisePayableMSOverpaymentVo> getOverpayment(FranchisePayableFilter filter) throws DaoException {
        return selectList(filter, "FranchisePayableMSOverpayment.getOverpayment");
    }

    public FranchisePayableMSOverpaymentVo getOverpaymentTotal(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableMSOverpayment.getOverpaymentTotal");
    }

    public Integer getOverpaymentRecordCount(FranchisePayableFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "FranchisePayableMSOverpayment.getOverpaymentRecordCount");
    }
}
