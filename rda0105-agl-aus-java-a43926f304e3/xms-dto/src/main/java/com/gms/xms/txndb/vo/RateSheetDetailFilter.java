package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.txndb.vo.webship.WebshipRateSheetDetailVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from RateSheetDetailFilter
 * <p>
 * Author HungNT Date Apr 22, 2015
 */
public class RateSheetDetailFilter extends WebshipRateSheetDetailVo {
    private static final long serialVersionUID = -2601610615465045620L;

    private Double rowName;
    private String charRowName;
    private String columnName;
    private Double maxRowName;
    private String type;

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getRowName() {
        return rowName;
    }

    public void setRowName(Double rowName) {
        this.rowName = rowName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getMaxRowName() {
        return maxRowName;
    }

    public void setMaxRowName(Double maxRowName) {
        this.maxRowName = maxRowName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCharRowName() {
        return charRowName;
    }

    public void setCharRowName(String charRowName) {
        this.charRowName = charRowName;
    }

    @Override
    public String toString() {
        return "RateSheetDetailFilter [rowName=" + rowName + ", charRowName=" + charRowName + ", columnName=" + columnName + ", maxRowName=" + maxRowName + ", type=" + type + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((charRowName == null) ? 0 : charRowName.hashCode());
        result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
        result = prime * result + ((maxRowName == null) ? 0 : maxRowName.hashCode());
        result = prime * result + ((rowName == null) ? 0 : rowName.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        RateSheetDetailFilter other = (RateSheetDetailFilter) obj;
        if (charRowName == null) {
            if (other.charRowName != null)
                return false;
        } else if (!charRowName.equals(other.charRowName))
            return false;
        if (columnName == null) {
            if (other.columnName != null)
                return false;
        } else if (!columnName.equals(other.columnName))
            return false;
        if (maxRowName == null) {
            if (other.maxRowName != null)
                return false;
        } else if (!maxRowName.equals(other.maxRowName))
            return false;
        if (rowName == null) {
            if (other.rowName != null)
                return false;
        } else if (!rowName.equals(other.rowName))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
}
