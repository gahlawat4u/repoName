package com.gms.xms.workflow.client.integration.request;

/**
 * Posted from ChangePasswordRequest
 * <p>
 * Author HungNT Date Apr 11, 2015
 */
public class ChangePasswordRequest extends BaseRequest {
    private Long webshipId;
    private String encryptPass;

    public Long getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(Long webshipId) {
        this.webshipId = webshipId;
    }

    public String getEncryptPass() {
        return encryptPass;
    }

    public void setEncryptPass(String encryptPass) {
        this.encryptPass = encryptPass;
    }
}
