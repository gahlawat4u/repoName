package com.gms.xms.model.admin.administration;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.account.customers.manage.ShipmentTypeSettingModel;
import com.gms.xms.txndb.vo.ServiceVo;

import java.util.List;

/**
 * Posted from CustomerProfileBaseRateDetailModel
 * <p>
 * Author TANDT 29-10-2015
 */
public class CustomerProfileBaseRateModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private String customerProfileBaseRateId;
    private String profileId;
    private String shipmentTypeId;
    private String rateType;
    private String weight;
    private String rate;
    private String zoneCheck;
    private String content;
    private String bound;
    private String baseRateDescription;
    private String carrierId;
    private ServiceVo service;
    private List<CustomerProfileBaseRateDetailModel> customerProfileBaseRateDetail;
    private ShipmentTypeSettingModel shipmentType;

    public ShipmentTypeSettingModel getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentTypeSettingModel shipmentType) {
        this.shipmentType = shipmentType;
    }

    public ServiceVo getService() {
        return service;
    }

    public void setService(ServiceVo service) {
        this.service = service;
    }

    public List<CustomerProfileBaseRateDetailModel> getCustomerProfileBaseRateDetail() {
        return customerProfileBaseRateDetail;
    }

    public void setCustomerProfileBaseRateDetail(List<CustomerProfileBaseRateDetailModel> customerProfileBaseRateDetail) {
        this.customerProfileBaseRateDetail = customerProfileBaseRateDetail;
    }

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

    public String getBaseRateDescription() {
        return baseRateDescription;
    }

    public void setBaseRateDescription(String baseRateDescription) {
        this.baseRateDescription = baseRateDescription;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    @Override
    public String toString() {
        return "CustomerProfileBaseRateModel [customerProfileBaseRateId=" + customerProfileBaseRateId + ", profileId=" + profileId + ", shipmentTypeId=" + shipmentTypeId + ", rateType=" + rateType + ", weight=" + weight + ", rate=" + rate + ", zoneCheck=" + zoneCheck + ", content=" + content + ", bound=" + bound + ", baseRateDescription=" + baseRateDescription + ", carrierId=" + carrierId + ", service=" + service + ", customerProfileBaseRateDetail=" + customerProfileBaseRateDetail
                + ", shipmentType=" + shipmentType + "]";
    }

}
