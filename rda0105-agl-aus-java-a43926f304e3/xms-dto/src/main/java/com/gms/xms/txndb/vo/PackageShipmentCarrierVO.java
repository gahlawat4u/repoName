package com.gms.xms.txndb.vo;

public class PackageShipmentCarrierVO extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3734111423930427567L;
	private Long pccID;
	private Integer serviceId;
	private String servicename;
	private String serviceCategory;
	private Integer shipmentTypeId;
	private String shipmentTypeName;
	private Integer packageId;
	private String packageName;
	private Integer statusBoth;
	private Integer defaultBoth;
	private Integer docStatus;
	private Integer docDefault;
	private Integer nonDocStatus;
	private Integer nonDocDefault;
	private String packageTypeCode;

	public Long getPccID() {
		return pccID;
	}

	public void setPccID(Long pccID) {
		this.pccID = pccID;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public Integer getShipmentTypeId() {
		return shipmentTypeId;
	}

	public void setShipmentTypeId(Integer shipmentTypeId) {
		this.shipmentTypeId = shipmentTypeId;
	}

	public String getShipmentTypeName() {
		return shipmentTypeName;
	}

	public void setShipmentTypeName(String shipmentTypeName) {
		this.shipmentTypeName = shipmentTypeName;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public Integer getStatusBoth() {
		return statusBoth;
	}

	public void setStatusBoth(Integer statusBoth) {
		this.statusBoth = statusBoth;
	}

	public Integer getDefaultBoth() {
		return defaultBoth;
	}

	public void setDefaultBoth(Integer defaultBoth) {
		this.defaultBoth = defaultBoth;
	}

	public Integer getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(Integer docStatus) {
		this.docStatus = docStatus;
	}

	public Integer getDocDefault() {
		return docDefault;
	}

	public void setDocDefault(Integer docDefault) {
		this.docDefault = docDefault;
	}

	public Integer getNonDocStatus() {
		return nonDocStatus;
	}

	public void setNonDocStatus(Integer nonDocStatus) {
		this.nonDocStatus = nonDocStatus;
	}

	public Integer getNonDocDefault() {
		return nonDocDefault;
	}

	public void setNonDocDefault(Integer nonDocDefault) {
		this.nonDocDefault = nonDocDefault;
	}
	
	
	public String getPackageTypeCode() {
		return packageTypeCode;
	}

	public void setPackageTypeCode(String packageTypeCode) {
		this.packageTypeCode = packageTypeCode;
	}

	@Override
	public String toString() {
		return "PackageShipmentCarrierVO [pccID=" + pccID + ", serviceId=" + serviceId + ", servicename=" + servicename
				+ ", serviceCategory=" + serviceCategory + ", shipmentTypeId=" + shipmentTypeId + ", shipmentTypeName="
				+ shipmentTypeName + ", packageId=" + packageId + ", packageName=" + packageName + ", statusBoth="
				+ statusBoth + ", defaultBoth=" + defaultBoth + ", docStatus=" + docStatus + ", docDefault="
				+ docDefault + ", nonDocStatus=" + nonDocStatus + ", nonDocDefault=" + nonDocDefault
				+ ", packageTypeCode=" + packageTypeCode + "]";
	}

	

}
