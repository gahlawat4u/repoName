package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.adjustment.AirbillAdjustmentDetailVo;
import com.gms.xms.txndb.vo.adjustment.AirbillAdjustmentTotalVo;

import java.util.List;

/**
 * Posted from ManageAdjustmentResponse
 * <p>
 * Author DatTV Date May 14, 2015 10:18:11 AM
 */
public class ManageAdjustmentResponse extends BaseResponse {
    private List<AirbillAdjustmentDetailVo> adjustmentList;
    private AirbillAdjustmentTotalVo totalRecord;

    public List<AirbillAdjustmentDetailVo> getAdjustmentList() {
        return adjustmentList;
    }

    public void setAdjustmentList(List<AirbillAdjustmentDetailVo> adjustmentList) {
        this.adjustmentList = adjustmentList;
    }

    public AirbillAdjustmentTotalVo getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(AirbillAdjustmentTotalVo totalRecord) {
        this.totalRecord = totalRecord;
    }
}
