package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.LocationCodeVo;

import java.util.List;

/**
 * Posted from LocationCodeResponse
 * <p>
 * Author HungNT Date Apr 25, 2015
 */
public class LocationCodeResponse extends BaseResponse {
    private List<LocationCodeVo> locationCodeVos;

    public List<LocationCodeVo> getLocationCodeVos() {
        return locationCodeVos;
    }

    public void setLocationCodeVos(List<LocationCodeVo> locationCodeVos) {
        this.locationCodeVos = locationCodeVos;
    }
}