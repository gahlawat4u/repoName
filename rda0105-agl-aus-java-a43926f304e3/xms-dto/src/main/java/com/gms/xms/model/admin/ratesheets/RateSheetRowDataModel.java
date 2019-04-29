package com.gms.xms.model.admin.ratesheets;

import com.gms.xms.model.BaseModel;

import java.util.Map;

/**
 * Posted from May 7, 2016 9:08:33 AM
 * <p>
 * Author huynd
 */
public class RateSheetRowDataModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Map<String, String> rowData;

    public Map<String, String> getRowData() {
        return rowData;
    }

    public void setRowData(Map<String, String> rowData) {
        this.rowData = rowData;
    }

    @Override
    public String toString() {
        return "RateSheetRowDataModel [rowData=" + rowData + "]";
    }
}
