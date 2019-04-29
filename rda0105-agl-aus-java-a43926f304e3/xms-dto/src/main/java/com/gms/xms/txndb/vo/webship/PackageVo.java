package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from PackageVo
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class PackageVo extends BaseVo {
    private static final long serialVersionUID = -3184245458275149358L;

    private Integer packageId;

    private String packageName;

    private String packageTypeCode;

    private Byte addPiece;

    private Integer carrier;

    private Long localizationId;

    private Integer contentType;

    private Integer configId;

    @Override
    public String toString() {
        return "PackageVo [packageId=" + packageId + ", packageName=" + packageName + ", packageTypeCode=" + packageTypeCode + ", addPiece=" + addPiece + ", carrier=" + carrier + ", localizationId=" + localizationId + ", contentType=" + contentType + ", configId=" + configId + "]";
    }

    public Integer getCarrier() {
        return carrier;
    }

    public void setCarrier(Integer carrier) {
        this.carrier = carrier;
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

    public String getPackageTypeCode() {
        return packageTypeCode;
    }

    public void setPackageTypeCode(String packageTypeCode) {
        this.packageTypeCode = packageTypeCode;
    }

    public Byte getAddPiece() {
        return addPiece;
    }

    public void setAddPiece(Byte addPiece) {
        this.addPiece = addPiece;
    }

    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationId) {
        this.localizationId = localizationId;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }
}