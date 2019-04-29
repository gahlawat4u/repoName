package com.gms.xms.model;

/**
 * Posted from FranchiseServiceMarkupModel
 * <p>
 * Author @author HungNT May 20, 2016
 */
public class FranchiseServiceMarkupModel extends BaseModel {
    private static final long serialVersionUID = -9026728178080512526L;

    private String markupId;
    private String franchiseCode;
    private String serviceId;
    private String shipmentTypeId;
    private String markupRate;

    public String getMarkupId() {
        return markupId;
    }

    public void setMarkupId(String markupId) {
        this.markupId = markupId;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getMarkupRate() {
        return markupRate;
    }

    public void setMarkupRate(String markupRate) {
        this.markupRate = markupRate;
    }

    @Override
    public String toString() {
        return "FranchiseServiceMarkupModel [markupId=" + markupId + ", franchiseCode=" + franchiseCode + ", serviceId=" + serviceId + ", shipmentTypeId=" + shipmentTypeId + ", markupRate=" + markupRate + "]";
    }
}
