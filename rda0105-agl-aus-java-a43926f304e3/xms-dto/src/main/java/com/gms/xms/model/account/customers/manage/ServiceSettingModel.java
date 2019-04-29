package com.gms.xms.model.account.customers.manage;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * Posted from ServiceSettingModel
 * <p>
 * Author DatTV Sep 30, 2015
 */
public class ServiceSettingModel extends BaseModel {

    private static final long serialVersionUID = -7890522849547681833L;

    private String serviceId;
    private String serviceName;
    private String checked;
    private List<ShipmentTypeSettingModel> shipmentTypes;

    @Override
    public String toString() {
        return "ServiceSettingModel [serviceId=" + serviceId + ", serviceName=" + serviceName + ", checked=" + checked + ", shipmentTypes=" + shipmentTypes + "]";
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public List<ShipmentTypeSettingModel> getShipmentTypes() {
        return shipmentTypes;
    }

    public void setShipmentTypes(List<ShipmentTypeSettingModel> shipmentTypes) {
        this.shipmentTypes = shipmentTypes;
    }
}
