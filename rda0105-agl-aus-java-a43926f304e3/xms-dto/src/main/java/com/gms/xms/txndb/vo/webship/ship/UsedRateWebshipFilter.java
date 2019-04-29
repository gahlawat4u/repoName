package com.gms.xms.txndb.vo.webship.ship;

import com.gms.xms.txndb.vo.BaseVo;


/**
 * @author tkvcl
 */
public class UsedRateWebshipFilter extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 368006255074907861L;
    private Long customerCode;
    private Integer shipType;
    private Double weight;
    private Integer rateType;
    private Integer content;
    private Integer bound;
    private String zone;

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getShipType() {
        return shipType;
    }

    public void setShipType(Integer shipType) {
        this.shipType = shipType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getBound() {
        return bound;
    }

    public void setBound(Integer bound) {
        this.bound = bound;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "UsedRateWebshipFilter [customerCode=" + customerCode + ", shipType=" + shipType + ", weight=" + weight + ", rateType=" + rateType + ", content=" + content + ", bound=" + bound + ", zone=" + zone + "]";
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
        UsedRateWebshipFilter other = (UsedRateWebshipFilter) obj;
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
