package com.gms.xms.persistence.service.schedule;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IScheduleCollectionService
 * <p>
 * Author dattrinh Jan 18, 2016 6:23:59 PM
 */
public interface IScheduleCollectionService {
    public ScheduleCollectionVo selectById(String scheduleCollectionId) throws DaoException;

    public ScheduleCollectionVo doSchedule(Map<String, String> context, AddressVo address, ScheduleCollectionVo scheduleCollection) throws DaoException;

    public ScheduleCollectionVo modifySchedule(Map<String, String> context, AddressVo address, ScheduleCollectionVo scheduleCollection) throws DaoException;

    public ScheduleCollectionVo getScheduleCollectionForModify(Long shipmentId) throws DaoException;

    public List<ScheduleCollectionVo> selectListScheduleCollectionBySmId(Long shipmentId) throws DaoException;

    public void cancelScheduleCollection(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException;

    void cancelScheduleByShipmentId(Map<String, String> context, Long shipmentId) throws DaoException;
}
