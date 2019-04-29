package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.AirbillChangeLogFilter;
import com.gms.xms.filter.admin.EventLogFilter;
import com.gms.xms.filter.eventlog.CustomerEventLogFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.EventLogVo;

import java.util.List;

/**
 * Posted from AdminEmailDao
 * <p>
 * Author TANDT
 */
public class EventLogDao extends BaseDao<EventLogVo> {
    public EventLogDao() {
        super();
    }

    public EventLogDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<EventLogVo> selectListByFilter(EventLogFilter filter) throws DaoException {
        return this.selectList(filter, "EventLog.selectListByFilter");
    }

    public Long selectCountListByFilter(EventLogFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "EventLog.selectCountListByFilter");
    }

    public List<EventLogVo> selectByCustCode(CustomerEventLogFilter filter) throws DaoException {
        return this.selectList(filter, "EventLog.selectByCustCode");
    }

    public long countByCustCode(CustomerEventLogFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "EventLog.countByCustCode");
    }

    public List<EventLogVo> selectByProfileId(CustomerEventLogFilter filter) throws DaoException {
        return this.selectList(filter, "EventLog.selectByProfileId");
    }

    public long selectByProfileIdCount(CustomerEventLogFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "EventLog.selectByProfileIdCount");
    }

    public EventLogVo selectInitValueShipmentBilling(EventLogFilter filter) throws DaoException {
        return (EventLogVo) selectObject(filter, "EventLog.selectInitValueShipmentBilling");
    }

    /*
     * public long insert(Map<String, String> context, EventLogVo eventLogVo) throws DaoException { return insert(context, eventLogVo, "EventLog.insert"); }
     */
    @SuppressWarnings("rawtypes")
    public List selectColumns(String str) throws DaoException {
        return (List) this.selectListObject(str, "EventLog.selectColumns");
    }

    public List<EventLogVo> getAirbillChangeLog(AirbillChangeLogFilter filter) throws DaoException {
        return selectList(filter, "EventLog.getAirbillChangeLog");
    }

    public long countAirbillChangeLog(AirbillChangeLogFilter filter) throws DaoException {
        return (long) selectObject(filter, "EventLog.countAirbillChangeLog");
    }
}
