package com.gms.xms.txndb.vo.admin.ratesheets;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Date;

/**
 * Posted from May 5, 2016 10:12:04 AM
 * <p>
 * Author huynd
 */
public class ImportRateSheetVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long sheetId;
    private String sheetName;
    private Date createDate;
    private Long shipmentTypeId;
    private Long cells;
    private String sourceZone;
    private Integer carrierCost;
    private Integer isPerWeight;
    private Integer content;
    private Integer bound;

    private Long columnId;
    private String columnName;

    private Long rowId;
    private Double rowName;
    private String charRowName;
    private Byte isChar;

    private Double value;
    private Double minCharge;
    private Double baseCharge;
    private Double perKg;

    public Long getSheetId() {
        return sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Long shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Long getCells() {
        return cells;
    }

    public void setCells(Long cells) {
        this.cells = cells;
    }

    public String getSourceZone() {
        return sourceZone;
    }

    public void setSourceZone(String sourceZone) {
        this.sourceZone = sourceZone;
    }

    public Integer getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(Integer carrierCost) {
        this.carrierCost = carrierCost;
    }

    public Integer getIsPerWeight() {
        return isPerWeight;
    }

    public void setIsPerWeight(Integer isPerWeight) {
        this.isPerWeight = isPerWeight;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getBound() {
        return bound;
    }

    public void setBound(Integer bound) {
        this.bound = bound;
    }

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Double getRowName() {
        return rowName;
    }

    public void setRowName(Double rowName) {
        this.rowName = rowName;
    }

    public String getCharRowName() {
        return charRowName;
    }

    public void setCharRowName(String charRowName) {
        this.charRowName = charRowName;
    }

    public Byte getIsChar() {
        return isChar;
    }

    public void setIsChar(Byte isChar) {
        this.isChar = isChar;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(Double minCharge) {
        this.minCharge = minCharge;
    }

    public Double getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(Double baseCharge) {
        this.baseCharge = baseCharge;
    }

    public Double getPerKg() {
        return perKg;
    }

    public void setPerKg(Double perKg) {
        this.perKg = perKg;
    }

    @Override
    public String toString() {
        return "ImportRateVo [sheetId=" + sheetId + ", sheetName=" + sheetName + ", createDate=" + createDate + ", shipmentTypeId=" + shipmentTypeId + ", cells=" + cells + ", sourceZone=" + sourceZone + ", carrierCost=" + carrierCost + ", isPerWeight=" + isPerWeight + ", content=" + content + ", bound=" + bound + ", columnId=" + columnId + ", columnName=" + columnName + ", rowId=" + rowId + ", rowName=" + rowName + ", charRowName=" + charRowName + ", isChar=" + isChar + ", value=" + value
                + ", minCharge=" + minCharge + ", baseCharge=" + baseCharge + ", perKg=" + perKg + "]";
    }
}
