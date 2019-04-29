package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDoubleSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from RateSheetDetailVo
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class RateSheetDetailVo extends BaseVo {
    private static final long serialVersionUID = -1069557329148520738L;

    private Long sheetId;

    private Long rowId;

    private Long columnId;

    private Double value;

    private Double minCharge;

    private Double baseCharge;

    private Double perKg;

    private String columnName;

    private String charRowName;

    public Long getSheetId() {
        return sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(Double minCharge) {
        this.minCharge = minCharge;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(Double baseCharge) {
        this.baseCharge = baseCharge;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getPerKg() {
        return perKg;
    }

    public void setPerKg(Double perKg) {
        this.perKg = perKg;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getCharRowName() {
        return charRowName;
    }

    public void setCharRowName(String charRowName) {
        this.charRowName = charRowName;
    }

    @Override
    public String toString() {
        return "RateSheetDetailVo [sheetId=" + sheetId + ", rowId=" + rowId + ", columnId=" + columnId + ", value=" + value + ", minCharge=" + minCharge + ", baseCharge=" + baseCharge + ", perKg=" + perKg + ", columnName=" + columnName + ", charRowName=" + charRowName + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((baseCharge == null) ? 0 : baseCharge.hashCode());
        result = prime * result + ((charRowName == null) ? 0 : charRowName.hashCode());
        result = prime * result + ((columnId == null) ? 0 : columnId.hashCode());
        result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
        result = prime * result + ((minCharge == null) ? 0 : minCharge.hashCode());
        result = prime * result + ((perKg == null) ? 0 : perKg.hashCode());
        result = prime * result + ((rowId == null) ? 0 : rowId.hashCode());
        result = prime * result + ((sheetId == null) ? 0 : sheetId.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        RateSheetDetailVo other = (RateSheetDetailVo) obj;
        if (baseCharge == null) {
            if (other.baseCharge != null)
                return false;
        } else if (!baseCharge.equals(other.baseCharge))
            return false;
        if (charRowName == null) {
            if (other.charRowName != null)
                return false;
        } else if (!charRowName.equals(other.charRowName))
            return false;
        if (columnId == null) {
            if (other.columnId != null)
                return false;
        } else if (!columnId.equals(other.columnId))
            return false;
        if (columnName == null) {
            if (other.columnName != null)
                return false;
        } else if (!columnName.equals(other.columnName))
            return false;
        if (minCharge == null) {
            if (other.minCharge != null)
                return false;
        } else if (!minCharge.equals(other.minCharge))
            return false;
        if (perKg == null) {
            if (other.perKg != null)
                return false;
        } else if (!perKg.equals(other.perKg))
            return false;
        if (rowId == null) {
            if (other.rowId != null)
                return false;
        } else if (!rowId.equals(other.rowId))
            return false;
        if (sheetId == null) {
            if (other.sheetId != null)
                return false;
        } else if (!sheetId.equals(other.sheetId))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

}