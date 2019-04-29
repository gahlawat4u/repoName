package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from ShipmentTypePackageModel
 * <p>
 * Author HungNT Date Jul 21, 2015
 */
public class ShipmentTypePackageModel extends BaseModel {
    private static final long serialVersionUID = 5879529599461476027L;

    private String spId;

    private String shipmentTypeId;

    private String packageId;

    private String defaultContentType;

    private String allowDoxAddpiece;

    private String allowWpxAddpiece;

    private String allowDox;

    private String allowWpx;

    private String allowCustomValue;

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getDefaultContentType() {
        return defaultContentType;
    }

    public void setDefaultContentType(String defaultContentType) {
        this.defaultContentType = defaultContentType;
    }

    public String getAllowDoxAddpiece() {
        return allowDoxAddpiece;
    }

    public void setAllowDoxAddpiece(String allowDoxAddpiece) {
        this.allowDoxAddpiece = allowDoxAddpiece;
    }

    public String getAllowWpxAddpiece() {
        return allowWpxAddpiece;
    }

    public void setAllowWpxAddpiece(String allowWpxAddpiece) {
        this.allowWpxAddpiece = allowWpxAddpiece;
    }

    public String getAllowDox() {
        return allowDox;
    }

    public void setAllowDox(String allowDox) {
        this.allowDox = allowDox;
    }

    public String getAllowWpx() {
        return allowWpx;
    }

    public void setAllowWpx(String allowWpx) {
        this.allowWpx = allowWpx;
    }

    public String getAllowCustomValue() {
        return allowCustomValue;
    }

    public void setAllowCustomValue(String allowCustomValue) {
        this.allowCustomValue = allowCustomValue;
    }

    @Override
    public String toString() {
        return "ShipmentTypePackageModel [spId=" + spId + ", shipmentTypeId=" + shipmentTypeId + ", packageId=" + packageId + ", defaultContentType=" + defaultContentType + ", allowDoxAddpiece=" + allowDoxAddpiece + ", allowWpxAddpiece=" + allowWpxAddpiece + ", allowDox=" + allowDox + ", allowWpx=" + allowWpx + ", allowCustomValue=" + allowCustomValue + "]";
    }
}