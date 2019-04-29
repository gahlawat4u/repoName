package com.gms.xms.model;

/**
 * Posted from RateSheetDetailVo
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class RateSheetDetailModel extends BaseModel {
    private static final long serialVersionUID = 16836735726872356L;

    private String sheetId;

    private String rowId;

    private String columnId;

    private String value;

    private String minCharge;

    private String baseCharge;

    private String perKg;

    private String columnName;

    private String charRowName;

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(String minCharge) {
        this.minCharge = minCharge;
    }

    public String getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(String baseCharge) {
        this.baseCharge = baseCharge;
    }

    public String getPerKg() {
        return perKg;
    }

    public void setPerKg(String perKg) {
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
        return "RateSheetDetailModel [sheetId=" + sheetId + ", rowId=" + rowId + ", columnId=" + columnId + ", value=" + value + ", minCharge=" + minCharge + ", baseCharge=" + baseCharge + ", perKg=" + perKg + ", columnName=" + columnName + ", charRowName=" + charRowName + "]";
    }
}