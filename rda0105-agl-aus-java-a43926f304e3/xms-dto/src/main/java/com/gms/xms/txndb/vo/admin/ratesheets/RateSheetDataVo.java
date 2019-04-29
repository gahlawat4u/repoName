package com.gms.xms.txndb.vo.admin.ratesheets;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.List;

/**
 * Posted from May 7, 2016 9:06:46 AM
 * <p>
 * Author huynd
 */
public class RateSheetDataVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private List<RateSheetRowDataVo> rowData;

    public List<RateSheetRowDataVo> getRowData() {
        return rowData;
    }

    public void setRowData(List<RateSheetRowDataVo> rowData) {
        this.rowData = rowData;
    }

    @Override
    public String toString() {
        return "RateSheetDataModel [rowData=" + rowData + "]";
    }

}
