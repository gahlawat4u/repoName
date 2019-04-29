package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.SystemSettingVo;

/**
 * Posted from SystemSettingResponse
 * <p>
 * Author HungNT Date Apr 16, 2015
 */
public class SystemSettingResponse extends BaseResponse {
    private SystemSettingVo systemSettingVo;

    public SystemSettingVo getSystemSettingVo() {
        return systemSettingVo;
    }

    public void setSystemSettingVo(SystemSettingVo systemSettingVo) {
        this.systemSettingVo = systemSettingVo;
    }
}
