package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.List;

/**
 * Posted from ServiceSettingVo
 * <p>
 * Author DatTV Sep 30, 2015
 */
public class ServiceSettingVo extends BaseVo {

    private static final long serialVersionUID = -1376897426915055801L;

    private Integer serviceId;
    private String serviceName;
    private Boolean checked;
    private List<ShipmentTypeSettingVo> shipmentTypes;

    @Override
    public String toString() {
        return "ServiceSettingVo [serviceId=" + serviceId + ", serviceName=" + serviceName + ", checked=" + checked + ", shipmentTypes=" + shipmentTypes + "]";
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<ShipmentTypeSettingVo> getShipmentTypes() {
        return shipmentTypes;
    }

    public void setShipmentTypes(List<ShipmentTypeSettingVo> shipmentTypes) {
        this.shipmentTypes = shipmentTypes;
    }
}
