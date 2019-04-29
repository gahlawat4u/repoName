package com.gms.xms.model.admin.ratesheets;

import com.gms.xms.model.account.customers.manage.ShipmentTypeSettingModel;

/**
 * Posted from May 5, 2016 2:55:41 PM
 * <p>
 * Author huynd
 */
public class RateSheetTypeModel extends ShipmentTypeSettingModel {

    private static final long serialVersionUID = 1L;

    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RateSheetTypeModel [id=" + id + ", type=" + type + "]";
    }

}
