package com.gms.xms.model;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Posted from AccessorialModel
 * <p>
 * Author HungNT Date Apr 7, 2015
 */
public class AccessorialModel extends BaseModel {
    private static final long serialVersionUID = -3220846554450055627L;

    private String accessorialId;

    private String code;

    private String description;

    private String modifiedDate;

    private String typeId;

    private String isQuotable;

    private String carrier;

    private String value;
    @JsonIgnore
    private String valueCurrency;

    private Integer type;

    public String getValueCurrency() {
        return valueCurrency;
    }

    public void setValueCurrency(String valueCurrency) {
        this.valueCurrency = valueCurrency;
    }

    public String getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(String accessorialId) {
        this.accessorialId = accessorialId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getIsQuotable() {
        return isQuotable;
    }

    public void setIsQuotable(String isQuotable) {
        this.isQuotable = isQuotable;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AccessorialModel [accessorialId=" + accessorialId + ", code=" + code + ", description=" + description + ", modifiedDate=" + modifiedDate + ", typeId=" + typeId + ", isQuotable=" + isQuotable + ", carrier=" + carrier + ", value=" + value + ", valueCurrency=" + valueCurrency + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessorialId == null) ? 0 : accessorialId.hashCode());
        result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((isQuotable == null) ? 0 : isQuotable.hashCode());
        result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
        result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((valueCurrency == null) ? 0 : valueCurrency.hashCode());
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
        AccessorialModel other = (AccessorialModel) obj;
        if (accessorialId == null) {
            if (other.accessorialId != null)
                return false;
        } else if (!accessorialId.equals(other.accessorialId))
            return false;
        if (carrier == null) {
            if (other.carrier != null)
                return false;
        } else if (!carrier.equals(other.carrier))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (isQuotable == null) {
            if (other.isQuotable != null)
                return false;
        } else if (!isQuotable.equals(other.isQuotable))
            return false;
        if (modifiedDate == null) {
            if (other.modifiedDate != null)
                return false;
        } else if (!modifiedDate.equals(other.modifiedDate))
            return false;
        if (typeId == null) {
            if (other.typeId != null)
                return false;
        } else if (!typeId.equals(other.typeId))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        if (valueCurrency == null) {
            if (other.valueCurrency != null)
                return false;
        } else if (!valueCurrency.equals(other.valueCurrency))
            return false;
        return true;
    }
}