package com.gms.xms.txndb.vo.webship.quotejob;

import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * Posted from QuoteJobVo
 * <p>
 * Author HungNT Date Mar 30, 2015
 */
public class QuoteJobVo extends WebshipQuoteLogVo {

    private static final long serialVersionUID = 3039172160135436062L;

    private CustomerAddressVo customerAddress;

    private AddressVo senderAddress;

    private AddressVo receiverAddress;

    private ShipmentTypeVo shipmentType;

    private PackageVo packageType;

    private List<WebshipQuoteLogDetailVo> quoteLogDetails;

    private List<QuotePieceVo> quotePieces;

    private CountryVo senderCountry;

    private CountryVo receiverCountry;

    private Double actualWeight;

    private Double dimWeight;

    public CustomerAddressVo getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddressVo customerAddress) {
        this.customerAddress = customerAddress;
    }

    public AddressVo getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(AddressVo senderAddress) {
        this.senderAddress = senderAddress;
    }

    public AddressVo getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(AddressVo receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public ShipmentTypeVo getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentTypeVo shipmentType) {
        this.shipmentType = shipmentType;
    }

    public PackageVo getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageVo packageType) {
        this.packageType = packageType;
    }

    public List<WebshipQuoteLogDetailVo> getQuoteLogDetails() {
        return quoteLogDetails;
    }

    public void setQuoteLogDetails(List<WebshipQuoteLogDetailVo> quoteLogDetails) {
        this.quoteLogDetails = quoteLogDetails;
    }

    public List<QuotePieceVo> getQuotePieces() {
        return quotePieces;
    }

    public void setQuotePieces(List<QuotePieceVo> quotePieces) {
        this.quotePieces = quotePieces;
    }

    public CountryVo getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(CountryVo senderCountry) {
        this.senderCountry = senderCountry;
    }

    public CountryVo getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(CountryVo receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Double actualWeight) {
        this.actualWeight = actualWeight;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getDimWeight() {
        return dimWeight;
    }

    public void setDimWeight(Double dimWeight) {
        this.dimWeight = dimWeight;
    }

    @Override
    public String toString() {
        return "QuoteJobVo [customerAddress=" + customerAddress + ", senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress + ", shipmentType=" + shipmentType + ", packageType=" + packageType + ", quoteLogDetails=" + quoteLogDetails + ", quotePieces=" + quotePieces + ", senderCountry=" + senderCountry + ", receiverCountry=" + receiverCountry + ", actualWeight=" + actualWeight + ", dimWeight=" + dimWeight + "]";
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
        QuoteJobVo other = (QuoteJobVo) obj;
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
