package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceAddConVo extends BaseVo {
    private static final long serialVersionUID = -217817900179502813L;

    private Integer addConId;

    private String addConName;

    private String addConType;

    private String addConCode;

    private Integer shipmentTypeId;

    private String value;

    private List<ServiceAddConDetailsVo> listProperties;

    public Integer getAddConId() {
        return addConId;
    }

    public void setAddConId(Integer addConId) {
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

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ServiceAddConDetailsVo> getListProperties() {
        return listProperties;
    }

    public void setListProperties(List<ServiceAddConDetailsVo> listProperties) {
        this.listProperties = listProperties;
    }

    @JsonIgnore
    public Map<String, ServiceAddConDetailsVo> getPropertiesMap() {
        Map<String, ServiceAddConDetailsVo> properties = new HashMap<String, ServiceAddConDetailsVo>();
        if (!this.listProperties.isEmpty()) {
            for (ServiceAddConDetailsVo serviceAddConDetailsVo : this.listProperties) {
                properties.put(serviceAddConDetailsVo.getAddConDetailName(), serviceAddConDetailsVo);
            }
        }
        return properties;
    }

    @Override
    public String toString() {
        return "ServiceAddConVo [addConId=" + addConId + ", addConName=" + addConName + ", addConType=" + addConType + ", addConCode=" + addConCode + ", shipmentTypeId=" + shipmentTypeId + ", value=" + value + ", listProperties=" + listProperties + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addConCode == null) ? 0 : addConCode.hashCode());
        result = prime * result + ((addConId == null) ? 0 : addConId.hashCode());
        result = prime * result + ((addConName == null) ? 0 : addConName.hashCode());
        result = prime * result + ((addConType == null) ? 0 : addConType.hashCode());
        result = prime * result + ((listProperties == null) ? 0 : listProperties.hashCode());
        result = prime * result + ((shipmentTypeId == null) ? 0 : shipmentTypeId.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ServiceAddConVo other = (ServiceAddConVo) obj;
        if (addConCode == null) {
            if (other.addConCode != null)
                return false;
        } else if (!addConCode.equals(other.addConCode))
            return false;
        if (addConId == null) {
            if (other.addConId != null)
                return false;
        } else if (!addConId.equals(other.addConId))
            return false;
        if (addConName == null) {
            if (other.addConName != null)
                return false;
        } else if (!addConName.equals(other.addConName))
            return false;
        if (addConType == null) {
            if (other.addConType != null)
                return false;
        } else if (!addConType.equals(other.addConType))
            return false;
        if (listProperties == null) {
            if (other.listProperties != null)
                return false;
        } else if (!listProperties.equals(other.listProperties))
            return false;
        if (shipmentTypeId == null) {
            if (other.shipmentTypeId != null)
                return false;
        } else if (!shipmentTypeId.equals(other.shipmentTypeId))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
}