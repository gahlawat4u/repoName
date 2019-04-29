package com.gms.xms.model;

import com.gms.xms.model.webship.ShipmentTypeModel;

import java.util.List;

/**
 * Posted from ServiceModel
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class ServiceModel extends BaseModel {

    private static final long serialVersionUID = -1085303938742860014L;

    private String serviceId;

    private String serviceName;

    private String ftpServer;

    private String ftpUserName;

    private String ftpUserPassword;

    private String serverFolder;

    private String fileNamePrefix;

    private String fileCountryCode;

    private String inactive;

    private String allowChangeName;

    private String carrierType;

    private String integrated;

    private String nonCentralized;
    
    private String showorder;

    private List<ShipmentTypeModel> shipmentTypes;

    @Override
	public String toString() {
		return "ServiceModel [serviceId=" + serviceId + ", serviceName=" + serviceName + ", ftpServer=" + ftpServer
				+ ", ftpUserName=" + ftpUserName + ", ftpUserPassword=" + ftpUserPassword + ", serverFolder="
				+ serverFolder + ", fileNamePrefix=" + fileNamePrefix + ", fileCountryCode=" + fileCountryCode
				+ ", inactive=" + inactive + ", allowChangeName=" + allowChangeName + ", carrierType=" + carrierType
				+ ", integrated=" + integrated + ", nonCentralized=" + nonCentralized + ", showorder=" + showorder
				+ ", shipmentTypes=" + shipmentTypes + "]";
	}

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getFtpServer() {
        return ftpServer;
    }

    public void setFtpServer(String ftpServer) {
        this.ftpServer = ftpServer;
    }

    public String getFtpUserName() {
        return ftpUserName;
    }

    public void setFtpUserName(String ftpUserName) {
        this.ftpUserName = ftpUserName;
    }

    public String getFtpUserPassword() {
        return ftpUserPassword;
    }

    public void setFtpUserPassword(String ftpUserPassword) {
        this.ftpUserPassword = ftpUserPassword;
    }

    public String getServerFolder() {
        return serverFolder;
    }

    public void setServerFolder(String serverFolder) {
        this.serverFolder = serverFolder;
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }

    public void setFileNamePrefix(String fileNamePrefix) {
        this.fileNamePrefix = fileNamePrefix;
    }

    public String getFileCountryCode() {
        return fileCountryCode;
    }

    public void setFileCountryCode(String fileCountryCode) {
        this.fileCountryCode = fileCountryCode;
    }

    public String getInactive() {
        return inactive;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }

    public String getAllowChangeName() {
        return allowChangeName;
    }

    public void setAllowChangeName(String allowChangeName) {
        this.allowChangeName = allowChangeName;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public String getIntegrated() {
        return integrated;
    }

    public void setIntegrated(String integrated) {
        this.integrated = integrated;
    }

    public String getNonCentralized() {
        return nonCentralized;
    }

    public void setNonCentralized(String nonCentralized) {
        this.nonCentralized = nonCentralized;
    }

    public List<ShipmentTypeModel> getShipmentTypes() {
        return shipmentTypes;
    }

    public void setShipmentTypes(List<ShipmentTypeModel> shipmentTypes) {
        this.shipmentTypes = shipmentTypes;
    }

	public String getShoworder() {
		return showorder;
	}

	public void setShoworder(String showorder) {
		this.showorder = showorder;
	}
    
    
}