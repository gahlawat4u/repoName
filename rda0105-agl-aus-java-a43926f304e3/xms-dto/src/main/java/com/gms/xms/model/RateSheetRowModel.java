package com.gms.xms.model;

/**
 * Posted from RateSheetRowVo
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class RateSheetRowModel extends BaseModel {
    private static final long serialVersionUID = -5319706906252201432L;

    private String rowId;

    private String sheetId;

    private String rowName;

    private String charRowName;

    private String zoneName;

    private String isChar;

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
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

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getIsChar() {
        return isChar;
    }

    public void setIsChar(String isChar) {
        this.isChar = isChar;
    }

    @Override
    public String toString() {
        return "RateSheetRowModel [rowId=" + rowId + ", sheetId=" + sheetId + ", rowName=" + rowName + ", charRowName=" + charRowName + ", zoneName=" + zoneName + ", isChar=" + isChar + "]";
    }

}