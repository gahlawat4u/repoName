package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.CreditNotesGSTSummaryModel;

import java.util.List;

/**
 * Posted from ManageCreditNoteModel
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class ManageCreditNoteModel extends BaseModel {
    private static final long serialVersionUID = 6832824278180120620L;
    private CreditNoteInfoModel creditNoteInfo;
    private List<CreditNoteShowListModel> listCreditNoteAdj;
    private CreditNoteInfoSystemAdminModel infoSystemAdmin;
    private String invSignature;
    private List<CreditNotesGSTSummaryModel> gstSummary;

    public CreditNoteInfoSystemAdminModel getInfoSystemAdmin() {
        return infoSystemAdmin;
    }

    public void setInfoSystemAdmin(CreditNoteInfoSystemAdminModel infoSystemAdmin) {
        this.infoSystemAdmin = infoSystemAdmin;
    }

    public List<CreditNoteShowListModel> getListCreditNoteAdj() {
        return listCreditNoteAdj;
    }

    public void setListCreditNoteAdj(List<CreditNoteShowListModel> listCreditNoteAdj) {
        this.listCreditNoteAdj = listCreditNoteAdj;
    }

    public CreditNoteInfoModel getCreditNoteInfo() {
        return creditNoteInfo;
    }

    public void setCreditNoteInfo(CreditNoteInfoModel creditNoteInfo) {
        this.creditNoteInfo = creditNoteInfo;
    }

    public String getInvSignature() {
        return invSignature;
    }

    public void setInvSignature(String invSignature) {
        this.invSignature = invSignature;
    }

    public List<CreditNotesGSTSummaryModel> getGstSummary() {
        return gstSummary;
    }

    public void setGstSummary(List<CreditNotesGSTSummaryModel> gstSummary) {
        this.gstSummary = gstSummary;
    }

    @Override
    public String toString() {
        return "ManageCreditNoteModel [creditNoteInfo=" + creditNoteInfo + ", listCreditNoteAdj=" + listCreditNoteAdj + ", infoSystemAdmin=" + infoSystemAdmin + ", invSignature=" + invSignature + ", gstSummary=" + gstSummary + "]";
    }
}