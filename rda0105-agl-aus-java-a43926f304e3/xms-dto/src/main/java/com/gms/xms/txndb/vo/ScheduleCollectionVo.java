package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.common.json.JsonTime2StringSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from ScheduleCollectionVo
 * <p>
 * Author TanDT Date Mar 27, 2015
 */
public class ScheduleCollectionVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -6208532383294526230L;

    private Long scheduleCollectionId;

    private Date pickupDate;

    private String pickupTime;

    private String pickupTimeNoLater;

    private Integer noOfPieces;

    private Float totalWeight;

    private String scheduleWeightUnit;

    private String specialInstructions;

    private Integer addressId;

    private String confirmationNo;

    private Boolean location;

    private Integer locationCodeId;

    private String description;

    private Long shipmentId;

    private Byte status;

    private Date createDate;

    private ShipmentVo shipment;

    private AddressVo saddress;

    private CountryVo scountry;

    private AddressVo raddress;

    private CountryVo rcountry;

    private AddressVo pickupAddress;

    public Long getScheduleCollectionId() {
        return scheduleCollectionId;
    }

    public void setScheduleCollectionId(Long scheduleCollectionId) {
        this.scheduleCollectionId = scheduleCollectionId;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getPickupDate() {
        return pickupDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    @JsonSerialize(using = JsonTime2StringSerializer.class)
    public String getPickupTimeNoLater() {
        return pickupTimeNoLater;
    }

    public void setPickupTimeNoLater(String pickupTimeNoLater) {
        this.pickupTimeNoLater = pickupTimeNoLater;
    }

    @JsonSerialize(using = JsonTime2StringSerializer.class)
    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Integer getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Integer noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public Float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getScheduleWeightUnit() {
        return scheduleWeightUnit;
    }

    public void setScheduleWeightUnit(String scheduleWeightUnit) {
        this.scheduleWeightUnit = scheduleWeightUnit;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    public Boolean getLocation() {
        return location;
    }

    public void setLocation(Boolean location) {
        this.location = location;
    }

    public Integer getLocationCodeId() {
        return locationCodeId;
    }

    public void setLocationCodeId(Integer locationCodeId) {
        this.locationCodeId = locationCodeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ShipmentVo getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentVo shipment) {
        this.shipment = shipment;
    }

    public AddressVo getSaddress() {
        return saddress;
    }

    public void setSaddress(AddressVo saddress) {
        this.saddress = saddress;
    }

    public CountryVo getScountry() {
        return scountry;
    }

    public void setScountry(CountryVo scountry) {
        this.scountry = scountry;
    }

    public AddressVo getRaddress() {
        return raddress;
    }

    public void setRaddress(AddressVo raddress) {
        this.raddress = raddress;
    }

    public CountryVo getRcountry() {
        return rcountry;
    }

    public void setRcountry(CountryVo rcountry) {
        this.rcountry = rcountry;
    }

    @Override
    public String toString() {
        return "ScheduleCollectionVo [scheduleCollectionId=" + scheduleCollectionId + ", pickupDate=" + pickupDate + ", pickupTime=" + pickupTime + ", pickupTimeNoLater=" + pickupTimeNoLater + ", noOfPieces=" + noOfPieces + ", totalWeight=" + totalWeight + ", scheduleWeightUnit=" + scheduleWeightUnit + ", specialInstructions=" + specialInstructions + ", addressId=" + addressId + ", confirmationNo=" + confirmationNo + ", location=" + location + ", locationCodeId=" + locationCodeId
                + ", description=" + description + ", shipmentId=" + shipmentId + ", status=" + status + ", createDate=" + createDate + ", shipment=" + shipment + ", saddress=" + saddress + ", scountry=" + scountry + ", raddress=" + raddress + ", rcountry=" + rcountry + ", pickupAddress=" + pickupAddress + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
        result = prime * result + ((confirmationNo == null) ? 0 : confirmationNo.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((locationCodeId == null) ? 0 : locationCodeId.hashCode());
        result = prime * result + ((noOfPieces == null) ? 0 : noOfPieces.hashCode());
        result = prime * result + ((pickupDate == null) ? 0 : pickupDate.hashCode());
        result = prime * result + ((pickupTime == null) ? 0 : pickupTime.hashCode());
        result = prime * result + ((pickupTimeNoLater == null) ? 0 : pickupTimeNoLater.hashCode());
        result = prime * result + ((raddress == null) ? 0 : raddress.hashCode());
        result = prime * result + ((rcountry == null) ? 0 : rcountry.hashCode());
        result = prime * result + ((saddress == null) ? 0 : saddress.hashCode());
        result = prime * result + ((scheduleCollectionId == null) ? 0 : scheduleCollectionId.hashCode());
        result = prime * result + ((scheduleWeightUnit == null) ? 0 : scheduleWeightUnit.hashCode());
        result = prime * result + ((scountry == null) ? 0 : scountry.hashCode());
        result = prime * result + ((shipment == null) ? 0 : shipment.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((specialInstructions == null) ? 0 : specialInstructions.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((totalWeight == null) ? 0 : totalWeight.hashCode());
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
        ScheduleCollectionVo other = (ScheduleCollectionVo) obj;
        if (addressId == null) {
            if (other.addressId != null)
                return false;
        } else if (!addressId.equals(other.addressId))
            return false;
        if (confirmationNo == null) {
            if (other.confirmationNo != null)
                return false;
        } else if (!confirmationNo.equals(other.confirmationNo))
            return false;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (locationCodeId == null) {
            if (other.locationCodeId != null)
                return false;
        } else if (!locationCodeId.equals(other.locationCodeId))
            return false;
        if (noOfPieces == null) {
            if (other.noOfPieces != null)
                return false;
        } else if (!noOfPieces.equals(other.noOfPieces))
            return false;
        if (pickupDate == null) {
            if (other.pickupDate != null)
                return false;
        } else if (!pickupDate.equals(other.pickupDate))
            return false;
        if (pickupTime == null) {
            if (other.pickupTime != null)
                return false;
        } else if (!pickupTime.equals(other.pickupTime))
            return false;
        if (pickupTimeNoLater == null) {
            if (other.pickupTimeNoLater != null)
                return false;
        } else if (!pickupTimeNoLater.equals(other.pickupTimeNoLater))
            return false;
        if (raddress == null) {
            if (other.raddress != null)
                return false;
        } else if (!raddress.equals(other.raddress))
            return false;
        if (rcountry == null) {
            if (other.rcountry != null)
                return false;
        } else if (!rcountry.equals(other.rcountry))
            return false;
        if (saddress == null) {
            if (other.saddress != null)
                return false;
        } else if (!saddress.equals(other.saddress))
            return false;
        if (scheduleCollectionId == null) {
            if (other.scheduleCollectionId != null)
                return false;
        } else if (!scheduleCollectionId.equals(other.scheduleCollectionId))
            return false;
        if (scheduleWeightUnit == null) {
            if (other.scheduleWeightUnit != null)
                return false;
        } else if (!scheduleWeightUnit.equals(other.scheduleWeightUnit))
            return false;
        if (scountry == null) {
            if (other.scountry != null)
                return false;
        } else if (!scountry.equals(other.scountry))
            return false;
        if (shipment == null) {
            if (other.shipment != null)
                return false;
        } else if (!shipment.equals(other.shipment))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (specialInstructions == null) {
            if (other.specialInstructions != null)
                return false;
        } else if (!specialInstructions.equals(other.specialInstructions))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (totalWeight == null) {
            if (other.totalWeight != null)
                return false;
        } else if (!totalWeight.equals(other.totalWeight))
            return false;
        return true;
    }

    public AddressVo getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(AddressVo pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

}