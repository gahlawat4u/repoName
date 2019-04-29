package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.EventLogFilter;
import com.gms.xms.filter.eventlog.CustomerEventLogFilter;
import com.gms.xms.persistence.dao.admin.EventLogDao;
import com.gms.xms.txndb.vo.admin.EventLogVo;

import java.util.List;

/**
 * Posted from AdminEmailImp
 * <p>
 * Author TANDT
 */
public class EventLogServiceImp implements IEventLogService {

    @Override
    public List<EventLogVo> selectListByFilter(EventLogFilter filter) throws DaoException {
        EventLogDao dao = new EventLogDao();
        return dao.selectListByFilter(filter);
    }

    @Override
    public Long selectCountListByFilter(EventLogFilter filter) throws DaoException {
        EventLogDao dao = new EventLogDao();
        return dao.selectCountListByFilter(filter);
    }

    @Override
    public List<EventLogVo> selectByCustCode(CustomerEventLogFilter filter) throws DaoException {
        EventLogDao dao = new EventLogDao();
        return dao.selectByCustCode(filter);
    }

    @Override
    public long countByCustCode(CustomerEventLogFilter filter) throws DaoException {
        EventLogDao dao = new EventLogDao();
        return dao.countByCustCode(filter);
    }

    @Override
    public List<EventLogVo> selectByProfileId(CustomerEventLogFilter filter) throws DaoException {
        EventLogDao dao = new EventLogDao();
        return dao.selectByProfileId(filter);
    }

    @Override
    public long selectByProfileIdCount(CustomerEventLogFilter filter) throws DaoException {
        EventLogDao dao = new EventLogDao();
        return dao.selectByProfileIdCount(filter);
    }

}
