package com.gms.xms.model.admin.imports;

import com.gms.xms.model.BaseModel;

import java.util.Map;

/**
 * Posted from May 24, 2016 4:02:32 PM
 * <p>
 * Author huynd
 */
public class BillingRowDataModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Map<String, String> cellData;

    public Map<String, String> getCellData() {
        return cellData;
    }

    public void setCellData(Map<String, String> cellData) {
        this.cellData = cellData;
    }

    @Override
    public String toString() {
        return "BillingRowDataModel [cellData=" + cellData + "]";
    }

}
