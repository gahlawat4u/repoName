package com.gms.xms.workflow.client;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.txndb.vo.SystemSettingVo;
import com.gms.xms.workflow.client.integration.request.SystemSettingRequest;
import com.gms.xms.workflow.client.integration.response.SystemSettingResponse;
import com.gms.xms.workflow.core.WorkFlowManager;

import java.util.Map;

/**
 * Posted from SystemSettingClient
 * <p>
 * Author HungNT Date Apr 16, 2015
 */
public class SystemSettingClient extends WorkflowBaseClient {
    public SystemSettingClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    public SystemSettingResponse getSystemSettingByName(SystemSettingRequest systemSettingRequest) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        String settingName = systemSettingRequest.getSettingName();
        context.put(Attributes.SYSTEM_SETTING_NAME, settingName);
        context.put(Attributes.WFP_NAME, "Wfl-GetSystemSettingBySettingName");
        context = WorkFlowManager.getInstance().process(context);

        SystemSettingResponse systemSettingResponse = new SystemSettingResponse();
        systemSettingResponse.setErrorCode(context.get(Attributes.ERROR_CODE));
        systemSettingResponse.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        systemSettingResponse.setSystemSettingVo(GsonUtils.fromGson(context.get(Attributes.SYSTEM_SETTING_RESULT), SystemSettingVo.class));

        return systemSettingResponse;
    }
}
