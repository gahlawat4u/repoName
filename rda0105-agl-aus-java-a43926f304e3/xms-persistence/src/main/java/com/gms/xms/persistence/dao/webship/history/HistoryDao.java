package com.gms.xms.persistence.dao.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.HistoryDetailScheduleInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryVo;

import java.util.List;

/**
 * Posted from HistoryDao
 * <p>
 * Author TanDT Date Jul 8, 2015
 */
public class HistoryDao extends BaseDao<HistoryVo> {
    /**
     * @return
     * @throws DaoException
     */

    public List<HistoryVo> selectByFilter(HistoryFilter filter) throws DaoException {
        return selectList(filter, "History.selectByFilter");
    }

    public Integer selectCountByFilter(HistoryFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "History.selectCountByFilter");
    }

    public HistoryDetailScheduleInfoVo selectHistoryDetailScheduleCollection(Long shipmentId) throws DaoException {
        return (HistoryDetailScheduleInfoVo) selectObject(shipmentId,"History.selectHistoryDetailScheduleCollection");
    }
}
