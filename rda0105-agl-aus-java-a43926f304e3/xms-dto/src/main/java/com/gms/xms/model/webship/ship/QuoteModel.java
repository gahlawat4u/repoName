package com.gms.xms.model.webship.ship;

import com.gms.xms.model.AccessorialModel;
import com.gms.xms.model.BaseModel;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * Posted from GetQuoteModel
 * <p>
 * Author HungNT Date Apr 18, 2015
 */
public class QuoteModel extends BaseModel {
    private static final long serialVersionUID = -3444666181165705591L;

    private String baseCharge;
    private String carrierCharge;
    private List<AccessorialModel> accessorial;
    private String totalCharge;
    private String totalCustomValue;
    private String weight;
    private String weightType;
    private String nonStandardCharge;
    private Double manualHandlingSurcharge;
    private String insuranceValue;
    @JsonIgnore
    private String weightFomated;
    @JsonIgnore
    private String baseChargeUnit;
    @JsonIgnore
    private String totalChargeUnit;
    @JsonIgnore
    private String weightUnit;

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getWeightFomated() {
        return weightFomated;
    }

    public void setWeightFomated(String weightFomated) {
        this.weightFomated = weightFomated;
    }

    public String getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(String baseCharge) {
        this.baseCharge = baseCharge;
    }

    public List<AccessorialModel> getAccessorial() {
        return accessorial;
    }

    public void setAccessorial(List<AccessorialModel> accessorial) {
        this.accessorial = accessorial;
    }

    public String getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(String totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getTotalCustomValue() {
        return totalCustomValue;
    }

    public void setTotalCustomValue(String totalCustomValue) {
        this.totalCustomValue = totalCustomValue;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightType() {
        return weightType;
    }

    public void setWeightType(String weightType) {
        this.weightType = weightType;
    }

    public String getBaseChargeUnit() {
        return baseChargeUnit;
    }

    public void setBaseChargeUnit(String baseChargeUnit) {
        this.baseChargeUnit = baseChargeUnit;
    }

    public String getTotalChargeUnit() {
        return totalChargeUnit;
    }

    public void setTotalChargeUnit(String totalChargeUnit) {
        this.totalChargeUnit = totalChargeUnit;
    }

    public String getNonStandardCharge() {
        return nonStandardCharge;
    }

    public void setNonStandardCharge(String nonStandardCharge) {
        this.nonStandardCharge = nonStandardCharge;
    }

    public Double getManualHandlingSurcharge() {
        return manualHandlingSurcharge;
    }

    public void setManualHandlingSurcharge(Double manualHandlingSurcharge) {
        this.manualHandlingSurcharge = manualHandlingSurcharge;
    }

    public String getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(String insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public String getCarrierCharge() {
        return carrierCharge;
    }

    public void setCarrierCharge(String carrierCharge) {
        this.carrierCharge = carrierCharge;
    }

    @Override
    public String toString() {
        return "QuoteModel [baseCharge=" + baseCharge + ", carrierCharge=" + carrierCharge + ", accessorial=" + accessorial + ", totalCharge=" + totalCharge + ", totalCustomValue=" + totalCustomValue + ", weight=" + weight + ", weightType=" + weightType + ", nonStandardCharge=" + nonStandardCharge + ", insuranceValue=" + insuranceValue + ", weightFomated=" + weightFomated + ", baseChargeUnit=" + baseChargeUnit + ", totalChargeUnit=" + totalChargeUnit + ", weightUnit=" + weightUnit + "]";
    }
}
