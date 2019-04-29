package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.adjustment.CarrierAdjustmentVo;

import java.util.List;

/**
 * Posted from CarrierAdjustmentResponse
 * <p>
 * Author TanDT Date May 26, 2015
 */
public class CarrierAdjustmentResponse extends BaseResponse {
    private List<CarrierAdjustmentVo> listCarrierAdjustment;
    private Integer totalCarrierAdjustment;

    public List<CarrierAdjustmentVo> getListCarrierAdjustment() {
        return listCarrierAdjustment;
    }

    public void setListCarrierAdjustment(List<CarrierAdjustmentVo> listCarrierAdjustment) {
        this.listCarrierAdjustment = listCarrierAdjustment;
    }

    public Integer getTotalCarrierAdjustment() {
        return totalCarrierAdjustment;
    }

    public void setTotalCarrierAdjustment(Integer totalCarrierAdjustment) {
        this.totalCarrierAdjustment = totalCarrierAdjustment;
    }

}