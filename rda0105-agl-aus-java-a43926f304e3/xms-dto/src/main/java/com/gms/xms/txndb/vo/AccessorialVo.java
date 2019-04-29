package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from AccessorialVo
 * <p>
 * Author HungNT Date Apr 6, 2015
 */
public class AccessorialVo extends BaseVo {

    private static final long serialVersionUID = 5332843553254125571L;

    private Long accessorialId;

    private String code;

    private String description;

    private Date modifiedDate;

    private Integer typeId;

    private Integer isQuotable;

    private Long carrier;

    private Double value;

    private Integer type;

    public Long getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(Long accessorialId) {
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

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getIsQuotable() {
        return isQuotable;
    }

    public void setIsQuotable(Integer isQuotable) {
        this.isQuotable = isQuotable;
    }

    public Long getCarrier() {
        return carrier;
    }

    public void setCarrier(Long carrier) {
        this.carrier = carrier;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
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
        return "AccessorialVo [accessorialId=" + accessorialId + ", code=" + code + ", description=" + description + ", modifiedDate=" + modifiedDate + ", typeId=" + typeId + ", isQuotable=" + isQuotable + ", carrier=" + carrier + ", value=" + value + "]";
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
        AccessorialVo other = (AccessorialVo) obj;
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
        return true;
    }
}