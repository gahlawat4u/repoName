package com.gms.xms.txndb.vo.area;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from AreaVo
 * <p>
 * Author DatTV Sep 10, 2015
 */
public class AreaVo extends BaseVo {

    private static final long serialVersionUID = -723404006006832984L;

    private Integer areaId;
    private String areaName;

    @Override
    public String toString() {
        return "AreaVo [areaId=" + areaId + ", areaName=" + areaName + "]";
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
