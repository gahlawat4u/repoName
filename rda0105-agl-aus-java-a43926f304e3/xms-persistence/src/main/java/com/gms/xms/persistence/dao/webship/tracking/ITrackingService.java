package com.gms.xms.persistence.dao.webship.tracking;

import com.gms.xms.common.exception.DaoException;

/**
 * Posted from ITrackingService
 * <p>
 * Author dattrinh Jan 19, 2016 2:20:09 PM
 */
public interface ITrackingService {
    public String selectAirbillNumberByShipmentId(Long shipmentId) throws DaoException;
}
