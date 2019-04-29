package com.gms.xms.model;

import com.gms.xms.model.webship.ShipmentTypeModel;

import java.util.List;

/**
 * Posted from RateSheetVo
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class RateSheetModel extends BaseModel {
    private static final long serialVersionUID = -3347068754487495843L;

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

    private List<RateSheetColumnModel> rateSheetCols;

    private List<RateSheetRowModel> rateSheetRows;

    private List<RateSheetDetailModel> rateSheetDetails;

    private ShipmentTypeModel shipmentType;

    public ShipmentTypeModel getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentTypeModel shipmentType) {
        this.shipmentType = shipmentType;
    }

    public List<RateSheetColumnModel> getRateSheetCols() {
        return rateSheetCols;
    }

    public void setRateSheetCols(List<RateSheetColumnModel> rateSheetCols) {
        this.rateSheetCols = rateSheetCols;
    }

    public List<RateSheetRowModel> getRateSheetRows() {
        return rateSheetRows;
    }

    public void setRateSheetRows(List<RateSheetRowModel> rateSheetRows) {
        this.rateSheetRows = rateSheetRows;
    }

    public List<RateSheetDetailModel> getRateSheetDetails() {
        return rateSheetDetails;
    }

    public void setRateSheetDetails(List<RateSheetDetailModel> rateSheetDetails) {
        this.rateSheetDetails = rateSheetDetails;
    }

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

    @Override
    public String toString() {
        return "RateSheetModel [sheetId=" + sheetId + ", sheetName=" + sheetName + ", createDate=" + createDate + ", shipmentTypeId=" + shipmentTypeId + ", cells=" + cells + ", sourceZone=" + sourceZone + ", carrierCost=" + carrierCost + ", isPerWeight=" + isPerWeight + ", content=" + content + ", bound=" + bound + ", rateSheetCols=" + rateSheetCols + ", rateSheetRows=" + rateSheetRows + ", rateSheetDetails=" + rateSheetDetails + ", shipmentType=" + shipmentType + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bound == null) ? 0 : bound.hashCode());
        result = prime * result + ((carrierCost == null) ? 0 : carrierCost.hashCode());
        result = prime * result + ((cells == null) ? 0 : cells.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((isPerWeight == null) ? 0 : isPerWeight.hashCode());
        result = prime * result + ((rateSheetCols == null) ? 0 : rateSheetCols.hashCode());
        result = prime * result + ((rateSheetDetails == null) ? 0 : rateSheetDetails.hashCode());
        result = prime * result + ((rateSheetRows == null) ? 0 : rateSheetRows.hashCode());
        result = prime * result + ((sheetId == null) ? 0 : sheetId.hashCode());
        result = prime * result + ((sheetName == null) ? 0 : sheetName.hashCode());
        result = prime * result + ((shipmentType == null) ? 0 : shipmentType.hashCode());
        result = prime * result + ((shipmentTypeId == null) ? 0 : shipmentTypeId.hashCode());
        result = prime * result + ((sourceZone == null) ? 0 : sourceZone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RateSheetModel other = (RateSheetModel) obj;
        if (bound == null) {
            if (other.bound != null)
                return false;
        } else if (!bound.equals(other.bound))
            return false;
        if (carrierCost == null) {
            if (other.carrierCost != null)
                return false;
        } else if (!carrierCost.equals(other.carrierCost))
            return false;
        if (cells == null) {
            if (other.cells != null)
                return false;
        } else if (!cells.equals(other.cells))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (isPerWeight == null) {
            if (other.isPerWeight != null)
                return false;
        } else if (!isPerWeight.equals(other.isPerWeight))
            return false;
        if (rateSheetCols == null) {
            if (other.rateSheetCols != null)
                return false;
        } else if (!rateSheetCols.equals(other.rateSheetCols))
            return false;
        if (rateSheetDetails == null) {
            if (other.rateSheetDetails != null)
                return false;
        } else if (!rateSheetDetails.equals(other.rateSheetDetails))
            return false;
        if (rateSheetRows == null) {
            if (other.rateSheetRows != null)
                return false;
        } else if (!rateSheetRows.equals(other.rateSheetRows))
            return false;
        if (sheetId == null) {
            if (other.sheetId != null)
                return false;
        } else if (!sheetId.equals(other.sheetId))
            return false;
        if (sheetName == null) {
            if (other.sheetName != null)
                return false;
        } else if (!sheetName.equals(other.sheetName))
            return false;
        if (shipmentType == null) {
            if (other.shipmentType != null)
                return false;
        } else if (!shipmentType.equals(other.shipmentType))
            return false;
        if (shipmentTypeId == null) {
            if (other.shipmentTypeId != null)
                return false;
        } else if (!shipmentTypeId.equals(other.shipmentTypeId))
            return false;
        if (sourceZone == null) {
            if (other.sourceZone != null)
                return false;
        } else if (!sourceZone.equals(other.sourceZone))
            return false;
        return true;
    }

}