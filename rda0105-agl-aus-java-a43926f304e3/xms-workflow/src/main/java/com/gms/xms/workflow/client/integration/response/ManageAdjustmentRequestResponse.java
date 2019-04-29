package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestDetailVo;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestTotalVo;

import java.util.List;

/**
 * Posted from ManageAdjustmentRequestResponse
 * </p>
 *
 * @author hungnt - Nov 4, 2015
 */
public class ManageAdjustmentRequestResponse extends BaseResponse {
    private List<AirbillAdjustmentRequestDetailVo> adjustmentList;
    private AirbillAdjustmentRequestTotalVo totalRecord;

    public List<AirbillAdjustmentRequestDetailVo> getAdjustmentList() {
        return adjustmentList;
    }

    public void setAdjustmentList(List<AirbillAdjustmentRequestDetailVo> adjustmentList) {
        this.adjustmentList = adjustmentList;
    }

    public AirbillAdjustmentRequestTotalVo getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(AirbillAdjustmentRequestTotalVo totalRecord) {
        this.totalRecord = totalRecord;
    }
}
