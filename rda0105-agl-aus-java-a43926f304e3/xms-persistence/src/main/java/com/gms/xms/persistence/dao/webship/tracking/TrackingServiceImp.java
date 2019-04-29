package com.gms.xms.persistence.dao.webship.tracking;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.TrackingDao;

public class TrackingServiceImp implements ITrackingService {
    private TrackingDao trackingDao = new TrackingDao();

    @Override
    public String selectAirbillNumberByShipmentId(Long shipmentId) throws DaoException {
        return trackingDao.selectAirbillNumberByShipmentId(shipmentId);
    }
}
