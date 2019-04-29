package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.DeliveredTrackingListFilter;
import com.gms.xms.txndb.vo.TrackingVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from TrackingDao
 * <p>
 * Author TanDT Date Apr 14, 2015
 */
public class TrackingDao extends BaseDao<TrackingVo> {
    public TrackingDao() {
        super();
    }

    public TrackingDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * @return
     * @throws DaoException
     */
    public List<TrackingVo> getTrackingBySm(DeliveredTrackingListFilter deliveredTrackingListFilter) throws DaoException {
        return selectList(deliveredTrackingListFilter, "Tracking_SelectDeliveredTrackingList");
    }

    public int delTracking(Map<String, String> context, TrackingVo trackingVo) throws DaoException {
        return delete(context, trackingVo, "Tracking_DeleteTracking");
    }

    public int insTracking(Map<String, String> context, TrackingVo trackingVo) throws DaoException {
        return insert(context, trackingVo, "Tracking_InsertTracking");
    }

    public int updateTracking(Map<String, String> context, TrackingVo trackingVo) throws DaoException {
        return insert(context, trackingVo, "Tracking_UpdateTracking");
    }

    public String selectAirbillNumberByShipmentId(Long shipmentId) throws DaoException {
        return (String) this.selectObject(shipmentId, "Tracking.selectAirbillNumberByShipmentId");
    }

    public void deleteByShipmentId(Map<String, String> context, long shipmentId) throws DaoException {
        delete(context, shipmentId, "Tracking.deleteByShipmentId");
    }
}
