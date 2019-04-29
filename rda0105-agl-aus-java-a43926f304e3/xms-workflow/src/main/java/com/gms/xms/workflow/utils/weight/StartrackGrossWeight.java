package com.gms.xms.workflow.utils.weight;

/**
 * Posted from Jul 28, 2016 4:20:04 PM
 * <p>
 * Author dattrinh
 */
public class StartrackGrossWeight extends BaseGrossWeight {
    private Integer shipmentTypeId;
    private String shipmentTypeName;

    @Override
    public Integer getForceVolWeight() throws Exception {
        Integer forceVolWeight = null;
        if ("road express".equalsIgnoreCase(this.getShipmentTypeName()) || "premium air freight".equalsIgnoreCase(this.getShipmentTypeName()) || this.getShipmentTypeId() == 228 || this.getShipmentTypeId() == 229) {
            // Road Express & Premium air freight
            // PRM
            forceVolWeight = Integer.parseInt(this.getSystemSettingDao().getSystemSettingByName("Volume Weight Divided By (Startrack)").getSettingValue().trim()); // 4000;
        } else { // FPP
            forceVolWeight = Integer.parseInt(this.getSystemSettingDao().getSystemSettingByName("Volume Weight Divided By (Startrack FPP)").getSettingValue().trim()); // 5263;
        }
        return forceVolWeight == null ? 5000 : forceVolWeight;
    }

    @Override
    public double getGrossWeight(double l, double w, double h, String dimUnit) throws Exception {
        return Math.ceil(super.getGrossWeight(l, w, h, dimUnit));
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }
}
