package com.gms.xms.persistence.service.webship.shipment;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.model.webship.ship.BookingDataVo;

import java.util.Map;

public interface IBookingShipmentService {

    public BookingDataVo bookingShipment(Map<String, String> context, BookingDataVo bookingDataVo) throws DaoException;

}
