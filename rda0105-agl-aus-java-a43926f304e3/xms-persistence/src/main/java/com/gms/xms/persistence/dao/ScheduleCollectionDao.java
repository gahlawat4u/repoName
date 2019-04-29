package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.webship.ShipmentScheduleCollectionVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ScheduleCollectionDao
 * <p>
 * Author TanDT Date Apr 20, 2015
 */
public class ScheduleCollectionDao extends BaseDao<ScheduleCollectionVo> {
    public ScheduleCollectionDao() {
        super();
    }

    public ScheduleCollectionDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public ScheduleCollectionVo selectById(String scheduleCollectionId) throws DaoException {
        return this.select(scheduleCollectionId, "ScheduleCollection.selectById");
    }

    public Integer updateScheduleCollectionById(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException {
        return update(context, scheduleCollectionVo, "ScheduleCollection.Schedule_UpdateByPrimaryKeySelective");
    }

    public void cancelScheduleCollectionById(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException {
        update(context, scheduleCollectionVo, "ScheduleCollection.Schedule_CancelByPrimaryKeySelective");
    }

    public Integer insertScheduleCollection(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException {
        return insert(context, scheduleCollectionVo, "ScheduleCollection.Schedule_InsertSelective");
    }

    public ScheduleCollectionVo selectScheduleCollectionBySmId(Long shipmentId) throws DaoException {
        return select(shipmentId, "ScheduleCollection.Schedule_SelectScheduleCollectionBySmId");
    }

    public ShipmentScheduleCollectionVo selectShipmentScheduleCollectionBySmId(Long shipmentId) throws DaoException {
        return (ShipmentScheduleCollectionVo) selectObject(shipmentId, "ScheduleCollection.selectShipmentScheduleCollectionBySmId");
    }

    public List<ScheduleCollectionVo> selectListScheduleCollectionBySmId(Long shipmentId) throws DaoException {
        return selectList(shipmentId, "ScheduleCollection.ScheduleCollection_SelectListScheduleCollectionBySmId");
    }

    public Integer updateScheduleCollectionBySmId(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException {
        return update(context, scheduleCollectionVo, "ScheduleCollection.Schedule_UpdateByShipmentId");
    }

    public ScheduleCollectionVo getScheduleCollectionForModify(Long shipmentId) throws DaoException {
        return this.select(shipmentId, "ScheduleCollection.getScheduleCollectionForModify");
    }

    public void cancelScheduleByShipmentId(Map<String, String> context, Long shipmentId) throws DaoException {
        this.update(context, shipmentId, "ScheduleCollection.cancelScheduleByShipmentId");
    }
}
