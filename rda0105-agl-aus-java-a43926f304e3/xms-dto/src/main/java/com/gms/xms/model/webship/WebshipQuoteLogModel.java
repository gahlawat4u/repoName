package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

public class WebshipQuoteLogModel extends BaseModel {
    private static final long serialVersionUID = 7697145726372914056L;

    private String quoteId;

    private String quoteNumber;

    private String customerCode;

    private String webshipId;

    private String quoteDate;

    private String senderAddressId;

    private String receiverAddressId;

    private String shipmentTypeId;

    private String packageId;

    private String contents;

    private String weightUnit;

    private String dimensionUnit;

    private String noOfPieces;

    private String withInsurance;

    private String quoteStatus;

    private String baseCharge;

    private String residentialDelivery;

    private String residentialPickup;

    private String nonStandardCharge;

    private String manualHandlingSurcharge;

    private String totalCharge;

    private String shipDate;

    private String extraService;

    private String zone;

    private String ipAddress;

    @Override
    public String toString() {
        return "WebshipQuoteLogModel [quoteId=" + quoteId + ", quoteNumber=" + quoteNumber + ", customerCode=" + customerCode + ", webshipId=" + webshipId + ", quoteDate=" + quoteDate + ", senderAddressId=" + senderAddressId + ", receiverAddressId=" + receiverAddressId + ", shipmentTypeId=" + shipmentTypeId + ", packageId=" + packageId + ", contents=" + contents + ", weightUnit=" + weightUnit + ", dimensionUnit=" + dimensionUnit + ", noOfPieces=" + noOfPieces + ", withInsurance="
                + withInsurance + ", quoteStatus=" + quoteStatus + ", baseCharge=" + baseCharge + ", residentialDelivery=" + residentialDelivery + ", residentialPickup=" + residentialPickup + ", nonStandardCharge=" + nonStandardCharge + ", manualHandlingSurcharge=" + manualHandlingSurcharge + ",  totalCharge=" + totalCharge + ", shipDate=" + shipDate + ", extraService=" + extraService + ", zone=" + zone + ", ipAddress=" + ipAddress + "]";
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
        WebshipQuoteLogModel other = (WebshipQuoteLogModel) obj;
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

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(String webshipId) {
        this.webshipId = webshipId;
    }

    public String getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(String quoteDate) {
        this.quoteDate = quoteDate;
    }

    public String getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(String senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    public String getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(String receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
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

    public String getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(String noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public String getWithInsurance() {
        return withInsurance;
    }

    public void setWithInsurance(String withInsurance) {
        this.withInsurance = withInsurance;
    }

    public String getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(String quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    public String getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(String baseCharge) {
        this.baseCharge = baseCharge;
    }

    public String getResidentialDelivery() {
        return residentialDelivery;
    }

    public void setResidentialDelivery(String residentialDelivery) {
        this.residentialDelivery = residentialDelivery;
    }

    public String getResidentialPickup() {
        return residentialPickup;
    }

    public void setResidentialPickup(String residentialPickup) {
        this.residentialPickup = residentialPickup;
    }

    public String getNonStandardCharge() {
        return nonStandardCharge;
    }

    public void setNonStandardCharge(String nonStandardCharge) {
        this.nonStandardCharge = nonStandardCharge;
    }

    public String getManualHandlingSurcharge() {
        return manualHandlingSurcharge;
    }

    public void setManualHandlingSurcharge(String manualHandlingSurcharge) {
        this.manualHandlingSurcharge = manualHandlingSurcharge;
    }

    public String getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(String totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
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
}
