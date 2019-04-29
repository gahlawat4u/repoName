package com.gms.xms.txndb.vo;

/**
 * Posted from RateSheetColumnVo
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class RateSheetColumnVo extends BaseVo {
    private static final long serialVersionUID = 4756284245927610487L;

    private Long columnId;

    private Long sheetId;

    private String columnName;

    private String zoneName;

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public Long getSheetId() {
        return sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public String toString() {
        return "RateSheetColumnVo [columnId=" + columnId + ", sheetId=" + sheetId + ", columnName=" + columnName + ", zoneName=" + zoneName + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((columnId == null) ? 0 : columnId.hashCode());
        result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
        result = prime * result + ((sheetId == null) ? 0 : sheetId.hashCode());
        result = prime * result + ((zoneName == null) ? 0 : zoneName.hashCode());
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
        RateSheetColumnVo other = (RateSheetColumnVo) obj;
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
        if (sheetId == null) {
            if (other.sheetId != null)
                return false;
        } else if (!sheetId.equals(other.sheetId))
            return false;
        if (zoneName == null) {
            if (other.zoneName != null)
                return false;
        } else if (!zoneName.equals(other.zoneName))
            return false;
        return true;
    }

}