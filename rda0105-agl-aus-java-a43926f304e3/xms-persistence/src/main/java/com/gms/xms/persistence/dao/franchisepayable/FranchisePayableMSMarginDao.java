package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayableMSMarginVo;

import java.util.List;

/**
 * Posted from FranchisePayableMSMarginDao
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableMSMarginDao extends BaseDao<FranchisePayableMSMarginVo> {
    // Franchise Payable - Payment Margin Details (Margin Share)

    public List<FranchisePayableMSMarginVo> getPaymentMarginDetails(FranchisePayableFilter filter) throws DaoException {
        return selectList(filter, "FranchisePayableMSMargin.getPaymentMarginDetails");
    }

    public FranchisePayableMSMarginVo getPaymentMarginDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableMSMargin.getPaymentMarginDetailsTotal");
    }

    public Integer getPaymentMarginDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "FranchisePayableMSMargin.getPaymentMarginDetailsRecordCount");
    }
}
