package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.txndb.vo.SystemSettingVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetSystemSettingBySettingNameTask
 * <p>
 * Author HungNT Date Apr 16, 2015
 */
public class GetSystemSettingBySettingNameTask implements Task {
    private static final Log log = LogFactory.getLog(GetSystemSettingBySettingNameTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        SystemSettingDao systemSettingDao = new SystemSettingDao();
        String settingName = context.get(Attributes.SYSTEM_SETTING_NAME);
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            SystemSettingVo systemSettingVo = systemSettingDao.getSystemSettingByName(settingName);
            context.put(Attributes.SYSTEM_SETTING_RESULT, GsonUtils.toGson(systemSettingVo));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }

        return true;
    }
}
