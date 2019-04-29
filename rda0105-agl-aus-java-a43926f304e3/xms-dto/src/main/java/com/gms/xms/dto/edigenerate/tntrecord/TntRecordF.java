package com.gms.xms.dto.edigenerate.tntrecord;

import com.gms.xms.common.constants.AppConstants.PADDING_TYPE;

/**
 * Posted from Sep 23, 2016 11:35:44 AM
 * <p>
 * Author dattrinh
 */
public class TntRecordF extends TntRecordBase {
    private String manifestIdentifier;
    private String conNoteNumber;
    private String lineSequence;
    private String customerReference;
    private String descriptionOfGood;
    private String commodityCode;
    private String numberOfItems;
    private String weight;
    private String unitOfMeasureWeight;
    private String itemDimensionLength;
    private String itemDimensionWidth;
    private String itemDimensionHeight;
    private String unitOfMeasureDimension;
    private String cubicVolume;
    private String unitOfMeasureCubicVolume;
    private String articleQty;
    private String garmentQty;

    @Override
    public boolean isValidRow() {
        boolean isValid = !"NO".equalsIgnoreCase(this.getRecordType());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getManifestIdentifier());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getConNoteNumber());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getLineSequence());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getCustomerReference());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getDescriptionOfGood());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getCommodityCode());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getNumberOfItems());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getWeight());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getUnitOfMeasureWeight());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getItemDimensionLength());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getItemDimensionWidth());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getItemDimensionHeight());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getUnitOfMeasureDimension());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getCubicVolume());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getUnitOfMeasureCubicVolume());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getArticleQty());
        isValid = isValid && !"NO".equalsIgnoreCase(this.getGarmentQty());
        return isValid;
    }

    @Override
    public String getRow() {
        String row = "";
        row += this.getRecordType();
        row += this.getManifestIdentifier();
        row += this.getConNoteNumber();
        row += this.getLineSequence();
        row += this.getCustomerReference();
        row += this.getDescriptionOfGood();
        row += this.getCommodityCode();
        row += this.getNumberOfItems();
        row += this.getWeight();
        row += this.getUnitOfMeasureWeight();
        row += this.getItemDimensionLength();
        row += this.getItemDimensionWidth();
        row += this.getItemDimensionHeight();
        row += this.getUnitOfMeasureDimension();
        row += this.getCubicVolume();
        row += this.getUnitOfMeasureCubicVolume();
        row += this.getArticleQty();
        row += this.getGarmentQty();
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
        return getRecord(conNoteNumber, "CHAR", 15, "BF", PADDING_TYPE.RIGHT);
    }

    public void setConNoteNumber(String conNoteNumber) {
        this.conNoteNumber = conNoteNumber;
    }

    public String getLineSequence() {
        return getRecord(lineSequence, "NUM", 3, "BF", PADDING_TYPE.RIGHT);
    }

    public void setLineSequence(String lineSequence) {
        this.lineSequence = lineSequence;
    }

    public String getCustomerReference() {
        return getRecord(customerReference, "CHAR", 15, "BF");
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public String getDescriptionOfGood() {
        return getRecord(descriptionOfGood, "CHAR", 20, "BF", PADDING_TYPE.RIGHT);
    }

    public void setDescriptionOfGood(String descriptionOfGood) {
        this.descriptionOfGood = descriptionOfGood;
    }

    public String getCommodityCode() {
        return getRecord(commodityCode, "CHAR", 6, "BF");
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getNumberOfItems() {
        return getRecord(numberOfItems, "NUM", 5, "ZF");
    }

    public void setNumberOfItems(String numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public String getWeight() {
        return getRecord(weight, "NUM9.3", 9, "ZF");
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getUnitOfMeasureWeight() {
        return getRecord(unitOfMeasureWeight, "CHAR", 2, "BF");
    }

    public void setUnitOfMeasureWeight(String unitOfMeasureWeight) {
        this.unitOfMeasureWeight = unitOfMeasureWeight;
    }

    public String getItemDimensionLength() {
        return getRecord(itemDimensionLength, "NUM7.2", 7, "ZF");
    }

    public void setItemDimensionLength(String itemDimensionLength) {
        this.itemDimensionLength = itemDimensionLength;
    }

    public String getItemDimensionWidth() {
        return getRecord(itemDimensionWidth, "NUM7.2", 7, "ZF");
    }

    public void setItemDimensionWidth(String itemDimensionWidth) {
        this.itemDimensionWidth = itemDimensionWidth;
    }

    public String getItemDimensionHeight() {
        return getRecord(itemDimensionHeight, "NUM7.2", 7, "ZF");
    }

    public void setItemDimensionHeight(String itemDimensionHeight) {
        this.itemDimensionHeight = itemDimensionHeight;
    }

    public String getUnitOfMeasureDimension() {
        return getRecord(unitOfMeasureDimension, "CHAR", 2, "BF");
    }

    public void setUnitOfMeasureDimension(String unitOfMeasureDimension) {
        this.unitOfMeasureDimension = unitOfMeasureDimension;
    }

    public String getCubicVolume() {
        return getRecord(cubicVolume, "NUM10.4", 10, "ZF");
    }

    public void setCubicVolume(String cubicVolume) {
        this.cubicVolume = cubicVolume;
    }

    public String getUnitOfMeasureCubicVolume() {
        return getRecord(unitOfMeasureCubicVolume, "CHAR", 2, "BF");
    }

    public void setUnitOfMeasureCubicVolume(String unitOfMeasureCubicVolume) {
        this.unitOfMeasureCubicVolume = unitOfMeasureCubicVolume;
    }

    public String getArticleQty() {
        return getRecord(articleQty, "NUM", 5, "ZF");
    }

    public void setArticleQty(String articleQty) {
        this.articleQty = articleQty;
    }

    public String getGarmentQty() {
        return getRecord(garmentQty, "NUM", 5, "ZF");
    }

    public void setGarmentQty(String garmentQty) {
        this.garmentQty = garmentQty;
    }
}
