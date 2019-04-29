package com.gms.xms.txndb.vo;

import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;

import java.util.List;

/**
 * Posted from ServiceVo
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class ServiceVo extends BaseVo {

    private static final long serialVersionUID = 2312702565079649439L;

    private Integer serviceId;

    private String serviceName;

    private String ftpServer;

    private String ftpUserName;

    private String ftpUserPassword;

    private String serverFolder;

    private String fileNamePrefix;

    private String fileCountryCode;

    private Byte inactive;

    private Byte allowChangeName;

    private Integer carrierType;

    private Byte integrated;

    private Byte nonCentralized;
    
    private Byte showorder;

    private List<ShipmentTypeVo> shipmentTypes;

    @Override
	public String toString() {
		return "ServiceVo [serviceId=" + serviceId + ", serviceName=" + serviceName + ", ftpServer=" + ftpServer
				+ ", ftpUserName=" + ftpUserName + ", ftpUserPassword=" + ftpUserPassword + ", serverFolder="
				+ serverFolder + ", fileNamePrefix=" + fileNamePrefix + ", fileCountryCode=" + fileCountryCode
				+ ", inactive=" + inactive + ", allowChangeName=" + allowChangeName + ", carrierType=" + carrierType
				+ ", integrated=" + integrated + ", nonCentralized=" + nonCentralized + ", showorder=" + showorder
				+ ", shipmentTypes=" + shipmentTypes + "]";
	}

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
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

    public Byte getInactive() {
        return inactive;
    }

    public void setInactive(Byte inactive) {
        this.inactive = inactive;
    }

    public Byte getAllowChangeName() {
        return allowChangeName;
    }

    public void setAllowChangeName(Byte allowChangeName) {
        this.allowChangeName = allowChangeName;
    }

    public Integer getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(Integer carrierType) {
        this.carrierType = carrierType;
    }

    public Byte getIntegrated() {
        return integrated;
    }

    public void setIntegrated(Byte integrated) {
        this.integrated = integrated;
    }

    public Byte getNonCentralized() {
        return nonCentralized;
    }

    public void setNonCentralized(Byte nonCentralized) {
        this.nonCentralized = nonCentralized;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public List<ShipmentTypeVo> getShipmentTypes() {
        return shipmentTypes;
    }

    public void setShipmentTypes(List<ShipmentTypeVo> shipmentTypes) {
        this.shipmentTypes = shipmentTypes;
    }

	public Byte getShoworder() {
		return showorder;
	}

	public void setShoworder(Byte showorder) {
		this.showorder = showorder;
	}
    
    
}