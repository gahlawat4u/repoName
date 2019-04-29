package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.SystemStatsListFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsWebshipLogFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.WebshipLogVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AdminEmailDao
 * <p>
 * Author TANDT
 */
public class WebshipLogDao extends BaseDao<WebshipLogVo> {

    public List<WebshipLogVo> selectWebshipLogByFilter(SystemStatsListFilter filter) throws DaoException {
        return this.selectList(filter, "WebshipLog.selectWebshipLogByFilter");
    }

    public Long selectWebshipLogCountByFilter(SystemStatsListFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "WebshipLog.selectWebshipLogCountByFilter");
    }

    public void insert(Map<String, String> context, WebshipLogVo webshipLogVo) throws DaoException {
        insert(context, webshipLogVo, "WebshipLog.insert");
    }

    public List<WebshipLogVo> getWebshipLogs(SysStatsWebshipLogFilter filter) throws DaoException {
        return selectList(filter, "WebshipLog.getWebshipLogs");
    }

    public long countWebshipLogs(SysStatsWebshipLogFilter filter) throws DaoException {
        return (long) selectObject(filter, "WebshipLog.countWebshipLogs");
    }
}
