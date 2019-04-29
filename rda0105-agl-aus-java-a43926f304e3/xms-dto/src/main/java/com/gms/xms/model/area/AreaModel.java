package com.gms.xms.model.area;

import com.gms.xms.model.BaseModel;

/**
 * Posted from AreaModel
 * <p>
 * Author DatTV Sep 10, 2015
 */
public class AreaModel extends BaseModel {

    private static final long serialVersionUID = 7089499762113690889L;

    private String areaId;
    private String areaName;

    @Override
    public String toString() {
        return "AreaModel [areaId=" + areaId + ", areaName=" + areaName + "]";
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
