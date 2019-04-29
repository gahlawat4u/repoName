package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.adjustment.CarrierAdjustmentFilter;

/**
 * Posted from CarrierAdjustmentRequest
 * <p>
 * Author TanDT Date May 26, 2015
 */
public class CarrierAdjustmentRequest extends BaseRequest {
    private CarrierAdjustmentFilter filter;
    private String[] listAdjustmentId;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String[] getListAdjustmentId() {
        return listAdjustmentId;
    }

    public void setListAdjustmentId(String[] listAdjustmentId) {
        this.listAdjustmentId = listAdjustmentId;
    }

    public CarrierAdjustmentFilter getFilter() {
        return filter;
    }

    public void setFilter(CarrierAdjustmentFilter filter) {
        this.filter = filter;
    }

}
