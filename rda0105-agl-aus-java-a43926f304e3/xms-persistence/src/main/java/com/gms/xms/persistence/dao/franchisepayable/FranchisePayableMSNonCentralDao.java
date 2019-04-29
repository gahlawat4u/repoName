package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayableMSNonCentralVo;

import java.util.List;

/**
 * Posted from FranchisePayableMSNonCentralMapper
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableMSNonCentralDao extends BaseDao<FranchisePayableMSNonCentralVo> {
    // Franchise Payable - Non Centralized Details (Margin Share)

    public List<FranchisePayableMSNonCentralVo> getNonCentralizedDetails(FranchisePayableFilter filter) throws DaoException {
        return selectList(filter, "FranchisePayableMSNonCentral.getNonCentralizedDetails");
    }

    public FranchisePayableMSNonCentralVo getNonCentralizedDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableMSNonCentral.getNonCentralizedDetailsTotal");
    }

    public Integer getNonCentralizedDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "FranchisePayableMSNonCentral.getNonCentralizedDetailsRecordCount");
    }
}
