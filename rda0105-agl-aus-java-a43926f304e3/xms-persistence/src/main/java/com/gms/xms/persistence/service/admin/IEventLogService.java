package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.EventLogFilter;
import com.gms.xms.filter.eventlog.CustomerEventLogFilter;
import com.gms.xms.txndb.vo.admin.EventLogVo;

import java.util.List;

/**
 * Posted from IEventLogService
 * <p>
 * Author TANDT
 */
public interface IEventLogService {
    public List<EventLogVo> selectListByFilter(EventLogFilter filter) throws DaoException;

    public Long selectCountListByFilter(EventLogFilter filter) throws DaoException;

    public List<EventLogVo> selectByCustCode(CustomerEventLogFilter filter) throws DaoException;

    public long countByCustCode(CustomerEventLogFilter filter) throws DaoException;

    public List<EventLogVo> selectByProfileId(CustomerEventLogFilter filter) throws DaoException;

    public long selectByProfileIdCount(CustomerEventLogFilter filter) throws DaoException;
}
