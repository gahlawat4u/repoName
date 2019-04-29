package com.gms.xms.txndb.vo;

/**
 * Posted from SupplyVo
 * <p>
 * Author DatTV Date Jul 15, 2015 5:06:01 PM
 */
public class SupplyVo extends BaseVo {

    private static final long serialVersionUID = -3775588938583695537L;

    private Integer supplyId;
    private String supplyName;
    private String carrierName;
    private Long carrierId;
    private Long localizationId;
    private String image;
    private String description;
    private String fileType;
    private String fileData;

    public Integer getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationId) {
        this.localizationId = localizationId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    @Override
    public String toString() {
        return "SupplyVo [supplyId=" + supplyId + ", supplyName=" + supplyName + ", carrierName=" + carrierName + ", carrierId=" + carrierId + ", localizationId=" + localizationId + ", image=" + image + ", description=" + description + ", fileType=" + fileType + ", fileData=" + fileData + "]";
    }
}