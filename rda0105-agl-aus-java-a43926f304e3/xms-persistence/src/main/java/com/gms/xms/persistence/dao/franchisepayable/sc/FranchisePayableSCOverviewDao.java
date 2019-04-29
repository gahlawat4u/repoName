package com.gms.xms.persistence.dao.franchisepayable.sc;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCOverviewVo;

import java.util.Map;

/**
 * Posted from FranchisePayableSCOverviewDao
 * <p>
 * Author DatTV Oct 30, 2015
 */
public class FranchisePayableSCOverviewDao extends BaseDao<FranchisePayableSCOverviewVo> {
    public FranchisePayableSCOverviewVo getOverview(FranchisePayableFilter filter) throws DaoException {
        return this.select(filter, "FranchisePayableSCOverview.getOverview");
    }

    public FranchisePayableSCOverviewVo getManagementFeeOnCredit(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableSCOverview.getManagementFeeOnCredit");
    }

    public void updateManagementFeeOnCredit(Map<String, String> context, FranchisePayableSCOverviewVo overviewVo) throws DaoException {
        update(context, overviewVo, "FranchisePayableSCOverview.updateManagementFeeOnCredit");
    }
}
