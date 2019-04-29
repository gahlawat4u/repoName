package com.gms.xms.model.webship.addressbook;

import com.gms.xms.model.BaseModel;

import java.util.Map;

/**
 * Posted from Aug 25, 2016 1:47:44 PM
 * <p>
 * Author huynd
 */
public class AddressRowDataModel extends BaseModel {

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
        return "AddressRowDataModel [cellData=" + cellData + "]";
    }

}
