package com.gms.xms.model.webship.ship;

import com.gms.xms.model.BaseModel;


/**
 * @author tkvcl
 */
public class UsedRateWebshipModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -8836019421308817063L;
    private String srNo;
    private String customerBaseRateId;
    private String customerCode;
    private String shipmentTypeId;
    private String rateType;
    private String weight;
    private String rate;
    private String zoneCheck;
    private String content;
    private String bound;
    private String cbRate;
    private String minimunBaseCharge;

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getCustomerBaseRateId() {
        return customerBaseRateId;
    }

    public void setCustomerBaseRateId(String customerBaseRateId) {
        this.customerBaseRateId = customerBaseRateId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getZoneCheck() {
        return zoneCheck;
    }

    public void setZoneCheck(String zoneCheck) {
        this.zoneCheck = zoneCheck;
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

    public String getCbRate() {
        return cbRate;
    }

    public void setCbRate(String cbRate) {
        this.cbRate = cbRate;
    }

    public String getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(String minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    @Override
    public String toString() {
        return "UsedRateWebshipModel [srNo=" + srNo + ", customerBaseRateId=" + customerBaseRateId + ", customerCode=" + customerCode + ", shipmentTypeId=" + shipmentTypeId + ", rateType=" + rateType + ", weight=" + weight + ", rate=" + rate + ", zoneCheck=" + zoneCheck + ", content=" + content + ", bound=" + bound + ", cbRate=" + cbRate + ", minimunBaseCharge=" + minimunBaseCharge + "]";
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
        UsedRateWebshipModel other = (UsedRateWebshipModel) obj;
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
