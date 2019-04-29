package com.gms.xms.model.webship;

import java.util.List;

import com.gms.xms.txndb.vo.PieceVo;

public class UPSSchedulingModel {
	
	private String serviceCode;
	private String airbill;
	private String billingAccount;
	private String pickupDate;
	private String readyTime;
	private String closeTime;
	private String companyName;
	private String city;
	private String contactName;
	private String addressLine;
	private String stateProvince;
	private String postalCode;
	private String countryCode;
	private String countryName;
	private String weightUnit;
	private String phone;
	private String totalWeight;
	private List<PieceVo> pieceList;
	private String toCountryCode;
	private String specialInstruction;
	private PieceVo pieceVo;
	private String authenticationKey ;

	
	
	public String getAuthenticationKey() {
		return authenticationKey;
	}
	public void setAuthenticationKey(String authenticationKey) {
		this.authenticationKey = authenticationKey;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getAirbill() {
		return airbill;
	}
	public void setAirbill(String airbill) {
		this.airbill = airbill;
	}
	public String getBillingAccount() {
		return billingAccount;
	}
	public void setBillingAccount(String billingAccount) {
		this.billingAccount = billingAccount;
	}
	public String getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}
	public String getReadyTime() {
		return readyTime;
	}
	public void setReadyTime(String readyTime) {
		this.readyTime = readyTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getStateProvince() {
		return stateProvince;
	}
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}
	public List<PieceVo> getPieceList() {
		return pieceList;
	}
	public void setPieceList(List<PieceVo> pieceList) {
		this.pieceList = pieceList;
	}
	public String getToCountryCode() {
		return toCountryCode;
	}
	public void setToCountryCode(String toCountryCode) {
		this.toCountryCode = toCountryCode;
	}
	public String getSpecialInstruction() {
		return specialInstruction;
	}
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	public PieceVo getPieceVo() {
		return pieceVo;
	}
	public void setPieceVo(PieceVo pieceVo) {
		this.pieceVo = pieceVo;
	}
	
	
	
}
