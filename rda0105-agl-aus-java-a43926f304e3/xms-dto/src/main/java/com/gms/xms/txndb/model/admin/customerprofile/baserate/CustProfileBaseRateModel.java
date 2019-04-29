package com.gms.xms.txndb.model.admin.customerprofile.baserate;

import com.gms.xms.model.BaseModel;
import com.gms.xms.txndb.vo.RateType;

import java.util.List;

/**
 * Posted from CustProfileBaseRateModel
 * <p>
 * Author @author HungNT Apr 8, 2016
 */
public class CustProfileBaseRateModel extends BaseModel {
    private static final long serialVersionUID = -3651559320788683588L;

    private String customerProfileBaseRateId;
    private String profileId;
    private String shipmentTypeId;
    private String rateType;
    private String weight;
    private String rate;
    private String zoneCheck;
    private String content;
    private String bound;
    private String baserateDescription;
    private String carrierId;
    private List<CustProfileBaseRateDetailModel> custProfileBaseRateDetails;
    private List<RateType> rateTypes;

    public String getCustomerProfileBaseRateId() {
        return customerProfileBaseRateId;
    }

    public void setCustomerProfileBaseRateId(String customerProfileBaseRateId) {
        this.customerProfileBaseRateId = customerProfileBaseRateId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
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

    public List<CustProfileBaseRateDetailModel> getCustProfileBaseRateDetails() {
        return custProfileBaseRateDetails;
    }

    public void setCustProfileBaseRateDetails(List<CustProfileBaseRateDetailModel> custProfileBaseRateDetails) {
        this.custProfileBaseRateDetails = custProfileBaseRateDetails;
    }

    public List<RateType> getRateTypes() {
        return rateTypes;
    }

    public void setRateTypes(List<RateType> rateTypes) {
        this.rateTypes = rateTypes;
    }

    @Override
    public String toString() {
        return "CustProfileBaseRateModel [customerProfileBaseRateId=" + customerProfileBaseRateId + ", profileId=" + profileId + ", shipmentTypeId=" + shipmentTypeId + ", rateType=" + rateType + ", weight=" + weight + ", rate=" + rate + ", zoneCheck=" + zoneCheck + ", content=" + content + ", bound=" + bound + ", baserateDescription=" + baserateDescription + ", carrierId=" + carrierId + ", custProfileBaseRateDetails=" + custProfileBaseRateDetails + ", rateTypes=" + rateTypes + "]";
    }
}
