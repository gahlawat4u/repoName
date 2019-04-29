
package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("RequestOption")
    @Expose
    private String requestOption;
    @SerializedName("TransactionReference")
    @Expose
    private TransactionReference transactionReference;

    public String getRequestOption() {
        return requestOption;
    }

    public void setRequestOption(String requestOption) {
        this.requestOption = requestOption;
    }

    public TransactionReference getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(TransactionReference transactionReference) {
        this.transactionReference = transactionReference;
    }

}
