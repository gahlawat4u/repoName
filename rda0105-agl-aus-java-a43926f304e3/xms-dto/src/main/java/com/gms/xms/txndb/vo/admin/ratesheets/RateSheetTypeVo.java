package com.gms.xms.txndb.vo.admin.ratesheets;

import com.gms.xms.txndb.vo.account.customers.manage.ShipmentTypeSettingVo;

/**
 * Posted from May 5, 2016 2:55:41 PM
 * <p>
 * Author huynd
 */
public class RateSheetTypeVo extends ShipmentTypeSettingVo {

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
        return "RateSheetTypeVo [id=" + id + ", type=" + type + "]";
    }

}
