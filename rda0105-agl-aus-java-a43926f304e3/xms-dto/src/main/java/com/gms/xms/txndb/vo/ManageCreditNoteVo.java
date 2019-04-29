package com.gms.xms.txndb.vo;

import java.util.List;

/**
 * Posted from ManageCreditNoteVo
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class ManageCreditNoteVo extends BaseVo {
    private static final long serialVersionUID = 1L;
    private CreditNoteInfoVo creditNoteInfo;
    private List<CreditNoteShowListVo> listCreditNoteAdj;
    private CreditNoteInfoSystemAdminVo infoSystemAdmin;
    private List<CreditNotesGSTSummaryVo> gstSummary;

    public CreditNoteInfoSystemAdminVo getInfoSystemAdmin() {
        return infoSystemAdmin;
    }

    public void setInfoSystemAdmin(CreditNoteInfoSystemAdminVo infoSystemAdmin) {
        this.infoSystemAdmin = infoSystemAdmin;
    }

    public List<CreditNoteShowListVo> getListCreditNoteAdj() {
        return listCreditNoteAdj;
    }

    public void setListCreditNoteAdj(List<CreditNoteShowListVo> listCreditNoteAdj) {
        this.listCreditNoteAdj = listCreditNoteAdj;
    }

    public CreditNoteInfoVo getCreditNoteInfo() {
        return creditNoteInfo;
    }

    public void setCreditNoteInfo(CreditNoteInfoVo creditNoteInfo) {
        this.creditNoteInfo = creditNoteInfo;
    }

    public List<CreditNotesGSTSummaryVo> getGstSummary() {
        return gstSummary;
    }

    public void setGstSummary(List<CreditNotesGSTSummaryVo> gstSummary) {
        this.gstSummary = gstSummary;
    }

    @Override
    public String toString() {
        return "ManageCreditNoteVo [creditNoteInfo=" + creditNoteInfo + ", listCreditNoteAdj=" + listCreditNoteAdj + ", infoSystemAdmin=" + infoSystemAdmin + ", gstSummary=" + gstSummary + "]";
    }

}