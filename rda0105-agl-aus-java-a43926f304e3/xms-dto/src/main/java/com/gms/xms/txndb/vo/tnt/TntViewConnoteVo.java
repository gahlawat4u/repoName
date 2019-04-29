package com.gms.xms.txndb.vo.tnt;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonString2DoubleDeserializer;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class TntViewConnoteVo extends BaseVo {
    private static final long serialVersionUID = 3253082301408559952L;

    private ShipmentVo shipment;
    private AddressVo senderAddress;
    private AddressVo receiverAddress;
    private Double totalWeight;
    private Double totalVolume;
    private String tntAccount;
    private String systemAddress;
    private ShipmentTypeVo shipmentType;
    private String logo;
    private String barCode;

    public ShipmentVo getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentVo shipment) {
        this.shipment = shipment;
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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalWeight() {
        return totalWeight;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalVolume() {
        return totalVolume;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setTotalVolume(Double totalVolume) {
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

    public ShipmentTypeVo getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentTypeVo shipmentType) {
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
        return "TntViewConnoteVo [shipment=" + shipment + ", senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress + ", totalWeight=" + totalWeight + ", totalVolume=" + totalVolume + ", tntAccount=" + tntAccount + ", systemAddress=" + systemAddress + ", shipmentType=" + shipmentType + ", logo=" + logo + ", barCode=" + barCode + "]";
    }
}
