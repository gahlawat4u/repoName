package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;
import java.util.List;

/**
 * Posted from RateSheetVo
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class RateSheetVo extends BaseVo {
    private static final long serialVersionUID = -3347068754487495843L;

    private Long sheetId;

    private String sheetName;

    private Date createDate;

    private Integer shipmentTypeId;

    private Integer cells;

    private String sourceZone;

    private Byte carrierCost;

    private Byte isPerWeight;

    private Integer content;

    private Integer bound;

    private List<RateSheetColumnVo> rateSheetCols;

    private List<RateSheetRowVo> rateSheetRows;

    private List<RateSheetDetailVo> rateSheetDetails;

    private ShipmentTypeVo shipmentType;

    public ShipmentTypeVo getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentTypeVo shipmentType) {
        this.shipmentType = shipmentType;
    }

    public List<RateSheetColumnVo> getRateSheetCols() {
        return rateSheetCols;
    }

    public void setRateSheetCols(List<RateSheetColumnVo> rateSheetCols) {
        this.rateSheetCols = rateSheetCols;
    }

    public List<RateSheetRowVo> getRateSheetRows() {
        return rateSheetRows;
    }

    public void setRateSheetRows(List<RateSheetRowVo> rateSheetRows) {
        this.rateSheetRows = rateSheetRows;
    }

    public List<RateSheetDetailVo> getRateSheetDetails() {
        return rateSheetDetails;
    }

    public void setRateSheetDetails(List<RateSheetDetailVo> rateSheetDetails) {
        this.rateSheetDetails = rateSheetDetails;
    }

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
        this.sheetName = sheetName == null ? null : sheetName.trim();
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Integer getCells() {
        return cells;
    }

    public void setCells(Integer cells) {
        this.cells = cells;
    }

    public String getSourceZone() {
        return sourceZone;
    }

    public void setSourceZone(String sourceZone) {
        this.sourceZone = sourceZone == null ? null : sourceZone.trim();
    }

    public Byte getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(Byte carrierCost) {
        this.carrierCost = carrierCost;
    }

    public Byte getIsPerWeight() {
        return isPerWeight;
    }

    public void setIsPerWeight(Byte isPerWeight) {
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
        return "RateSheetVo [sheetId=" + sheetId + ", sheetName=" + sheetName + ", createDate=" + createDate + ", shipmentTypeId=" + shipmentTypeId + ", cells=" + cells + ", sourceZone=" + sourceZone + ", carrierCost=" + carrierCost + ", isPerWeight=" + isPerWeight + ", content=" + content + ", bound=" + bound + ", rateSheetCols=" + rateSheetCols + ", rateSheetRows=" + rateSheetRows + ", rateSheetDetails=" + rateSheetDetails + ", shipmentType=" + shipmentType + "]";
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
        RateSheetVo other = (RateSheetVo) obj;
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