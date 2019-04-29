package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from BillingTypeModel
 * <p>
 * Author HungNT Date Mar 26, 2015
 */
public class BillingTypeModel extends BaseModel {
    private static final long serialVersionUID = -215861866670716792L;

    private String billingId;

    private String billingName;

    private String localizationId;

    @Override
    public String toString() {
        return "BillingTypeModel [billingId=" + billingId + ", billingName=" + billingName + ", localizationId=" + localizationId + "]";
    }

    public String getBillingId() {
        return billingId;
    }

    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }
}
