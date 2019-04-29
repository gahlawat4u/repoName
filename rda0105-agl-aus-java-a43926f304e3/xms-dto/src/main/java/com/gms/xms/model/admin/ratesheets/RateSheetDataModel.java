package com.gms.xms.model.admin.ratesheets;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * Posted from May 7, 2016 9:06:46 AM
 * <p>
 * Author huynd
 */
public class RateSheetDataModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private List<RateSheetRowDataModel> rowData;

    public List<RateSheetRowDataModel> getRowData() {
        return rowData;
    }

    public void setRowData(List<RateSheetRowDataModel> rowData) {
        this.rowData = rowData;
    }

    @Override
    public String toString() {
        return "RateSheetDataModel [rowData=" + rowData + "]";
    }

}
