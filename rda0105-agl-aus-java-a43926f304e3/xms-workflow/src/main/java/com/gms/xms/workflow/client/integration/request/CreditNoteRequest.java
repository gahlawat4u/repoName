package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.CreditNoteFilter;
import com.gms.xms.txndb.vo.UserVo;

/**
 * Posted from CreditNoteRequest
 * <p>
 * Author HungNT Date May 20, 2015
 */
public class CreditNoteRequest extends BaseRequest {
    private CreditNoteFilter creditNoteFilter;
    private UserVo userVo;
    private String creditNoteCode;

    public String getCreditNoteCode() {
        return creditNoteCode;
    }

    public void setCreditNoteCode(String creditNoteCode) {
        this.creditNoteCode = creditNoteCode;
    }

    public CreditNoteFilter getCreditNoteFilter() {
        return creditNoteFilter;
    }

    public void setCreditNoteFilter(CreditNoteFilter creditNoteFilter) {
        this.creditNoteFilter = creditNoteFilter;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }
}
