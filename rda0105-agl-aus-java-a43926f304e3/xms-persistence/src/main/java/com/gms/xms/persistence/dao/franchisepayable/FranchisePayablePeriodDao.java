package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayablePeriodVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from FranchisePayablePeriodDao
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayablePeriodDao extends BaseDao<FranchisePayablePeriodVo> {

    public FranchisePayablePeriodDao() {
        super();
    }

    public FranchisePayablePeriodDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    // Get franchise payable period by date range
    public FranchisePayablePeriodVo getFranchisePayablePeriodByDateRange(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayablePeriod.selectByDateRange");
    }

    // Update franchise payable period
    public void updateFranchisePayablePeriod(Map<String, String> context, FranchisePayablePeriodVo period) throws DaoException {
        update(context, period, "FranchisePayablePeriod.update");
    }

    // Get list of unfrozen franchise payable report
    public List<FranchisePayablePeriodVo> getUnfrozenFranchisePayableReport() throws DaoException {
        return selectList(null, "FranchisePayablePeriod.selectUnfrozen");
    }

    // Get list of frozen franchise payable report
    public List<FranchisePayablePeriodVo> getFrozenFranchisePayableReport() throws DaoException {
        return selectList(null, "FranchisePayablePeriod.selectFrozen");
    }
}
