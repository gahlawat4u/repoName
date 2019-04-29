package com.gms.xms.txndb.vo.massedit;

import com.gms.xms.txndb.vo.account.customers.manage.ShipmentTypeSettingVo;

/**
 * Posted from Jul 15, 2016 4:06:31 PM
 * <p>
 * Author huynd
 */
public class ServiceLevelVo extends ShipmentTypeSettingVo {

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
        return "ServiceLevelVo [id=" + id + ", type=" + type + "]";
    }

}
