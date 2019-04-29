package com.gms.xms.txndb.vo.admin.customer.baserate;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.RateType;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * Posted from Apr 4, 2016 11:09:58 PM
 * <p>
 * Author dattrinh
 */
public class CustBaseRateVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long customerBaseRateId;
    private String customerCode;
    private Integer shipmentTypeId;
    private Integer rateType;
    private Double weight;
    private Double rate;
    private Boolean zoneCheck;
    private Integer content;
    private Integer bound;
    private String baserateDescription;
    private Integer carrierId;
    private List<CustBaseRateDetailVo> custBaseRateDetails;
    private List<RateType> rateTypes;

    public Long getCustomerBaseRateId() {
        return customerBaseRateId;
    }

    public void setCustomerBaseRateId(Long customerBaseRateId) {
        this.customerBaseRateId = customerBaseRateId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
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

    public List<CustBaseRateDetailVo> getCustBaseRateDetails() {
        return custBaseRateDetails;
    }

    public void setCustBaseRateDetails(List<CustBaseRateDetailVo> custBaseRateDetails) {
        this.custBaseRateDetails = custBaseRateDetails;
    }

    @Override
    public String toString() {
        return "CustBaseRateVo [customerBaseRateId=" + customerBaseRateId + ", customerCode=" + customerCode + ", shipmentTypeId=" + shipmentTypeId + ", rateType=" + rateType + ", weight=" + weight + ", rate=" + rate + ", zoneCheck=" + zoneCheck + ", content=" + content + ", bound=" + bound + ", baserateDescription=" + baserateDescription + ", carrierId=" + carrierId + ", custBaseRateDetails=" + custBaseRateDetails + ", rateTypes=" + rateTypes + "]";
    }

    public List<RateType> getRateTypes() {
        return rateTypes;
    }

    public void setRateTypes(List<RateType> rateTypes) {
        this.rateTypes = rateTypes;
    }
}
