package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayableMSDeductVo;

import java.util.List;

/**
 * Posted from FranchisePayableMSDeductDao
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableMSDeductDao extends BaseDao<FranchisePayableMSDeductVo> {
    // Franchise Payable - Carrier Cost Deduction (Margin Share)

    public List<FranchisePayableMSDeductVo> getCarrierCostDeduction(FranchisePayableFilter filter) throws DaoException {
        return selectList(filter, "FranchisePayableMSDeduct.getCarrierCostDeduction");
    }

    public FranchisePayableMSDeductVo getCarrierCostDeductionTotal(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableMSDeduct.getCarrierCostDeductionTotal");
    }

    public Integer getCarrierCostDeductionRecordCount(FranchisePayableFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "FranchisePayableMSDeduct.getCarrierCostDeductionRecordCount");
    }
}
