package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsAdjustmentLogFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.systemstats.IdsVo;
import com.gms.xms.txndb.vo.admin.systemstats.RecentImportVo;
import com.gms.xms.txndb.vo.admin.systemstats.SysStatAdjustmentLogVo;

import java.util.List;

/**
 * Posted from Aug 30, 2016 11:18:54 AM
 * <p>
 * Author dattrinh
 */
public class SystemStatsDao extends BaseDao<Object> {
    public List<RecentImportVo> getRecentImports(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "SystemStats.getRecentImports");
    }

    public long countRecentImports(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(filter, "SystemStats.countRecentImports");
    }

    public List<IdsVo> getFilterCollections(String airbillNumber) throws DaoException {
        return selectList(airbillNumber, "SystemStats.getFilterCollections");
    }

    public List<SysStatAdjustmentLogVo> getAdjustmentLogs(SysStatsAdjustmentLogFilter filter) throws DaoException {
        return selectList(filter, "SystemStats.getAdjustmentLogs");
    }

    public long countAdjustmentLogs(SysStatsAdjustmentLogFilter filter) throws DaoException {
        return (long) selectObject(filter, "SystemStats.countAdjustmentLogs");
    }
}