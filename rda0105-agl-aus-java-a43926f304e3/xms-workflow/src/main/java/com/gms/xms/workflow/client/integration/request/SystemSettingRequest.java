package com.gms.xms.workflow.client.integration.request;

/**
 * Posted from SystemSettingRequest
 * <p>
 * Author HungNT Date Apr 16, 2015
 */
public class SystemSettingRequest extends BaseRequest {
    private String settingName;

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

}
