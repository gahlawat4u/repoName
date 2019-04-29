package com.gms.xms.txndb.vo.webship.ship;

import com.gms.xms.txndb.vo.BaseVo;


/**
 * @author tkvcl
 */
public class UsedRateWebshipVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 1067322619954188417L;
    private Integer srNo;
    private Integer customerBaseRateId;
    private Long customerCode;
    private Integer shipmentTypeId;
    private Integer rateType;
    private Double weight;
    private Double rate;
    private Integer zoneCheck;
    private Integer content;
    private Integer bound;
    private Integer cbRate;
    private Double minimunBaseCharge;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public Integer getCustomerBaseRateId() {
        return customerBaseRateId;
    }

    public void setCustomerBaseRateId(Integer customerBaseRateId) {
        this.customerBaseRateId = customerBaseRateId;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getZoneCheck() {
        return zoneCheck;
    }

    public void setZoneCheck(Integer zoneCheck) {
        this.zoneCheck = zoneCheck;
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

    public Integer getCbRate() {
        return cbRate;
    }

    public void setCbRate(Integer cbRate) {
        this.cbRate = cbRate;
    }

    public Double getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(Double minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    @Override
    public String toString() {
        return "UsedRateWebshipVo [srNo=" + srNo + ", customerBaseRateId=" + customerBaseRateId + ", customerCode=" + customerCode + ", shipmentTypeId=" + shipmentTypeId + ", rateType=" + rateType + ", weight=" + weight + ", rate=" + rate + ", zoneCheck=" + zoneCheck + ", content=" + content + ", bound=" + bound + ", cbRate=" + cbRate + ", minimunBaseCharge=" + minimunBaseCharge + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bound == null) ? 0 : bound.hashCode());
        result = prime * result + ((cbRate == null) ? 0 : cbRate.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((customerBaseRateId == null) ? 0 : customerBaseRateId.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((minimunBaseCharge == null) ? 0 : minimunBaseCharge.hashCode());
        result = prime * result + ((rate == null) ? 0 : rate.hashCode());
        result = prime * result + ((rateType == null) ? 0 : rateType.hashCode());
        result = prime * result + ((shipmentTypeId == null) ? 0 : shipmentTypeId.hashCode());
        result = prime * result + ((srNo == null) ? 0 : srNo.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((zoneCheck == null) ? 0 : zoneCheck.hashCode());
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
        UsedRateWebshipVo other = (UsedRateWebshipVo) obj;
        if (bound == null) {
            if (other.bound != null)
                return false;
        } else if (!bound.equals(other.bound))
            return false;
        if (cbRate == null) {
            if (other.cbRate != null)
                return false;
        } else if (!cbRate.equals(other.cbRate))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (customerBaseRateId == null) {
            if (other.customerBaseRateId != null)
                return false;
        } else if (!customerBaseRateId.equals(other.customerBaseRateId))
            return false;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (minimunBaseCharge == null) {
            if (other.minimunBaseCharge != null)
                return false;
        } else if (!minimunBaseCharge.equals(other.minimunBaseCharge))
            return false;
        if (rate == null) {
            if (other.rate != null)
                return false;
        } else if (!rate.equals(other.rate))
            return false;
        if (rateType == null) {
            if (other.rateType != null)
                return false;
        } else if (!rateType.equals(other.rateType))
            return false;
        if (shipmentTypeId == null) {
            if (other.shipmentTypeId != null)
                return false;
        } else if (!shipmentTypeId.equals(other.shipmentTypeId))
            return false;
        if (srNo == null) {
            if (other.srNo != null)
                return false;
        } else if (!srNo.equals(other.srNo))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        if (zoneCheck == null) {
            if (other.zoneCheck != null)
                return false;
        } else if (!zoneCheck.equals(other.zoneCheck))
            return false;
        return true;
    }


}
