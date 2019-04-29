package com.gms.xms.model.webship.addressbook;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * Posted from Aug 25, 2016 1:58:25 PM
 * <p>
 * Author huynd
 */
public class AddressDataModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private List<AddressRowDataModel> rowData;

    public List<AddressRowDataModel> getRowData() {
        return rowData;
    }

    public void setRowData(List<AddressRowDataModel> rowData) {
        this.rowData = rowData;
    }

    @Override
    public String toString() {
        return "AddressDataModel [rowData=" + rowData + "]";
    }

}
