package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.SystemStatsListFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsLoginLogFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.LoginLogVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from LoginLogDao
 * <p>
 * Author TANDT
 */
public class LoginLogDao extends BaseDao<LoginLogVo> {
    public List<LoginLogVo> selectByFilter(SystemStatsListFilter filter) throws DaoException {
        return this.selectList(filter, "LoginLog.selectByFilter");
    }

    public Long selectByFilterCount(SystemStatsListFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "LoginLog.selectByFilterCount");
    }

    public void insertLog(Map<String, String> context, LoginLogVo loginLogVo) throws DaoException {
        insert(context, loginLogVo, "LoginLog.insertLog");
    }

    public List<LoginLogVo> getLoginLogs(SysStatsLoginLogFilter filter) throws DaoException {
        return selectList(filter, "LoginLog.getLoginLogs");
    }

    public long countLoginLogs(SysStatsLoginLogFilter filter) throws DaoException {
        return (long) selectObject(filter, "LoginLog.countLoginLogs");
    }
}
