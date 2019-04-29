
package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShipmentCharge {

    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("BillShipper")
    @Expose
    private BillShipper billShipper;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BillShipper getBillShipper() {
        return billShipper;
    }

    public void setBillShipper(BillShipper billShipper) {
        this.billShipper = billShipper;
    }

}
