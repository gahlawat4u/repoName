package com.gms.xms.txndb.vo;

/**
 * Posted from RateSheetRowVo
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class RateSheetRowVo extends BaseVo {
    private static final long serialVersionUID = 2142200413536408863L;

    private Long rowId;

    private Long sheetId;

    private Double rowName;

    private String charRowName;

    private String zoneName;

    private Boolean isChar;

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Long getSheetId() {
        return sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
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
        this.charRowName = charRowName == null ? null : charRowName.trim();
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Boolean getIsChar() {
        return isChar;
    }

    public void setIsChar(Boolean isChar) {
        this.isChar = isChar;
    }

    @Override
    public String toString() {
        return "RateSheetRowVo [rowId=" + rowId + ", sheetId=" + sheetId + ", rowName=" + rowName + ", charRowName=" + charRowName + ", zoneName=" + zoneName + ", isChar=" + isChar + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((charRowName == null) ? 0 : charRowName.hashCode());
        result = prime * result + ((isChar == null) ? 0 : isChar.hashCode());
        result = prime * result + ((rowId == null) ? 0 : rowId.hashCode());
        result = prime * result + ((rowName == null) ? 0 : rowName.hashCode());
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
        RateSheetRowVo other = (RateSheetRowVo) obj;
        if (charRowName == null) {
            if (other.charRowName != null)
                return false;
        } else if (!charRowName.equals(other.charRowName))
            return false;
        if (isChar == null) {
            if (other.isChar != null)
                return false;
        } else if (!isChar.equals(other.isChar))
            return false;
        if (rowId == null) {
            if (other.rowId != null)
                return false;
        } else if (!rowId.equals(other.rowId))
            return false;
        if (rowName == null) {
            if (other.rowName != null)
                return false;
        } else if (!rowName.equals(other.rowName))
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