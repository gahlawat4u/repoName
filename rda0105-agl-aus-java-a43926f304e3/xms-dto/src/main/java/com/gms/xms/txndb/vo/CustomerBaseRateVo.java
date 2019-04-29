package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonString2DoubleDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * Posted from CustomerBaseRateVo
 * <p>
 * Author HungNT Date Apr 22, 2015
 */
public class CustomerBaseRateVo extends BaseVo {
    private static final long serialVersionUID = -6249185292723233015L;

    private Long customerBaseRateId;

    private Long customerCode;

    private Integer shipmentTypeId;

    private Integer rateType;

    private Double weight;

    private Double rate;

    private Boolean zoneCheck;

    private Integer content;

    private Integer bound;

    private String baserateDescription;

    private Integer carrierId;

    private List<CustomerBaseRateDetailVo> customerBaseRateDetails;

    private List<RateType> rateTypes;

    @Override
    public String toString() {
        return "CustomerBaseRateVo [customerBaseRateId=" + customerBaseRateId + ", customerCode=" + customerCode + ", shipmentTypeId=" + shipmentTypeId + ", rateType=" + rateType + ", weight=" + weight + ", rate=" + rate + ", zoneCheck=" + zoneCheck + ", content=" + content + ", bound=" + bound + ", baserateDescription=" + baserateDescription + ", carrierId=" + carrierId + ", customerBaseRateDetails=" + customerBaseRateDetails + ", rateTypes=" + rateTypes + "]";
    }

    public Long getCustomerBaseRateId() {
        return customerBaseRateId;
    }

    public void setCustomerBaseRateId(Long customerBaseRateId) {
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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getWeight() {
        return weight;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRate() {
        return rate;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
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

    public List<CustomerBaseRateDetailVo> getCustomerBaseRateDetails() {
        return customerBaseRateDetails;
    }

    public void setCustomerBaseRateDetails(List<CustomerBaseRateDetailVo> customerBaseRateDetails) {
        this.customerBaseRateDetails = customerBaseRateDetails;
    }

    public List<RateType> getRateTypes() {
        return rateTypes;
    }

    public void setRateTypes(List<RateType> rateTypes) {
        this.rateTypes = rateTypes;
    }

}