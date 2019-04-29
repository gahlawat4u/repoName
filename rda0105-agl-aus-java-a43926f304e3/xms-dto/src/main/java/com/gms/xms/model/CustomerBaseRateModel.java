package com.gms.xms.model;

import com.gms.xms.txndb.vo.RateType;

import java.util.List;

/**
 * Posted from CustomerBaseRateModel
 * <p>
 * Author DatTV Sep 14, 2015
 */
public class CustomerBaseRateModel extends BaseModel {

    private static final long serialVersionUID = -6973170673594595880L;

    private String customerBaseRateId;

    private String customerCode;

    private String shipmentTypeId;

    private String rateType;

    private String weight;

    private String rate;

    private String zoneCheck;

    private String content;

    private String bound;

    private String baserateDescription;

    private String carrierId;

    private List<CustomerBaseRateDetailModel> customerBaseRateDetails;

    private List<RateType> rateTypes;

    @Override
    public String toString() {
        return "CustomerBaseRateModel [customerBaseRateId=" + customerBaseRateId + ", customerCode=" + customerCode + ", shipmentTypeId=" + shipmentTypeId + ", rateType=" + rateType + ", weight=" + weight + ", rate=" + rate + ", zoneCheck=" + zoneCheck + ", content=" + content + ", bound=" + bound + ", baserateDescription=" + baserateDescription + ", carrierId=" + carrierId + ", customerBaseRateDetails=" + customerBaseRateDetails + "]";
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

    public String getBaserateDescription() {
        return baserateDescription;
    }

    public void setBaserateDescription(String baserateDescription) {
        this.baserateDescription = baserateDescription;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public List<CustomerBaseRateDetailModel> getCustomerBaseRateDetails() {
        return customerBaseRateDetails;
    }

    public void setCustomerBaseRateDetails(List<CustomerBaseRateDetailModel> customerBaseRateDetails) {
        this.customerBaseRateDetails = customerBaseRateDetails;
    }

    public List<RateType> getRateTypes() {
        return rateTypes;
    }

    public void setRateTypes(List<RateType> rateTypes) {
        this.rateTypes = rateTypes;
    }
}