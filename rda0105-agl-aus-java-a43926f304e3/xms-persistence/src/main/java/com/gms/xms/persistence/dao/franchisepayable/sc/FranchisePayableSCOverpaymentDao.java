package com.gms.xms.persistence.dao.franchisepayable.sc;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCOverpaymentVo;

import java.util.List;

/**
 * Posted from FranchisePayableSCOverpaymentDao
 * <p>
 * Author DatTV Oct 29, 2015
 */
public class FranchisePayableSCOverpaymentDao extends BaseDao<FranchisePayableSCOverpaymentVo> {
    // Franchise Payable - Over payment (Service Charge)

    public List<FranchisePayableSCOverpaymentVo> getOverpayment(FranchisePayableFilter filter) throws DaoException {
        return selectList(filter, "FranchisePayableSCOverpayment.getOverpayment");
    }

    public FranchisePayableSCOverpaymentVo getOverpaymentTotal(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableSCOverpayment.getOverpaymentTotal");
    }

    public Integer getOverpaymentRecordCount(FranchisePayableFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "FranchisePayableSCOverpayment.getOverpaymentRecordCount");
    }
}
