package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.txndb.vo.CreditNotesGSTSummaryVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from SendCreditNotesRequest
 * <p>
 * Author HungNT Date May 21, 2015
 */
public class SendCreditNotesRequest extends BaseRequest {
    private String tmpPath;
    private List<CreditNoteVo> creditNoteVos;
    private Map<String, String> addInfo;
    private List<CreditNotesGSTSummaryVo> gstSummary;

    public String getTmpPath() {
        return tmpPath;
    }

    public void setTmpPath(String tmpPath) {
        this.tmpPath = tmpPath;
    }

    public List<CreditNoteVo> getCreditNoteVos() {
        return creditNoteVos;
    }

    public void setCreditNoteVos(List<CreditNoteVo> creditNoteVos) {
        this.creditNoteVos = creditNoteVos;
    }

    public Map<String, String> getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(Map<String, String> addInfo) {
        this.addInfo = addInfo;
    }

    public List<CreditNotesGSTSummaryVo> getGstSummary() {
        return gstSummary;
    }

    public void setGstSummary(List<CreditNotesGSTSummaryVo> gstSummary) {
        this.gstSummary = gstSummary;
    }
}
