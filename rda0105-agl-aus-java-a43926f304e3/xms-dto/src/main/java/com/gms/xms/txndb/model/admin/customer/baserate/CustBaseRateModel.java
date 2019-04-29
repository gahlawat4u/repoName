package com.gms.xms.txndb.model.admin.customer.baserate;

import com.gms.xms.model.BaseModel;
import com.gms.xms.txndb.vo.RateType;

import java.util.List;

/**
 * Posted from Apr 4, 2016 11:09:58 PM
 * <p>
 * Author dattrinh
 */
public class CustBaseRateModel extends BaseModel {

    private static final long serialVersionUID = 1L;

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
    private List<CustBaseRateDetailModel> custBaseRateDetails;
    private List<RateType> rateTypes;

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

    public List<CustBaseRateDetailModel> getCustBaseRateDetails() {
        return custBaseRateDetails;
    }

    public void setCustBaseRateDetails(List<CustBaseRateDetailModel> custBaseRateDetails) {
        this.custBaseRateDetails = custBaseRateDetails;
    }

    @Override
    public String toString() {
        return "CustBaseRateModel [customerBaseRateId=" + customerBaseRateId + ", customerCode=" + customerCode + ", shipmentTypeId=" + shipmentTypeId + ", rateType=" + rateType + ", weight=" + weight + ", rate=" + rate + ", zoneCheck=" + zoneCheck + ", content=" + content + ", bound=" + bound + ", baserateDescription=" + baserateDescription + ", carrierId=" + carrierId + ", custBaseRateDetails=" + custBaseRateDetails + ", rateTypes=" + rateTypes + "]";
    }

    public List<RateType> getRateTypes() {
        return rateTypes;
    }

    public void setRateTypes(List<RateType> rateTypes) {
        this.rateTypes = rateTypes;
    }
}
