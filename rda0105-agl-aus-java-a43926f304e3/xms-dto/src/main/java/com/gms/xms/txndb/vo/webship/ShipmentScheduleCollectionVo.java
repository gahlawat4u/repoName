package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from ScheduleCollectionVo
 * <p>
 * Author TanDT Date Mar 27, 2015
 */
public class ShipmentScheduleCollectionVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -6208532383294526230L;

    private Long shipmentId;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
    private String customerCode;
    private Integer packageId;
    private Integer locationCodeId;
    private String description;
    private String confirmationNo;
    private String pickupDate;
    private String pickupTime;
    private String pickupTimeNoLater;
    private String totalWeight;
    private Integer shipmentTypeId;
    private String scheduleWeightUnit;
    private String specialInstructions;
    private Integer noOfPieces;
    private String contactName;
    private String countryCode;
    private String orgsvCode;
    private String companyName;
    private String phone;

    public ShipmentScheduleCollectionVo() {
    }

    public ShipmentScheduleCollectionVo(Long shipmentId, String address, String address2, String city, String state, String postalCode, String customerCode, Integer packageId, Integer locationCodeId, String description, String confirmationNo, String pickupDate, String pickupTime, String pickupTimeNoLater, String totalWeight, Integer shipmentTypeId, String scheduleWeightUnit, String specialInstructions, Integer noOfPieces, String contactName, String countryCode, String orgsvCode, String companyName, String phone) {
        this.shipmentId = shipmentId;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.customerCode = customerCode;
        this.packageId = packageId;
        this.locationCodeId = locationCodeId;
        this.description = description;
        this.confirmationNo = confirmationNo;
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.pickupTimeNoLater = pickupTimeNoLater;
        this.totalWeight = totalWeight;
        this.shipmentTypeId = shipmentTypeId;
        this.scheduleWeightUnit = scheduleWeightUnit;
        this.specialInstructions = specialInstructions;
        this.noOfPieces = noOfPieces;
        this.contactName = contactName;
        this.countryCode = countryCode;
        this.orgsvCode = orgsvCode;
        this.companyName = companyName;
        this.phone = phone;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
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

    public String getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getPickupTimeNoLater() {
        return pickupTimeNoLater;
    }

    public void setPickupTimeNoLater(String pickupTimeNoLater) {
        this.pickupTimeNoLater = pickupTimeNoLater;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
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

    public Integer getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Integer noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getOrgsvCode() {
        return orgsvCode;
    }

    public void setOrgsvCode(String orgsvCode) {
        this.orgsvCode = orgsvCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}