package com.gms.xms.workflow.service.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.ScheduleCollectionDao;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.webship.ShipmentScheduleCollectionVo;

import java.util.Map;

public class HistoryScheduleServiceImp implements IHistoryScheduleService {

    @Override
    public Integer updateScheduleCollectionById(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException {
        ScheduleCollectionDao dao = new ScheduleCollectionDao();
        return dao.updateScheduleCollectionById(context, scheduleCollectionVo);
    }

    @Override
    public Integer insertScheduleCollection(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException {
        ScheduleCollectionDao dao = new ScheduleCollectionDao();
        return dao.insertScheduleCollection(context, scheduleCollectionVo);
    }

    @Override
    public ScheduleCollectionVo selectScheduleCollectionBySmId(Long shipmentId) throws DaoException {
        ScheduleCollectionDao dao = new ScheduleCollectionDao();
        return dao.selectScheduleCollectionBySmId(shipmentId);
    }

    @Override
    public Integer updateScheduleCollectionBySmId(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException {
        ScheduleCollectionDao dao = new ScheduleCollectionDao();
        return dao.updateScheduleCollectionBySmId(context, scheduleCollectionVo);
    }

    @Override
    public void cancelScheduleCollectionById(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException {
        ScheduleCollectionDao dao = new ScheduleCollectionDao();
        dao.cancelScheduleCollectionById(context, scheduleCollectionVo);

    }

    @Override
    public ShipmentScheduleCollectionVo selectShipmentScheduleCollectionByShipmentId(Long shipmentId) throws DaoException {
        ScheduleCollectionDao scheduleCollectionDao = new ScheduleCollectionDao();
        return scheduleCollectionDao.selectShipmentScheduleCollectionBySmId(shipmentId);
    }
}
