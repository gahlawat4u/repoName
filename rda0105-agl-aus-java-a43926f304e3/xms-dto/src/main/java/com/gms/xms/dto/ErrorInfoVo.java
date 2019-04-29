package com.gms.xms.dto;

import com.gms.xms.txndb.vo.BaseVo;

public class ErrorInfoVo extends BaseVo {
    private static final long serialVersionUID = 3128544234418669075L;
    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "ErrorInfoVo [errCode=" + errCode + ", errMsg=" + errMsg + "]";
    }


}
