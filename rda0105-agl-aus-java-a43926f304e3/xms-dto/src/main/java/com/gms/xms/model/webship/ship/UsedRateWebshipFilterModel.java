package com.gms.xms.model.webship.ship;

import com.gms.xms.model.BaseModel;


/**
 * @author tkvcl
 */
public class UsedRateWebshipFilterModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -8836019421308817063L;
    private String customerCode;
    private String shipType;
    private String weight;
    private String rateType;
    private String content;
    private String bound;
    private String zone;


    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    @Override
    public String toString() {
        return "UsedRateWebshipFilterModel [customerCode=" + customerCode + ", shipType=" + shipType + ", weight=" + weight + ", rateType=" + rateType + ", content=" + content + ", bound=" + bound + ", zone=" + zone + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bound == null) ? 0 : bound.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((rateType == null) ? 0 : rateType.hashCode());
        result = prime * result + ((shipType == null) ? 0 : shipType.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((zone == null) ? 0 : zone.hashCode());
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
        UsedRateWebshipFilterModel other = (UsedRateWebshipFilterModel) obj;
        if (bound == null) {
            if (other.bound != null)
                return false;
        } else if (!bound.equals(other.bound))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (rateType == null) {
            if (other.rateType != null)
                return false;
        } else if (!rateType.equals(other.rateType))
            return false;
        if (shipType == null) {
            if (other.shipType != null)
                return false;
        } else if (!shipType.equals(other.shipType))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        return true;
    }


}
