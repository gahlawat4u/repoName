package com.gms.xms.model;

import com.gms.xms.model.webship.AddressModel;

/**
 * Posted from ShipmentBillingModel
 * <p>
 * Author TanDT Date May 28, 2015
 */
public class ShipmentBillingModel extends BaseModel {

    private static final long serialVersionUID = 2535476349979872357L;

    private String shipmentId;

    private String paid;

    private String paymentDate;

    private String airbillNumber;

    private String airbillNumberEdi;

    private String description;

    private String displayDescription;

    private String packageFlag;

    private String pal;

    private String calculatedCarrierCost;

    private String calculatedFranchiseCost;

    private String carrierCost;

    private String customerCost;

    private String weight;

    private String insuranceAmount;

    private String grossDiscountAmount;

    private String taxAmount;

    private String customerTaxAmount;

    private String gstPercent;

    private String carrierTaxPercent;

    private String customerTaxPercent;

    private String taxCode;

    private String insuranceDiscountAmount;

    private String insuranceTaxAmount;

    private String franchiseCost;

    private String franchiseTaxAmount;

    private String weightUnit;

    private String shipperReference;

    private String carrier;

    private String importDate;

    private String invoiceDate;

    private String originCountryId;

    private String destinationCountryId;

    private String oldCarrierCost;

    private String oldTaxAmount;

    private String shipDate;

    private String senderAddressId;

    private String receiverAddressId;

    private String billingType;

    private String billingAccount;

    private String billToAccount;

    private String serviceAreaCodeDestination;

    private String serviceAreaCodeOrigin;

    private String downloadFileId;

    private String isBaseCharge;

    private String accessorialCount;

    private String oldTotalAccessorialCount;

    private String requireCalculate;

    private String zone;

    private String billActualDimension;

    private String billingBound;

    private String billingShipmentTypeId;

    private String billingContents;

    private String billingReweightWeight;

    private String billingReference2;

    private String billingReference3;

    private String billingReceivedBy;

    private String billingReceivedDate;

    private String billingFreightClass;

    private AddressModel rAddress;

    private AddressModel sAddress;

    public AddressModel getrAddress() {
        return rAddress;
    }

    public void setrAddress(AddressModel rAddress) {
        this.rAddress = rAddress;
    }

    public AddressModel getsAddress() {
        return sAddress;
    }

    public void setsAddress(AddressModel sAddress) {
        this.sAddress = sAddress;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getAirbillNumberEdi() {
        return airbillNumberEdi;
    }

    public void setAirbillNumberEdi(String airbillNumberEdi) {
        this.airbillNumberEdi = airbillNumberEdi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    public String getPackageFlag() {
        return packageFlag;
    }

    public void setPackageFlag(String packageFlag) {
        this.packageFlag = packageFlag;
    }

    public String getPal() {
        return pal;
    }

    public void setPal(String pal) {
        this.pal = pal;
    }

    public String getCalculatedCarrierCost() {
        return calculatedCarrierCost;
    }

    public void setCalculatedCarrierCost(String calculatedCarrierCost) {
        this.calculatedCarrierCost = calculatedCarrierCost;
    }

    public String getCalculatedFranchiseCost() {
        return calculatedFranchiseCost;
    }

    public void setCalculatedFranchiseCost(String calculatedFranchiseCost) {
        this.calculatedFranchiseCost = calculatedFranchiseCost;
    }

    public String getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(String carrierCost) {
        this.carrierCost = carrierCost;
    }

    public String getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(String customerCost) {
        this.customerCost = customerCost;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(String insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public String getGrossDiscountAmount() {
        return grossDiscountAmount;
    }

    public void setGrossDiscountAmount(String grossDiscountAmount) {
        this.grossDiscountAmount = grossDiscountAmount;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getCustomerTaxAmount() {
        return customerTaxAmount;
    }

    public void setCustomerTaxAmount(String customerTaxAmount) {
        this.customerTaxAmount = customerTaxAmount;
    }

    public String getGstPercent() {
        return gstPercent;
    }

    public void setGstPercent(String gstPercent) {
        this.gstPercent = gstPercent;
    }

    public String getCarrierTaxPercent() {
        return carrierTaxPercent;
    }

    public void setCarrierTaxPercent(String carrierTaxPercent) {
        this.carrierTaxPercent = carrierTaxPercent;
    }

    public String getCustomerTaxPercent() {
        return customerTaxPercent;
    }

    public void setCustomerTaxPercent(String customerTaxPercent) {
        this.customerTaxPercent = customerTaxPercent;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getInsuranceDiscountAmount() {
        return insuranceDiscountAmount;
    }

    public void setInsuranceDiscountAmount(String insuranceDiscountAmount) {
        this.insuranceDiscountAmount = insuranceDiscountAmount;
    }

    public String getInsuranceTaxAmount() {
        return insuranceTaxAmount;
    }

    public void setInsuranceTaxAmount(String insuranceTaxAmount) {
        this.insuranceTaxAmount = insuranceTaxAmount;
    }

    public String getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(String franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    public String getFranchiseTaxAmount() {
        return franchiseTaxAmount;
    }

    public void setFranchiseTaxAmount(String franchiseTaxAmount) {
        this.franchiseTaxAmount = franchiseTaxAmount;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getShipperReference() {
        return shipperReference;
    }

    public void setShipperReference(String shipperReference) {
        this.shipperReference = shipperReference;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getOldCarrierCost() {
        return oldCarrierCost;
    }

    public void setOldCarrierCost(String oldCarrierCost) {
        this.oldCarrierCost = oldCarrierCost;
    }

    public String getOldTaxAmount() {
        return oldTaxAmount;
    }

    public void setOldTaxAmount(String oldTaxAmount) {
        this.oldTaxAmount = oldTaxAmount;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
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

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public String getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(String billingAccount) {
        this.billingAccount = billingAccount;
    }

    public String getOriginCountryId() {
        return originCountryId;
    }

    public void setOriginCountryId(String originCountryId) {
        this.originCountryId = originCountryId;
    }

    public String getDestinationCountryId() {
        return destinationCountryId;
    }

    public void setDestinationCountryId(String destinationCountryId) {
        this.destinationCountryId = destinationCountryId;
    }

    public String getBillToAccount() {
        return billToAccount;
    }

    public void setBillToAccount(String billToAccount) {
        this.billToAccount = billToAccount;
    }

    public String getServiceAreaCodeDestination() {
        return serviceAreaCodeDestination;
    }

    public void setServiceAreaCodeDestination(String serviceAreaCodeDestination) {
        this.serviceAreaCodeDestination = serviceAreaCodeDestination;
    }

    public String getServiceAreaCodeOrigin() {
        return serviceAreaCodeOrigin;
    }

    public void setServiceAreaCodeOrigin(String serviceAreaCodeOrigin) {
        this.serviceAreaCodeOrigin = serviceAreaCodeOrigin;
    }

    public String getDownloadFileId() {
        return downloadFileId;
    }

    public void setDownloadFileId(String downloadFileId) {
        this.downloadFileId = downloadFileId;
    }

    public String getIsBaseCharge() {
        return isBaseCharge;
    }

    public void setIsBaseCharge(String isBaseCharge) {
        this.isBaseCharge = isBaseCharge;
    }

    public String getAccessorialCount() {
        return accessorialCount;
    }

    public void setAccessorialCount(String accessorialCount) {
        this.accessorialCount = accessorialCount;
    }

    public String getOldTotalAccessorialCount() {
        return oldTotalAccessorialCount;
    }

    public void setOldTotalAccessorialCount(String oldTotalAccessorialCount) {
        this.oldTotalAccessorialCount = oldTotalAccessorialCount;
    }

    public String getRequireCalculate() {
        return requireCalculate;
    }

    public void setRequireCalculate(String requireCalculate) {
        this.requireCalculate = requireCalculate;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getBillActualDimension() {
        return billActualDimension;
    }

    public void setBillActualDimension(String billActualDimension) {
        this.billActualDimension = billActualDimension;
    }

    public String getBillingBound() {
        return billingBound;
    }

    public void setBillingBound(String billingBound) {
        this.billingBound = billingBound;
    }

    public String getBillingShipmentTypeId() {
        return billingShipmentTypeId;
    }

    public void setBillingShipmentTypeId(String billingShipmentTypeId) {
        this.billingShipmentTypeId = billingShipmentTypeId;
    }

    public String getBillingContents() {
        return billingContents;
    }

    public void setBillingContents(String billingContents) {
        this.billingContents = billingContents;
    }

    public String getBillingReweightWeight() {
        return billingReweightWeight;
    }

    public void setBillingReweightWeight(String billingReweightWeight) {
        this.billingReweightWeight = billingReweightWeight;
    }

    public String getBillingReference2() {
        return billingReference2;
    }

    public void setBillingReference2(String billingReference2) {
        this.billingReference2 = billingReference2;
    }

    public String getBillingReference3() {
        return billingReference3;
    }

    public void setBillingReference3(String billingReference3) {
        this.billingReference3 = billingReference3;
    }

    public String getBillingReceivedBy() {
        return billingReceivedBy;
    }

    public void setBillingReceivedBy(String billingReceivedBy) {
        this.billingReceivedBy = billingReceivedBy;
    }

    public String getBillingReceivedDate() {
        return billingReceivedDate;
    }

    public void setBillingReceivedDate(String billingReceivedDate) {
        this.billingReceivedDate = billingReceivedDate;
    }

    public String getBillingFreightClass() {
        return billingFreightClass;
    }

    public void setBillingFreightClass(String billingFreightClass) {
        this.billingFreightClass = billingFreightClass;
    }

    @Override
    public String toString() {
        return "ShipmentBillingModel [shipmentId=" + shipmentId + ", paid=" + paid + ", paymentDate=" + paymentDate + ", airbillNumber=" + airbillNumber + ", airbillNumberEdi=" + airbillNumberEdi + ", description=" + description + ", displayDescription=" + displayDescription + ", packageFlag=" + packageFlag + ", pal=" + pal + ", calculatedCarrierCost=" + calculatedCarrierCost + ", calculatedFranchiseCost=" + calculatedFranchiseCost + ", carrierCost=" + carrierCost + ", customerCost="
                + customerCost + ", weight=" + weight + ", insuranceAmount=" + insuranceAmount + ", grossDiscountAmount=" + grossDiscountAmount + ", taxAmount=" + taxAmount + ", customerTaxAmount=" + customerTaxAmount + ", gstPercent=" + gstPercent + ", carrierTaxPercent=" + carrierTaxPercent + ", customerTaxPercent=" + customerTaxPercent + ", taxCode=" + taxCode + ", insuranceDiscountAmount=" + insuranceDiscountAmount + ", insuranceTaxAmount=" + insuranceTaxAmount + ", franchiseCost="
                + franchiseCost + ", franchiseTaxAmount=" + franchiseTaxAmount + ", weightUnit=" + weightUnit + ", shipperReference=" + shipperReference + ", carrier=" + carrier + ", importDate=" + importDate + ", invoiceDate=" + invoiceDate + ", originCountryId=" + originCountryId + ", destinationCountryId=" + destinationCountryId + ", oldCarrierCost=" + oldCarrierCost + ", oldTaxAmount=" + oldTaxAmount + ", shipDate=" + shipDate + ", senderAddressId=" + senderAddressId + ", receiverAddressId="
                + receiverAddressId + ", billingType=" + billingType + ", billingAccount=" + billingAccount + ", billToAccount=" + billToAccount + ", serviceAreaCodeDestination=" + serviceAreaCodeDestination + ", serviceAreaCodeOrigin=" + serviceAreaCodeOrigin + ", downloadFileId=" + downloadFileId + ", isBaseCharge=" + isBaseCharge + ", accessorialCount=" + accessorialCount + ", oldTotalAccessorialCount=" + oldTotalAccessorialCount + ", requireCalculate=" + requireCalculate + ", zone=" + zone
                + ", billActualDimension=" + billActualDimension + ", billingBound=" + billingBound + ", billingShipmentTypeId=" + billingShipmentTypeId + ", billingContents=" + billingContents + ", billingReweightWeight=" + billingReweightWeight + ", billingReference2=" + billingReference2 + ", billingReference3=" + billingReference3 + ", billingReceivedBy=" + billingReceivedBy + ", billingReceivedDate=" + billingReceivedDate + ", billingFreightClass=" + billingFreightClass + ", rAddress="
                + rAddress + ", sAddress=" + sAddress + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessorialCount == null) ? 0 : accessorialCount.hashCode());
        result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
        result = prime * result + ((airbillNumberEdi == null) ? 0 : airbillNumberEdi.hashCode());
        result = prime * result + ((billActualDimension == null) ? 0 : billActualDimension.hashCode());
        result = prime * result + ((billToAccount == null) ? 0 : billToAccount.hashCode());
        result = prime * result + ((billingAccount == null) ? 0 : billingAccount.hashCode());
        result = prime * result + ((billingBound == null) ? 0 : billingBound.hashCode());
        result = prime * result + ((billingContents == null) ? 0 : billingContents.hashCode());
        result = prime * result + ((billingFreightClass == null) ? 0 : billingFreightClass.hashCode());
        result = prime * result + ((billingReceivedBy == null) ? 0 : billingReceivedBy.hashCode());
        result = prime * result + ((billingReceivedDate == null) ? 0 : billingReceivedDate.hashCode());
        result = prime * result + ((billingReference2 == null) ? 0 : billingReference2.hashCode());
        result = prime * result + ((billingReference3 == null) ? 0 : billingReference3.hashCode());
        result = prime * result + ((billingReweightWeight == null) ? 0 : billingReweightWeight.hashCode());
        result = prime * result + ((billingShipmentTypeId == null) ? 0 : billingShipmentTypeId.hashCode());
        result = prime * result + ((billingType == null) ? 0 : billingType.hashCode());
        result = prime * result + ((calculatedCarrierCost == null) ? 0 : calculatedCarrierCost.hashCode());
        result = prime * result + ((calculatedFranchiseCost == null) ? 0 : calculatedFranchiseCost.hashCode());
        result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
        result = prime * result + ((carrierCost == null) ? 0 : carrierCost.hashCode());
        result = prime * result + ((carrierTaxPercent == null) ? 0 : carrierTaxPercent.hashCode());
        result = prime * result + ((customerCost == null) ? 0 : customerCost.hashCode());
        result = prime * result + ((customerTaxAmount == null) ? 0 : customerTaxAmount.hashCode());
        result = prime * result + ((customerTaxPercent == null) ? 0 : customerTaxPercent.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((destinationCountryId == null) ? 0 : destinationCountryId.hashCode());
        result = prime * result + ((displayDescription == null) ? 0 : displayDescription.hashCode());
        result = prime * result + ((downloadFileId == null) ? 0 : downloadFileId.hashCode());
        result = prime * result + ((franchiseCost == null) ? 0 : franchiseCost.hashCode());
        result = prime * result + ((franchiseTaxAmount == null) ? 0 : franchiseTaxAmount.hashCode());
        result = prime * result + ((grossDiscountAmount == null) ? 0 : grossDiscountAmount.hashCode());
        result = prime * result + ((gstPercent == null) ? 0 : gstPercent.hashCode());
        result = prime * result + ((importDate == null) ? 0 : importDate.hashCode());
        result = prime * result + ((insuranceAmount == null) ? 0 : insuranceAmount.hashCode());
        result = prime * result + ((insuranceDiscountAmount == null) ? 0 : insuranceDiscountAmount.hashCode());
        result = prime * result + ((insuranceTaxAmount == null) ? 0 : insuranceTaxAmount.hashCode());
        result = prime * result + ((invoiceDate == null) ? 0 : invoiceDate.hashCode());
        result = prime * result + ((isBaseCharge == null) ? 0 : isBaseCharge.hashCode());
        result = prime * result + ((oldCarrierCost == null) ? 0 : oldCarrierCost.hashCode());
        result = prime * result + ((oldTaxAmount == null) ? 0 : oldTaxAmount.hashCode());
        result = prime * result + ((oldTotalAccessorialCount == null) ? 0 : oldTotalAccessorialCount.hashCode());
        result = prime * result + ((originCountryId == null) ? 0 : originCountryId.hashCode());
        result = prime * result + ((packageFlag == null) ? 0 : packageFlag.hashCode());
        result = prime * result + ((paid == null) ? 0 : paid.hashCode());
        result = prime * result + ((pal == null) ? 0 : pal.hashCode());
        result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
        result = prime * result + ((rAddress == null) ? 0 : rAddress.hashCode());
        result = prime * result + ((receiverAddressId == null) ? 0 : receiverAddressId.hashCode());
        result = prime * result + ((requireCalculate == null) ? 0 : requireCalculate.hashCode());
        result = prime * result + ((sAddress == null) ? 0 : sAddress.hashCode());
        result = prime * result + ((senderAddressId == null) ? 0 : senderAddressId.hashCode());
        result = prime * result + ((serviceAreaCodeDestination == null) ? 0 : serviceAreaCodeDestination.hashCode());
        result = prime * result + ((serviceAreaCodeOrigin == null) ? 0 : serviceAreaCodeOrigin.hashCode());
        result = prime * result + ((shipDate == null) ? 0 : shipDate.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((shipperReference == null) ? 0 : shipperReference.hashCode());
        result = prime * result + ((taxAmount == null) ? 0 : taxAmount.hashCode());
        result = prime * result + ((taxCode == null) ? 0 : taxCode.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((weightUnit == null) ? 0 : weightUnit.hashCode());
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
        ShipmentBillingModel other = (ShipmentBillingModel) obj;
        if (accessorialCount == null) {
            if (other.accessorialCount != null)
                return false;
        } else if (!accessorialCount.equals(other.accessorialCount))
            return false;
        if (airbillNumber == null) {
            if (other.airbillNumber != null)
                return false;
        } else if (!airbillNumber.equals(other.airbillNumber))
            return false;
        if (airbillNumberEdi == null) {
            if (other.airbillNumberEdi != null)
                return false;
        } else if (!airbillNumberEdi.equals(other.airbillNumberEdi))
            return false;
        if (billActualDimension == null) {
            if (other.billActualDimension != null)
                return false;
        } else if (!billActualDimension.equals(other.billActualDimension))
            return false;
        if (billToAccount == null) {
            if (other.billToAccount != null)
                return false;
        } else if (!billToAccount.equals(other.billToAccount))
            return false;
        if (billingAccount == null) {
            if (other.billingAccount != null)
                return false;
        } else if (!billingAccount.equals(other.billingAccount))
            return false;
        if (billingBound == null) {
            if (other.billingBound != null)
                return false;
        } else if (!billingBound.equals(other.billingBound))
            return false;
        if (billingContents == null) {
            if (other.billingContents != null)
                return false;
        } else if (!billingContents.equals(other.billingContents))
            return false;
        if (billingFreightClass == null) {
            if (other.billingFreightClass != null)
                return false;
        } else if (!billingFreightClass.equals(other.billingFreightClass))
            return false;
        if (billingReceivedBy == null) {
            if (other.billingReceivedBy != null)
                return false;
        } else if (!billingReceivedBy.equals(other.billingReceivedBy))
            return false;
        if (billingReceivedDate == null) {
            if (other.billingReceivedDate != null)
                return false;
        } else if (!billingReceivedDate.equals(other.billingReceivedDate))
            return false;
        if (billingReference2 == null) {
            if (other.billingReference2 != null)
                return false;
        } else if (!billingReference2.equals(other.billingReference2))
            return false;
        if (billingReference3 == null) {
            if (other.billingReference3 != null)
                return false;
        } else if (!billingReference3.equals(other.billingReference3))
            return false;
        if (billingReweightWeight == null) {
            if (other.billingReweightWeight != null)
                return false;
        } else if (!billingReweightWeight.equals(other.billingReweightWeight))
            return false;
        if (billingShipmentTypeId == null) {
            if (other.billingShipmentTypeId != null)
                return false;
        } else if (!billingShipmentTypeId.equals(other.billingShipmentTypeId))
            return false;
        if (billingType == null) {
            if (other.billingType != null)
                return false;
        } else if (!billingType.equals(other.billingType))
            return false;
        if (calculatedCarrierCost == null) {
            if (other.calculatedCarrierCost != null)
                return false;
        } else if (!calculatedCarrierCost.equals(other.calculatedCarrierCost))
            return false;
        if (calculatedFranchiseCost == null) {
            if (other.calculatedFranchiseCost != null)
                return false;
        } else if (!calculatedFranchiseCost.equals(other.calculatedFranchiseCost))
            return false;
        if (carrier == null) {
            if (other.carrier != null)
                return false;
        } else if (!carrier.equals(other.carrier))
            return false;
        if (carrierCost == null) {
            if (other.carrierCost != null)
                return false;
        } else if (!carrierCost.equals(other.carrierCost))
            return false;
        if (carrierTaxPercent == null) {
            if (other.carrierTaxPercent != null)
                return false;
        } else if (!carrierTaxPercent.equals(other.carrierTaxPercent))
            return false;
        if (customerCost == null) {
            if (other.customerCost != null)
                return false;
        } else if (!customerCost.equals(other.customerCost))
            return false;
        if (customerTaxAmount == null) {
            if (other.customerTaxAmount != null)
                return false;
        } else if (!customerTaxAmount.equals(other.customerTaxAmount))
            return false;
        if (customerTaxPercent == null) {
            if (other.customerTaxPercent != null)
                return false;
        } else if (!customerTaxPercent.equals(other.customerTaxPercent))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (destinationCountryId == null) {
            if (other.destinationCountryId != null)
                return false;
        } else if (!destinationCountryId.equals(other.destinationCountryId))
            return false;
        if (displayDescription == null) {
            if (other.displayDescription != null)
                return false;
        } else if (!displayDescription.equals(other.displayDescription))
            return false;
        if (downloadFileId == null) {
            if (other.downloadFileId != null)
                return false;
        } else if (!downloadFileId.equals(other.downloadFileId))
            return false;
        if (franchiseCost == null) {
            if (other.franchiseCost != null)
                return false;
        } else if (!franchiseCost.equals(other.franchiseCost))
            return false;
        if (franchiseTaxAmount == null) {
            if (other.franchiseTaxAmount != null)
                return false;
        } else if (!franchiseTaxAmount.equals(other.franchiseTaxAmount))
            return false;
        if (grossDiscountAmount == null) {
            if (other.grossDiscountAmount != null)
                return false;
        } else if (!grossDiscountAmount.equals(other.grossDiscountAmount))
            return false;
        if (gstPercent == null) {
            if (other.gstPercent != null)
                return false;
        } else if (!gstPercent.equals(other.gstPercent))
            return false;
        if (importDate == null) {
            if (other.importDate != null)
                return false;
        } else if (!importDate.equals(other.importDate))
            return false;
        if (insuranceAmount == null) {
            if (other.insuranceAmount != null)
                return false;
        } else if (!insuranceAmount.equals(other.insuranceAmount))
            return false;
        if (insuranceDiscountAmount == null) {
            if (other.insuranceDiscountAmount != null)
                return false;
        } else if (!insuranceDiscountAmount.equals(other.insuranceDiscountAmount))
            return false;
        if (insuranceTaxAmount == null) {
            if (other.insuranceTaxAmount != null)
                return false;
        } else if (!insuranceTaxAmount.equals(other.insuranceTaxAmount))
            return false;
        if (invoiceDate == null) {
            if (other.invoiceDate != null)
                return false;
        } else if (!invoiceDate.equals(other.invoiceDate))
            return false;
        if (isBaseCharge == null) {
            if (other.isBaseCharge != null)
                return false;
        } else if (!isBaseCharge.equals(other.isBaseCharge))
            return false;
        if (oldCarrierCost == null) {
            if (other.oldCarrierCost != null)
                return false;
        } else if (!oldCarrierCost.equals(other.oldCarrierCost))
            return false;
        if (oldTaxAmount == null) {
            if (other.oldTaxAmount != null)
                return false;
        } else if (!oldTaxAmount.equals(other.oldTaxAmount))
            return false;
        if (oldTotalAccessorialCount == null) {
            if (other.oldTotalAccessorialCount != null)
                return false;
        } else if (!oldTotalAccessorialCount.equals(other.oldTotalAccessorialCount))
            return false;
        if (originCountryId == null) {
            if (other.originCountryId != null)
                return false;
        } else if (!originCountryId.equals(other.originCountryId))
            return false;
        if (packageFlag == null) {
            if (other.packageFlag != null)
                return false;
        } else if (!packageFlag.equals(other.packageFlag))
            return false;
        if (paid == null) {
            if (other.paid != null)
                return false;
        } else if (!paid.equals(other.paid))
            return false;
        if (pal == null) {
            if (other.pal != null)
                return false;
        } else if (!pal.equals(other.pal))
            return false;
        if (paymentDate == null) {
            if (other.paymentDate != null)
                return false;
        } else if (!paymentDate.equals(other.paymentDate))
            return false;
        if (rAddress == null) {
            if (other.rAddress != null)
                return false;
        } else if (!rAddress.equals(other.rAddress))
            return false;
        if (receiverAddressId == null) {
            if (other.receiverAddressId != null)
                return false;
        } else if (!receiverAddressId.equals(other.receiverAddressId))
            return false;
        if (requireCalculate == null) {
            if (other.requireCalculate != null)
                return false;
        } else if (!requireCalculate.equals(other.requireCalculate))
            return false;
        if (sAddress == null) {
            if (other.sAddress != null)
                return false;
        } else if (!sAddress.equals(other.sAddress))
            return false;
        if (senderAddressId == null) {
            if (other.senderAddressId != null)
                return false;
        } else if (!senderAddressId.equals(other.senderAddressId))
            return false;
        if (serviceAreaCodeDestination == null) {
            if (other.serviceAreaCodeDestination != null)
                return false;
        } else if (!serviceAreaCodeDestination.equals(other.serviceAreaCodeDestination))
            return false;
        if (serviceAreaCodeOrigin == null) {
            if (other.serviceAreaCodeOrigin != null)
                return false;
        } else if (!serviceAreaCodeOrigin.equals(other.serviceAreaCodeOrigin))
            return false;
        if (shipDate == null) {
            if (other.shipDate != null)
                return false;
        } else if (!shipDate.equals(other.shipDate))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (shipperReference == null) {
            if (other.shipperReference != null)
                return false;
        } else if (!shipperReference.equals(other.shipperReference))
            return false;
        if (taxAmount == null) {
            if (other.taxAmount != null)
                return false;
        } else if (!taxAmount.equals(other.taxAmount))
            return false;
        if (taxCode == null) {
            if (other.taxCode != null)
                return false;
        } else if (!taxCode.equals(other.taxCode))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        if (weightUnit == null) {
            if (other.weightUnit != null)
                return false;
        } else if (!weightUnit.equals(other.weightUnit))
            return false;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        return true;
    }

}