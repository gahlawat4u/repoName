package com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.response;

import com.gms.delivery.dhl.xmlpi.datatype.error.response.PickupErrorResponse;

/**
 * Posted from CancelPUResult
 * <p>
 * Author dattrinh Jan 19, 2016 10:17:13 AM
 */
public class CancelPUResult {
    private PickupErrorResponse errorResponse;
    private CancelPUResponse response;

    public PickupErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(PickupErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public CancelPUResponse getResponse() {
        return response;
    }

    public void setResponse(CancelPUResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "CancelPUResult [errorResponse=" + errorResponse + ", response=" + response + "]";
    }
}
