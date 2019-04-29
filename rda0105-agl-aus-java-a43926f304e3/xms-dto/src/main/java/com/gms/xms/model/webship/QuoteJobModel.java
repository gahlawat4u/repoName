package com.gms.xms.model.webship;

import com.gms.xms.model.CountryModel;
import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.QuotePieceModel;

import java.util.List;

/**
 * Posted from QuoteJobModel
 * <p>
 * Author HungNT Date Mar 30, 2015
 */
public class QuoteJobModel extends WebshipQuoteLogModel {
    private static final long serialVersionUID = 5265831421197609222L;

    private CustomerAddressModel customerAddress;

    private AddressModel senderAddress;

    private AddressModel receiverAddress;

    private ShipmentTypeModel shipmentType;

    private PackageModel packageType;

    private List<WebshipQuoteLogDetailModel> quoteLogDetails;

    private List<QuotePieceModel> quotePieces;

    private CountryModel senderCountry;

    private CountryModel receiverCountry;

    private String actualWeight;

    private String dimWeight;

    public CustomerAddressModel getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddressModel customerAddress) {
        this.customerAddress = customerAddress;
    }

    public AddressModel getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(AddressModel senderAddress) {
        this.senderAddress = senderAddress;
    }

    public AddressModel getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(AddressModel receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public ShipmentTypeModel getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentTypeModel shipmentType) {
        this.shipmentType = shipmentType;
    }

    public PackageModel getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageModel packageType) {
        this.packageType = packageType;
    }

    public List<WebshipQuoteLogDetailModel> getQuoteLogDetails() {
        return quoteLogDetails;
    }

    public void setQuoteLogDetails(List<WebshipQuoteLogDetailModel> quoteLogDetails) {
        this.quoteLogDetails = quoteLogDetails;
    }

    public List<QuotePieceModel> getQuotePieces() {
        return quotePieces;
    }

    public void setQuotePieces(List<QuotePieceModel> quotePieces) {
        this.quotePieces = quotePieces;
    }

    public CountryModel getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(CountryModel senderCountry) {
        this.senderCountry = senderCountry;
    }

    public CountryModel getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(CountryModel receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public String getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(String actualWeight) {
        this.actualWeight = actualWeight;
    }

    public String getDimWeight() {
        return dimWeight;
    }

    public void setDimWeight(String dimWeight) {
        this.dimWeight = dimWeight;
    }

    @Override
    public String toString() {
        return "QuoteJobModel [customerAddress=" + customerAddress + ", senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress + ", shipmentType=" + shipmentType + ", packageType=" + packageType + ", quoteLogDetails=" + quoteLogDetails + ", quotePieces=" + quotePieces + ", senderCountry=" + senderCountry + ", receiverCountry=" + receiverCountry + ", actualWeight=" + actualWeight + ", dimWeight=" + dimWeight + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((actualWeight == null) ? 0 : actualWeight.hashCode());
        result = prime * result + ((customerAddress == null) ? 0 : customerAddress.hashCode());
        result = prime * result + ((dimWeight == null) ? 0 : dimWeight.hashCode());
        result = prime * result + ((packageType == null) ? 0 : packageType.hashCode());
        result = prime * result + ((quoteLogDetails == null) ? 0 : quoteLogDetails.hashCode());
        result = prime * result + ((quotePieces == null) ? 0 : quotePieces.hashCode());
        result = prime * result + ((receiverAddress == null) ? 0 : receiverAddress.hashCode());
        result = prime * result + ((receiverCountry == null) ? 0 : receiverCountry.hashCode());
        result = prime * result + ((senderAddress == null) ? 0 : senderAddress.hashCode());
        result = prime * result + ((senderCountry == null) ? 0 : senderCountry.hashCode());
        result = prime * result + ((shipmentType == null) ? 0 : shipmentType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        QuoteJobModel other = (QuoteJobModel) obj;
        if (actualWeight == null) {
            if (other.actualWeight != null)
                return false;
        } else if (!actualWeight.equals(other.actualWeight))
            return false;
        if (customerAddress == null) {
            if (other.customerAddress != null)
                return false;
        } else if (!customerAddress.equals(other.customerAddress))
            return false;
        if (dimWeight == null) {
            if (other.dimWeight != null)
                return false;
        } else if (!dimWeight.equals(other.dimWeight))
            return false;
        if (packageType == null) {
            if (other.packageType != null)
                return false;
        } else if (!packageType.equals(other.packageType))
            return false;
        if (quoteLogDetails == null) {
            if (other.quoteLogDetails != null)
                return false;
        } else if (!quoteLogDetails.equals(other.quoteLogDetails))
            return false;
        if (quotePieces == null) {
            if (other.quotePieces != null)
                return false;
        } else if (!quotePieces.equals(other.quotePieces))
            return false;
        if (receiverAddress == null) {
            if (other.receiverAddress != null)
                return false;
        } else if (!receiverAddress.equals(other.receiverAddress))
            return false;
        if (receiverCountry == null) {
            if (other.receiverCountry != null)
                return false;
        } else if (!receiverCountry.equals(other.receiverCountry))
            return false;
        if (senderAddress == null) {
            if (other.senderAddress != null)
                return false;
        } else if (!senderAddress.equals(other.senderAddress))
            return false;
        if (senderCountry == null) {
            if (other.senderCountry != null)
                return false;
        } else if (!senderCountry.equals(other.senderCountry))
            return false;
        if (shipmentType == null) {
            if (other.shipmentType != null)
                return false;
        } else if (!shipmentType.equals(other.shipmentType))
            return false;
        return true;
    }
}
