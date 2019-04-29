package com.gms.xms.filter.admin.ratesheets;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from May 5, 2016 10:22:22 AM
 * <p>
 * Author huynd
 */
public class ImportRateSheetFilter extends BaseFilter {

    private Long sheetId;
    private String sheetName;
    private Date create_date;
    private Long shipmentTypeId;
    private Long cells;
    private String sourceZone;
    private Integer carrierCost;
    private Integer isPerWeight;
    private Integer content;
    private Integer bound;

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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
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

    @Override
    public String toString() {
        return "ImportRateSheetFilter [sheetId=" + sheetId + ", sheetName=" + sheetName + ", create_date=" + create_date + ", shipmentTypeId=" + shipmentTypeId + ", cells=" + cells + ", sourceZone=" + sourceZone + ", carrierCost=" + carrierCost + ", isPerWeight=" + isPerWeight + ", content=" + content + ", bound=" + bound + "]";
    }

}
