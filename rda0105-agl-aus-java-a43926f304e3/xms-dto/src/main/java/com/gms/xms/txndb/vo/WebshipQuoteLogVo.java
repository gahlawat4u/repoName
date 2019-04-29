package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDecimalString2DoubleDeserializer;
import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from WebshipQuoteLogVo
 * <p>
 * Author HungNT Date Mar 28, 2015
 */
public class WebshipQuoteLogVo extends BaseVo {
    private static final long serialVersionUID = -6435328152765105833L;

    private Long quoteId;

    private String quoteNumber;

    private Long customerCode;

    private Long webshipId;

    private Date quoteDate;

    private Integer senderAddressId;

    private Integer receiverAddressId;

    private Integer shipmentTypeId;

    private Integer packageId;

    private String contents;

    private String weightUnit;

    private String dimensionUnit;

    private Integer noOfPieces;

    private Double withInsurance;

    private Boolean quoteStatus;

    private Double baseCharge;

    private Boolean residentialDelivery;

    private Boolean residentialPickup;

    private Double nonStandardCharge;

    private Double manualHandlingSurcharge;

    private Double totalCharge;

    private Date shipDate;

    private String extraService;

    private String zone;

    private String ipAddress;

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Long getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(Long webshipId) {
        this.webshipId = webshipId;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getQuoteDate() {
        return quoteDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    public Integer getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(Integer senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    public Integer getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(Integer receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public Integer getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Integer noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getWithInsurance() {
        return withInsurance;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setWithInsurance(Double withInsurance) {
        this.withInsurance = withInsurance;
    }

    public Boolean getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(Boolean quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getBaseCharge() {
        return baseCharge;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setBaseCharge(Double baseCharge) {
        this.baseCharge = baseCharge;
    }

    public Boolean getResidentialDelivery() {
        return residentialDelivery;
    }

    public void setResidentialDelivery(Boolean residentialDelivery) {
        this.residentialDelivery = residentialDelivery;
    }

    public Boolean getResidentialPickup() {
        return residentialPickup;
    }

    public void setResidentialPickup(Boolean residentialPickup) {
        this.residentialPickup = residentialPickup;
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
    public Double getTotalCharge() {
        return totalCharge;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setTotalCharge(Double totalCharge) {
        this.totalCharge = totalCharge;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipDate() {
        return shipDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getExtraService() {
        return extraService;
    }

    public void setExtraService(String extraService) {
        this.extraService = extraService;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "WebshipQuoteLogVo [quoteId=" + quoteId + ", quoteNumber=" + quoteNumber + ", customerCode=" + customerCode + ", webshipId=" + webshipId + ", quoteDate=" + quoteDate + ", senderAddressId=" + senderAddressId + ", receiverAddressId=" + receiverAddressId + ", shipmentTypeId=" + shipmentTypeId + ", packageId=" + packageId + ", contents=" + contents + ", weightUnit=" + weightUnit + ", dimensionUnit=" + dimensionUnit + ", noOfPieces=" + noOfPieces + ", withInsurance=" + withInsurance
                + ", quoteStatus=" + quoteStatus + ", baseCharge=" + baseCharge + ", residentialDelivery=" + residentialDelivery + ", residentialPickup=" + residentialPickup + ", nonStandardCharge=" + nonStandardCharge + ", manualHandlingSurcharge=" + manualHandlingSurcharge + " , totalCharge=" + totalCharge + ", shipDate=" + shipDate + ", extraService=" + extraService + ", zone=" + zone + ", ipAddress=" + ipAddress + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((baseCharge == null) ? 0 : baseCharge.hashCode());
        result = prime * result + ((contents == null) ? 0 : contents.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((dimensionUnit == null) ? 0 : dimensionUnit.hashCode());
        result = prime * result + ((extraService == null) ? 0 : extraService.hashCode());
        result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        result = prime * result + ((noOfPieces == null) ? 0 : noOfPieces.hashCode());
        result = prime * result + ((nonStandardCharge == null) ? 0 : nonStandardCharge.hashCode());
        result = prime * result + ((manualHandlingSurcharge == null) ? 0 : manualHandlingSurcharge.hashCode());
        result = prime * result + ((packageId == null) ? 0 : packageId.hashCode());
        result = prime * result + ((quoteDate == null) ? 0 : quoteDate.hashCode());
        result = prime * result + ((quoteId == null) ? 0 : quoteId.hashCode());
        result = prime * result + ((quoteNumber == null) ? 0 : quoteNumber.hashCode());
        result = prime * result + ((quoteStatus == null) ? 0 : quoteStatus.hashCode());
        result = prime * result + ((receiverAddressId == null) ? 0 : receiverAddressId.hashCode());
        result = prime * result + ((residentialDelivery == null) ? 0 : residentialDelivery.hashCode());
        result = prime * result + ((residentialPickup == null) ? 0 : residentialPickup.hashCode());
        result = prime * result + ((senderAddressId == null) ? 0 : senderAddressId.hashCode());
        result = prime * result + ((shipDate == null) ? 0 : shipDate.hashCode());
        result = prime * result + ((shipmentTypeId == null) ? 0 : shipmentTypeId.hashCode());
        result = prime * result + ((totalCharge == null) ? 0 : totalCharge.hashCode());
        result = prime * result + ((webshipId == null) ? 0 : webshipId.hashCode());
        result = prime * result + ((weightUnit == null) ? 0 : weightUnit.hashCode());
        result = prime * result + ((withInsurance == null) ? 0 : withInsurance.hashCode());
        result = prime * result + ((zone == null) ? 0 : zone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WebshipQuoteLogVo other = (WebshipQuoteLogVo) obj;
        if (baseCharge == null) {
            if (other.baseCharge != null)
                return false;
        } else if (!baseCharge.equals(other.baseCharge))
            return false;
        if (contents == null) {
            if (other.contents != null)
                return false;
        } else if (!contents.equals(other.contents))
            return false;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (dimensionUnit == null) {
            if (other.dimensionUnit != null)
                return false;
        } else if (!dimensionUnit.equals(other.dimensionUnit))
            return false;
        if (extraService == null) {
            if (other.extraService != null)
                return false;
        } else if (!extraService.equals(other.extraService))
            return false;
        if (ipAddress == null) {
            if (other.ipAddress != null)
                return false;
        } else if (!ipAddress.equals(other.ipAddress))
            return false;
        if (noOfPieces == null) {
            if (other.noOfPieces != null)
                return false;
        } else if (!noOfPieces.equals(other.noOfPieces))
            return false;
        if (nonStandardCharge == null) {
            if (other.nonStandardCharge != null)
                return false;
        } else if (!nonStandardCharge.equals(other.nonStandardCharge))
            return false;
        if (manualHandlingSurcharge == null) {
            if (other.manualHandlingSurcharge != null)
                return false;
        } else if (!manualHandlingSurcharge.equals(other.manualHandlingSurcharge))
            return false;
        if (packageId == null) {
            if (other.packageId != null)
                return false;
        } else if (!packageId.equals(other.packageId))
            return false;
        if (quoteDate == null) {
            if (other.quoteDate != null)
                return false;
        } else if (!quoteDate.equals(other.quoteDate))
            return false;
        if (quoteId == null) {
            if (other.quoteId != null)
                return false;
        } else if (!quoteId.equals(other.quoteId))
            return false;
        if (quoteNumber == null) {
            if (other.quoteNumber != null)
                return false;
        } else if (!quoteNumber.equals(other.quoteNumber))
            return false;
        if (quoteStatus == null) {
            if (other.quoteStatus != null)
                return false;
        } else if (!quoteStatus.equals(other.quoteStatus))
            return false;
        if (receiverAddressId == null) {
            if (other.receiverAddressId != null)
                return false;
        } else if (!receiverAddressId.equals(other.receiverAddressId))
            return false;
        if (residentialDelivery == null) {
            if (other.residentialDelivery != null)
                return false;
        } else if (!residentialDelivery.equals(other.residentialDelivery))
            return false;
        if (residentialPickup == null) {
            if (other.residentialPickup != null)
                return false;
        } else if (!residentialPickup.equals(other.residentialPickup))
            return false;
        if (senderAddressId == null) {
            if (other.senderAddressId != null)
                return false;
        } else if (!senderAddressId.equals(other.senderAddressId))
            return false;
        if (shipDate == null) {
            if (other.shipDate != null)
                return false;
        } else if (!shipDate.equals(other.shipDate))
            return false;
        if (shipmentTypeId == null) {
            if (other.shipmentTypeId != null)
                return false;
        } else if (!shipmentTypeId.equals(other.shipmentTypeId))
            return false;
        if (totalCharge == null) {
            if (other.totalCharge != null)
                return false;
        } else if (!totalCharge.equals(other.totalCharge))
            return false;
        if (webshipId == null) {
            if (other.webshipId != null)
                return false;
        } else if (!webshipId.equals(other.webshipId))
            return false;
        if (weightUnit == null) {
            if (other.weightUnit != null)
                return false;
        } else if (!weightUnit.equals(other.weightUnit))
            return false;
        if (withInsurance == null) {
            if (other.withInsurance != null)
                return false;
        } else if (!withInsurance.equals(other.withInsurance))
            return false;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        return true;
    }

}