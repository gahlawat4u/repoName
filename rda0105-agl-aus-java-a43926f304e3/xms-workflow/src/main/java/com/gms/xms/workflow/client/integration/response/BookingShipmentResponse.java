package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.model.webship.ship.BookingDataVo;

/**
 * Posted from BookingShipmentResponse
 * <p>
 * Author TANDT
 */
public class BookingShipmentResponse extends BaseResponse {
    private BookingDataVo bookingDataVo;

    public BookingDataVo getBookingDataVo() {
        return bookingDataVo;
    }

    public void setBookingDataVo(BookingDataVo bookingDataVo) {
        this.bookingDataVo = bookingDataVo;
    }

}
