package com.gms.xms.model.massedit;

import com.gms.xms.model.account.customers.manage.ShipmentTypeSettingModel;

/**
 * Posted from Jul 15, 2016 4:07:58 PM
 * <p>
 * Author huynd
 */
public class ServiceLevelModel extends ShipmentTypeSettingModel {

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
        return "ServiceLevelModel [id=" + id + ", type=" + type + "]";
    }

}
