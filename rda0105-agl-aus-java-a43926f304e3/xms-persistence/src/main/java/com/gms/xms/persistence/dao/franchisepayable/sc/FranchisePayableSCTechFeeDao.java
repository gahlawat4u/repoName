package com.gms.xms.persistence.dao.franchisepayable.sc;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCTechFeeTotalVo;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCTechFeeVo;

import java.util.List;

/**
 * Posted from FranchisePayableSCTechFeeDao
 * <p>
 * Author DatTV Oct 30, 2015
 */
public class FranchisePayableSCTechFeeDao extends BaseDao<FranchisePayableSCTechFeeVo> {
    // Franchise Payable - Tech Fee details (Service Charge)

    public List<FranchisePayableSCTechFeeVo> getTechFeeDetails(FranchisePayableFilter filter) throws DaoException {
        return selectList(filter, "FranchisePayableSCTechFee.getTechFeeDetails");
    }

    public FranchisePayableSCTechFeeTotalVo getTechFeeDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        return (FranchisePayableSCTechFeeTotalVo) selectObject(filter, "FranchisePayableSCTechFee.getTechFeeDetailsTotal");
    }

    public Long getTechFeeDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        return (Long) selectObject(filter, "FranchisePayableSCTechFee.getTechFeeDetailsRecordCount");
    }
}
