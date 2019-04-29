package com.gms.xms.txndb.vo.admin.customerprofile.baserate;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.RateType;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * Posted from CustProfileBaseRateVo
 * <p>
 * Author @author HungNT Apr 8, 2016
 */
public class CustProfileBaseRateVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long customerProfileBaseRateId;
    private Long profileId;
    private Integer shipmentTypeId;
    private Integer rateType;
    private Double weight;
    private Double rate;
    private Boolean zoneCheck;
    private Integer content;
    private Integer bound;
    private String baserateDescription;
    private Integer carrierId;
    private List<CustProfileBaseRateDetailVo> custProfileBaseRateDetails;
    private List<RateType> rateTypes;

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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
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

    public String getBaserateDescription() {
        return baserateDescription;
    }

    public void setBaserateDescription(String baserateDescription) {
        this.baserateDescription = baserateDescription;
    }

    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }

    public List<CustProfileBaseRateDetailVo> getCustProfileBaseRateDetails() {
        return custProfileBaseRateDetails;
    }

    public void setCustProfileBaseRateDetails(List<CustProfileBaseRateDetailVo> custProfileBaseRateDetails) {
        this.custProfileBaseRateDetails = custProfileBaseRateDetails;
    }

    @Override
    public String toString() {
        return "CustProfileBaseRateVo [customerProfileBaseRateId=" + customerProfileBaseRateId + ", profileId=" + profileId + ", shipmentTypeId=" + shipmentTypeId + ", rateType=" + rateType + ", weight=" + weight + ", rate=" + rate + ", zoneCheck=" + zoneCheck + ", content=" + content + ", bound=" + bound + ", baserateDescription=" + baserateDescription + ", carrierId=" + carrierId + ", custProfileBaseRateDetails=" + custProfileBaseRateDetails + ", rateTypes=" + rateTypes + "]";
    }

    public List<RateType> getRateTypes() {
        return rateTypes;
    }

    public void setRateTypes(List<RateType> rateTypes) {
        this.rateTypes = rateTypes;
    }
}
