package com.gms.xms.workflow.service.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.webship.ShipmentScheduleCollectionVo;

import java.util.Map;

public interface IHistoryScheduleService {
    public Integer updateScheduleCollectionById(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException;

    public Integer insertScheduleCollection(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException;

    public ScheduleCollectionVo selectScheduleCollectionBySmId(Long shipmentId) throws DaoException;

    public Integer updateScheduleCollectionBySmId(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException;

    public void cancelScheduleCollectionById(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException;

    ShipmentScheduleCollectionVo selectShipmentScheduleCollectionByShipmentId(Long shipmentId) throws DaoException;
}
