package com.gms.xms.txndb.vo.webship.ship;

import com.gms.xms.common.json.JsonDecimalString2DoubleDeserializer;
import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * Posted from GetQuoteVo
 * <p>
 * Author HungNT Date Apr 18, 2015
 */
public class QuoteVo extends BaseVo {
    private static final long serialVersionUID = -1674737465120185194L;

    private Double baseCharge;
    private Double carrierCharge;
    private List<AccessorialVo> accessorial;
    private Double totalCharge;
    private Double totalCustomValue;
    private Double weight;
    private String weightType;
    private Double nonStandardCharge;
    private Double manualHandlingSurcharge;
    private Double insuranceValue;

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getBaseCharge() {
        return baseCharge;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setBaseCharge(Double baseCharge) {
        this.baseCharge = baseCharge;
    }

    public List<AccessorialVo> getAccessorial() {
        return accessorial;
    }

    public void setAccessorial(List<AccessorialVo> accessorial) {
        this.accessorial = accessorial;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getTotalCharge() {
        return totalCharge;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setTotalCharge(Double totalCharge) {
        this.totalCharge = totalCharge;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getWeight() {
        return weight;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getWeightType() {
        return weightType;
    }

    public void setWeightType(String weightType) {
        this.weightType = weightType;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getTotalCustomValue() {
        return totalCustomValue;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setTotalCustomValue(Double totalCustomValue) {
        this.totalCustomValue = totalCustomValue;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getNonStandardCharge() {
        return nonStandardCharge;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setNonStandardCharge(Double nonStandardCharge) {
        this.nonStandardCharge = nonStandardCharge;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getManualHandlingSurcharge() {
        return manualHandlingSurcharge;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setManualHandlingSurcharge(Double manualHandlingSurcharge) {
        this.manualHandlingSurcharge = manualHandlingSurcharge;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getInsuranceValue() {
        return insuranceValue;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setInsuranceValue(Double insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public Double getCarrierCharge() {
        return carrierCharge;
    }

    public void setCarrierCharge(Double carrierCharge) {
        this.carrierCharge = carrierCharge;
    }

    @Override
    public String toString() {
        return "QuoteVo [baseCharge=" + baseCharge + ", carrierCharge=" + carrierCharge + ", accessorial=" + accessorial + ", totalCharge=" + totalCharge + ", totalCustomValue=" + totalCustomValue + ", weight=" + weight + ", weightType=" + weightType + ", nonStandardCharge=" + nonStandardCharge + ", insuranceValue=" + insuranceValue + "]";
    }
}
