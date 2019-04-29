package com.gms.xms.model.webship;


import com.gms.xms.model.AccessorialModel;
import com.gms.xms.model.BaseModel;


/**
 * Posted from AddressModel
 * <p>
 * Author TanDT Date Mar 27, 2015
 */
public class ShipmentDetailModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -475777796386143130L;

    private String shipmentId;

    private String accessorialId;

    private String amount;

    private Integer type;

    private AccessorialModel accessorial;

    public AccessorialModel getAccessorial() {
        return accessorial;
    }

    public void setAccessorial(AccessorialModel accessorial) {
        this.accessorial = accessorial;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(String accessorialId) {
        this.accessorialId = accessorialId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ShipmentDetailModel [shipmentId=" + shipmentId + ", accessorialId=" + accessorialId + ", amount="
                + amount + "]";
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
