package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from ServiceAdditionalConfigModel
 * <p>
 * Author HungNT Date Aug 27, 2015
 */
public class ServiceAddConModel extends BaseModel {
    private static final long serialVersionUID = 4316174448979596897L;

    private String addConId;

    private String addConName;

    private String addConType;

    private String addConCode;

    private String shipmentTypeId;

    private String value;

    private List<ServiceAddConDetailsModel> listProperties;

    public String getAddConId() {
        return addConId;
    }

    public void setAddConId(String addConId) {
        this.addConId = addConId;
    }

    public String getAddConName() {
        return addConName;
    }

    public void setAddConName(String addConName) {
        this.addConName = addConName;
    }

    public String getAddConType() {
        return addConType;
    }

    public void setAddConType(String addConType) {
        this.addConType = addConType;
    }

    public String getAddConCode() {
        return addConCode;
    }

    public void setAddConCode(String addConCode) {
        this.addConCode = addConCode;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ServiceAddConDetailsModel> getListProperties() {
        return listProperties;
    }

    public void setListProperties(List<ServiceAddConDetailsModel> listProperties) {
        this.listProperties = listProperties;
    }

    @JsonIgnore
    public Map<String, ServiceAddConDetailsModel> getProperties() {
        Map<String, ServiceAddConDetailsModel> properties = new HashMap<String, ServiceAddConDetailsModel>();
        for (ServiceAddConDetailsModel serviceAddConDetailsModel : listProperties) {
            properties.put(serviceAddConDetailsModel.getAddConDetailName(), serviceAddConDetailsModel);
        }
        return properties;
    }

    @Override
    public String toString() {
        return "ServiceAddConModel [addConId=" + addConId + ", addConName=" + addConName + ", addConType=" + addConType + ", addConCode=" + addConCode + ", shipmentTypeId=" + shipmentTypeId + ", value=" + value + ", listProperties=" + listProperties + "]";
    }
}
