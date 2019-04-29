package com.gms.xms.dto.edigenerate.tntrecord;

/**
 * Posted from Sep 23, 2016 11:35:44 AM
 * <p>
 * Author dattrinh
 */
public class TntRecordG extends TntRecordBase {
    private String manifestIdentifier;
    private String conNoteNumber;
    private String lineSequence;
    private String productSequence;
    private String productWeight;
    private String unitOfMeasureWeight;
    private String hazMaterialType;
    private String hazMaterialCode;
    private String hazMaterialTechnicalName;
    private String principleRiskClass;
    private String packagingGroupCode;

    @Override
    public boolean isValidRow() {
        boolean isValid = !"NO".equalsIgnoreCase(this.getRecordType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getManifestIdentifier());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getConNoteNumber());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getLineSequence());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getProductSequence());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getProductWeight());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getUnitOfMeasureWeight());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getHazMaterialType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getHazMaterialCode());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getHazMaterialTechnicalName());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getPrincipleRiskClass());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getPackagingGroupCode());
        return isValid;
    }

    @Override
    public String getRow() {
        String row = "";
        row += this.getRecordType();
        row += this.getManifestIdentifier();
        row += this.getConNoteNumber();
        row += this.getLineSequence();
        row += this.getProductSequence();
        row += this.getProductWeight();
        row += this.getUnitOfMeasureWeight();
        row += this.getHazMaterialType();
        row += this.getHazMaterialCode();
        row += this.getHazMaterialTechnicalName();
        row += this.getPrincipleRiskClass();
        row += this.getPackagingGroupCode();
        row += "\n";
        return row;
    }

    public String getManifestIdentifier() {
        return getRecord(manifestIdentifier, "CHAR", 10, "BF");
    }

    public void setManifestIdentifier(String manifestIdentifier) {
        this.manifestIdentifier = manifestIdentifier;
    }

    public String getConNoteNumber() {
        return getRecord(conNoteNumber, "CHAR", 15, "BF");
    }

    public void setConNoteNumber(String conNoteNumber) {
        this.conNoteNumber = conNoteNumber;
    }

    public String getLineSequence() {
        return getRecord(lineSequence, "NUM", 3, "BF");
    }

    public void setLineSequence(String lineSequence) {
        this.lineSequence = lineSequence;
    }

    public String getProductSequence() {
        return getRecord(productSequence, "NUM", 2, "BF");
    }

    public void setProductSequence(String productSequence) {
        this.productSequence = productSequence;
    }

    public String getProductWeight() {
        return getRecord(productWeight, "NUM9.3", 9, "BF");
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getUnitOfMeasureWeight() {
        return getRecord(unitOfMeasureWeight, "CHAR", 2, "BF");
    }

    public void setUnitOfMeasureWeight(String unitOfMeasureWeight) {
        this.unitOfMeasureWeight = unitOfMeasureWeight;
    }

    public String getHazMaterialType() {
        return getRecord(hazMaterialType, "CHAR", 1, "BF");
    }

    public void setHazMaterialType(String hazMaterialType) {
        this.hazMaterialType = hazMaterialType;
    }

    public String getHazMaterialCode() {
        return getRecord(hazMaterialCode, "CHAR", 4, "BF");
    }

    public void setHazMaterialCode(String hazMaterialCode) {
        this.hazMaterialCode = hazMaterialCode;
    }

    public String getHazMaterialTechnicalName() {
        return getRecord(hazMaterialTechnicalName, "CHAR", 80, "BF");
    }

    public void setHazMaterialTechnicalName(String hazMaterialTechnicalName) {
        this.hazMaterialTechnicalName = hazMaterialTechnicalName;
    }

    public String getPrincipleRiskClass() {
        return getRecord(principleRiskClass, "CHAR", 10, "BF");
    }

    public void setPrincipleRiskClass(String principleRiskClass) {
        this.principleRiskClass = principleRiskClass;
    }

    public String getPackagingGroupCode() {
        return getRecord(packagingGroupCode, "CHAR", 10, "BF");
    }

    public void setPackagingGroupCode(String packagingGroupCode) {
        this.packagingGroupCode = packagingGroupCode;
    }
}
