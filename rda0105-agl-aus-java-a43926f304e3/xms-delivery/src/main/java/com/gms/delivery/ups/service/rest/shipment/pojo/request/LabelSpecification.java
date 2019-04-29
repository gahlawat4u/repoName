
package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabelSpecification {

    @SerializedName("LabelImageFormat")
    @Expose
    private LabelImageFormat labelImageFormat;
    @SerializedName("HTTPUserAgent")
    @Expose
    private String hTTPUserAgent;

    public LabelImageFormat getLabelImageFormat() {
        return labelImageFormat;
    }

    public void setLabelImageFormat(LabelImageFormat labelImageFormat) {
        this.labelImageFormat = labelImageFormat;
    }

    public String getHTTPUserAgent() {
        return hTTPUserAgent;
    }

    public void setHTTPUserAgent(String hTTPUserAgent) {
        this.hTTPUserAgent = hTTPUserAgent;
    }

}
