
package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionReference {

    @SerializedName("CustomerContext")
    @Expose
    private String customerContext;

    public String getCustomerContext() {
        return customerContext;
    }

    public void setCustomerContext(String customerContext) {
        this.customerContext = customerContext;
    }

}
