package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.webship.WebshipVo;

public class LoginRequest extends BaseRequest {
    public WebshipVo webshipVo;

    public WebshipVo getWebshipVo() {
        return webshipVo;
    }

    public void setWebshipVo(WebshipVo webshipVo) {
        this.webshipVo = webshipVo;
    }

}
