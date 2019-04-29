package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.webship.WebshipVo;

/**
 * Posted from ChangePasswordResponse
 * <p>
 * Author HungNT Date Apr 11, 2015
 */
public class ChangePasswordResponse extends BaseResponse {
    private WebshipVo webshipVo;

    public WebshipVo getWebshipVo() {
        return webshipVo;
    }

    public void setWebshipVo(WebshipVo webshipVo) {
        this.webshipVo = webshipVo;
    }
}