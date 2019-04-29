package com.gms.xms.model.tnt;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.webship.AddressModel;
import com.gms.xms.model.webship.ShipmentModel;
import com.gms.xms.model.webship.ShipmentTypeModel;

public class TntViewConnoteModel extends BaseModel {
    private static final long serialVersionUID = 6314161435114003106L;

    private ShipmentModel shipment;
    private AddressModel senderAddress;
    private AddressModel receiverAddress;
    private String totalWeight;
    private String totalVolume;
    private String tntAccount;
    private String systemAddress;
    private ShipmentTypeModel shipmentType;
    private String logo;
    private String barCode;

    public ShipmentModel getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentModel shipment) {
        this.shipment = shipment;
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

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(String totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getTntAccount() {
        return tntAccount;
    }

    public void setTntAccount(String tntAccount) {
        this.tntAccount = tntAccount;
    }

    public String getSystemAddress() {
        return systemAddress;
    }

    public void setSystemAddress(String systemAddress) {
        this.systemAddress = systemAddress;
    }

    public ShipmentTypeModel getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentTypeModel shipmentType) {
        this.shipmentType = shipmentType;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Override
    public String toString() {
        return "TntViewConnoteModel [shipment=" + shipment + ", senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress + ", totalWeight=" + totalWeight + ", totalVolume=" + totalVolume + ", tntAccount=" + tntAccount + ", systemAddress=" + systemAddress + ", shipmentType=" + shipmentType + ", logo=" + logo + ", barCode=" + barCode + "]";
    }
}
