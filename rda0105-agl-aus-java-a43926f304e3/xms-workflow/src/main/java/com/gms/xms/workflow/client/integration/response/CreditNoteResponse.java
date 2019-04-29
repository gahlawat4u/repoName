package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.*;

import java.util.List;

/**
 * Posted from CreditNoteResponse
 * <p>
 * Author HungNT Date May 20, 2015
 */
public class CreditNoteResponse extends BaseResponse {
    private List<String> dateList;
    private List<CreditNoteVo> creditNoteVos;
    private List<CustomerVo> listCustomerVos;
    private CreditNoteInfoVo creditNoteInfo;
    private List<CreditNoteShowListVo> listAdjustment;
    private CreditNoteInfoSystemAdminVo infoSystemAdminVo;
    private UserVo userVo;
    private CreditNoteVo creditNoteVo;
    private List<CreditNotesGSTSummaryVo> creditNotesGSTSummaryVos;

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public List<CreditNoteVo> getCreditNoteVos() {
        return creditNoteVos;
    }

    public void setCreditNoteVos(List<CreditNoteVo> creditNoteVos) {
        this.creditNoteVos = creditNoteVos;
    }

    public List<CustomerVo> getListCustomerVos() {
        return listCustomerVos;
    }

    public void setListCustomerVos(List<CustomerVo> listCustomerVos) {
        this.listCustomerVos = listCustomerVos;
    }

    public CreditNoteInfoVo getCreditNoteInfo() {
        return creditNoteInfo;
    }

    public void setCreditNoteInfo(CreditNoteInfoVo creditNoteInfo) {
        this.creditNoteInfo = creditNoteInfo;
    }

    public List<CreditNoteShowListVo> getListAdjustment() {
        return listAdjustment;
    }

    public void setListAdjustment(List<CreditNoteShowListVo> listAdjustment) {
        this.listAdjustment = listAdjustment;
    }

    public CreditNoteInfoSystemAdminVo getInfoSystemAdminVo() {
        return infoSystemAdminVo;
    }

    public void setInfoSystemAdminVo(CreditNoteInfoSystemAdminVo infoSystemAdminVo) {
        this.infoSystemAdminVo = infoSystemAdminVo;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public CreditNoteVo getCreditNoteVo() {
        return creditNoteVo;
    }

    public void setCreditNoteVo(CreditNoteVo creditNoteVo) {
        this.creditNoteVo = creditNoteVo;
    }

    public List<CreditNotesGSTSummaryVo> getCreditNotesGSTSummaryVos() {
        return creditNotesGSTSummaryVos;
    }

    public void setCreditNotesGSTSummaryVos(List<CreditNotesGSTSummaryVo> creditNotesGSTSummaryVos) {
        this.creditNotesGSTSummaryVos = creditNotesGSTSummaryVos;
    }
}
