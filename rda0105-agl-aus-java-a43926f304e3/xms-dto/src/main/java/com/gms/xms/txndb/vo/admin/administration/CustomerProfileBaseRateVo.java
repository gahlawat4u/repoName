package com.gms.xms.txndb.vo.admin.administration;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;

import java.util.List;

/**
 * Posted from CustomerProfileBaseRateDetailModel
 * <p>
 * Author TANDT 29-10-2015
 */
public class CustomerProfileBaseRateVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private Long customerProfileBaseRateId;
    private Long profileId;
    private Integer shipmentTypeId;
    private Integer rateType;
    private Double weight;
    private Double rate;
    private Boolean zoneCheck;
    private Integer content;
    private Integer bound;
    private String baseRateDescription;
    private Integer carrierId;
    private ServiceVo service;
    private List<CustomerProfileBaseRateDetailVo> customerProfileBaseRateDetail;
    private ShipmentTypeVo shipmentType;

    public ShipmentTypeVo getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentTypeVo shipmentType) {
        this.shipmentType = shipmentType;
    }

    public List<CustomerProfileBaseRateDetailVo> getCustomerProfileBaseRateDetail() {
        return customerProfileBaseRateDetail;
    }

    public void setCustomerProfileBaseRateDetail(List<CustomerProfileBaseRateDetailVo> customerProfileBaseRateDetail) {
        this.customerProfileBaseRateDetail = customerProfileBaseRateDetail;
    }

    public ServiceVo getService() {
        return service;
    }

    public void setService(ServiceVo service) {
        this.service = service;
    }

    public Long getCustomerProfileBaseRateId() {
        return customerProfileBaseRateId;
    }

    public void setCustomerProfileBaseRateId(Long customerProfileBaseRateId) {
        this.customerProfileBaseRateId = customerProfileBaseRateId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
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

    public Boolean getZoneCheck() {
        return zoneCheck;
    }

    public void setZoneCheck(Boolean zoneCheck) {
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

    public String getBaseRateDescription() {
        return baseRateDescription;
    }

    public void setBaseRateDescription(String baseRateDescription) {
        this.baseRateDescription = baseRateDescription;
    }

    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }

    @Override
    public String toString() {
        return "CustomerProfileBaseRateVo [customerProfileBaseRateId=" + customerProfileBaseRateId + ", profileId=" + profileId + ", shipmentTypeId=" + shipmentTypeId + ", rateType=" + rateType + ", weight=" + weight + ", rate=" + rate + ", zoneCheck=" + zoneCheck + ", content=" + content + ", bound=" + bound + ", baseRateDescription=" + baseRateDescription + ", carrierId=" + carrierId + ", service=" + service + ", customerProfileBaseRateDetail=" + customerProfileBaseRateDetail
                + ", shipmentType=" + shipmentType + "]";
    }
}
