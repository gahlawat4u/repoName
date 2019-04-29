package com.gms.xms.model.admin.ratesheets;

import com.gms.xms.model.BaseModel;

/**
 * Posted from May 5, 2016 10:22:22 AM
 * <p>
 * Author huynd
 */
public class ImportRateSheetModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String sheetId;
    private String sheetName;
    private String createDate;
    private String shipmentTypeId;
    private String cells;
    private String sourceZone;
    private String carrierCost;
    private String isPerWeight;
    private String content;
    private String bound;

    private String columnId;
    private String columnName;

    private String rowId;
    private String rowName;
    private String charRowName;
    private String isChar;

    private String value;
    private String minCharge;
    private String baseCharge;
    private String perKg;

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getCells() {
        return cells;
    }

    public void setCells(String cells) {
        this.cells = cells;
    }

    public String getSourceZone() {
        return sourceZone;
    }

    public void setSourceZone(String sourceZone) {
        this.sourceZone = sourceZone;
    }

    public String getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(String carrierCost) {
        this.carrierCost = carrierCost;
    }

    public String getIsPerWeight() {
        return isPerWeight;
    }

    public void setIsPerWeight(String isPerWeight) {
        this.isPerWeight = isPerWeight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getCharRowName() {
        return charRowName;
    }

    public void setCharRowName(String charRowName) {
        this.charRowName = charRowName;
    }

    public String getIsChar() {
        return isChar;
    }

    public void setIsChar(String isChar) {
        this.isChar = isChar;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(String minCharge) {
        this.minCharge = minCharge;
    }

    public String getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(String baseCharge) {
        this.baseCharge = baseCharge;
    }

    public String getPerKg() {
        return perKg;
    }

    public void setPerKg(String perKg) {
        this.perKg = perKg;
    }

    @Override
    public String toString() {
        return "ImportRateModel [sheetId=" + sheetId + ", sheetName=" + sheetName + ", createDate=" + createDate + ", shipmentTypeId=" + shipmentTypeId + ", cells=" + cells + ", sourceZone=" + sourceZone + ", carrierCost=" + carrierCost + ", isPerWeight=" + isPerWeight + ", content=" + content + ", bound=" + bound + ", columnId=" + columnId + ", columnName=" + columnName + ", rowId=" + rowId + ", rowName=" + rowName + ", charRowName=" + charRowName + ", isChar=" + isChar + ", value=" + value
                + ", minCharge=" + minCharge + ", baseCharge=" + baseCharge + ", perKg=" + perKg + "]";
    }

}
