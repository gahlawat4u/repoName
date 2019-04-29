package com.gms.xms.model;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Posted from SupplyModel
 * <p>
 * Author DatTV Date Jul 15, 2015 5:07:52 PM
 */
public class SupplyModel extends BaseModel {

    private static final long serialVersionUID = 3615308864111510675L;

    private String supplyId;
    private String supplyName;
    private String carrierName;
    private String carrierId;
    private String localizationId;
    private String image;
    private String description;
    private String fileType;
    private String fileData;

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
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

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
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

    @JsonIgnore
    public String getImageUrl() {
        String imagePath = "data:image/" + this.getFileType() + ";base64," + this.getFileData();
        return imagePath;
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
        return "SupplyModel [supplyId=" + supplyId + ", supplyName=" + supplyName + ", carrierName=" + carrierName + ", carrierId=" + carrierId + ", localizationId=" + localizationId + ", image=" + image + ", description=" + description + ", fileType=" + fileType + ", fileData=" + fileData + "]";
    }
}
