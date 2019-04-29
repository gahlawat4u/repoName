package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from PackageModel
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class PackageModel extends BaseModel {

    private static final long serialVersionUID = -559884804752791638L;

    private String packageId;

    private String packageName;

    private String packageTypeCode;

    private String addPiece;

    private String carrier;

    private String localizationId;

    private String contentType;

    private String configId;

    @Override
    public String toString() {
        return "PackageModel [packageId=" + packageId + ", packageName=" + packageName + ", packageTypeCode=" + packageTypeCode + ", addPiece=" + addPiece + ", carrier=" + carrier + ", localizationId=" + localizationId + ", contentType=" + contentType + ", configId=" + configId + "]";
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageTypeCode() {
        return packageTypeCode;
    }

    public void setPackageTypeCode(String packageTypeCode) {
        this.packageTypeCode = packageTypeCode;
    }

    public String getAddPiece() {
        return addPiece;
    }

    public void setAddPiece(String addPiece) {
        this.addPiece = addPiece;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }
}