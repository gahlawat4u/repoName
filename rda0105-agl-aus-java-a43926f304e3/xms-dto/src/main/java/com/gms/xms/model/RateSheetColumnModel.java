package com.gms.xms.model;

/**
 * Posted from RateSheetColumnVo
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class RateSheetColumnModel extends BaseModel {
    private static final long serialVersionUID = -3187400648372651990L;

    private String columnId;

    private String sheetId;

    private String columnName;

    private String zoneName;

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public String toString() {
        return "RateSheetColumnModel [columnId=" + columnId + ", sheetId=" + sheetId + ", columnName=" + columnName + ", zoneName=" + zoneName + "]";
    }

}