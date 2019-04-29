package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestVo;

import java.util.List;

/**
 * Posted from AdjusmentRequest
 * <p>
 * Author DatTV Date May 20, 2015 8:26:12 PM
 */
public class AdjustmentClaimRequest extends BaseRequest {
    private CreditNoteVo creditNote;
    private List<AirbillAdjustmentRequestVo> adjustmentList;

    public CreditNoteVo getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(CreditNoteVo creditNote) {
        this.creditNote = creditNote;
    }

    public List<AirbillAdjustmentRequestVo> getAdjustmentList() {
        return adjustmentList;
    }

    public void setAdjustmentList(List<AirbillAdjustmentRequestVo> adjustmentList) {
        this.adjustmentList = adjustmentList;
    }
}
