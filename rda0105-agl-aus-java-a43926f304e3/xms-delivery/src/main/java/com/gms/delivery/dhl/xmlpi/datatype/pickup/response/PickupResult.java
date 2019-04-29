package com.gms.delivery.dhl.xmlpi.datatype.pickup.response;

import com.gms.delivery.dhl.xmlpi.datatype.error.response.DhlErrorResponse;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.PickupErrorResponse;

public class PickupResult {
    private BookPUResponse bookPUResponse;
    private DhlErrorResponse errorResponse;
    private PickupErrorResponse pickupErrorResponse;

    public BookPUResponse getBookPUResponse() {
        return bookPUResponse;
    }

    public void setBookPUResponse(BookPUResponse bookPUResponse) {
        this.bookPUResponse = bookPUResponse;
    }

    public DhlErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(DhlErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public PickupErrorResponse getPickupErrorResponse() {
        return pickupErrorResponse;
    }

    public void setPickupErrorResponse(PickupErrorResponse pickupErrorResponse) {
        this.pickupErrorResponse = pickupErrorResponse;
    }
}
