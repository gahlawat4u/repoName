package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.txndb.vo.CreditNoteVo;

import java.util.List;

/**
 * Posted from AdjusmentRequest
 * <p>
 * Author DatTV Date May 20, 2015 8:26:12 PM
 */
public class AdjustmentRequest extends BaseRequest {
    private CreditNoteVo creditNote;
    private List<AirbillAdjustmentVo> adjustmentList;

    public CreditNoteVo getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(CreditNoteVo creditNote) {
        this.creditNote = creditNote;
    }

    public List<AirbillAdjustmentVo> getAdjustmentList() {
        return adjustmentList;
    }

    public void setAdjustmentList(List<AirbillAdjustmentVo> adjustmentList) {
        this.adjustmentList = adjustmentList;
    }
}
