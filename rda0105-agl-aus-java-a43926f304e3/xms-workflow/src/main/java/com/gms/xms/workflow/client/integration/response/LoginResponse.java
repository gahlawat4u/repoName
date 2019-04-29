package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.webship.WebshipVo;

public class LoginResponse extends BaseResponse {
    private WebshipVo webshipVo;

    public WebshipVo getWebshipVo() {
        return webshipVo;
    }

    public void setWebshipVo(WebshipVo webshipVo) {
        this.webshipVo = webshipVo;
    }

}
