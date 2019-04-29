package com.gms.delivery.dhl.xmlpi.datatype.pickup.modify;

import com.gms.delivery.dhl.xmlpi.datatype.pickup.PickupErrorResponse;

/**
 * Posted from ModifyPUResult
 * <p>
 * Author dattrinh Jan 22, 2016 5:12:00 PM
 */
public class ModifyPUResult {
    private ModifyPUResponse modifyPUResponse;
    private PickupErrorResponse pickupErrorResponse;

    public ModifyPUResponse getModifyPUResponse() {
        return modifyPUResponse;
    }

    public void setModifyPUResponse(ModifyPUResponse modifyPUResponse) {
        this.modifyPUResponse = modifyPUResponse;
    }

    public PickupErrorResponse getPickupErrorResponse() {
        return pickupErrorResponse;
    }

    public void setPickupErrorResponse(PickupErrorResponse pickupErrorResponse) {
        this.pickupErrorResponse = pickupErrorResponse;
    }
}
