package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CustomerDefaultSettingDao;
import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from UpdateCustomerDefaultSettingTask
 * <p>
 * Author DatTV Date Apr 1, 2015
 */
public class UpdateCustomerDefaultSettingTask implements Task {
    private static final Log log = LogFactory.getLog(UpdateCustomerDefaultSettingTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerDefaultSettingDao customerDefaultSettingDao = new CustomerDefaultSettingDao();
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            CustomerDefaultSettingVo customerDefaultSettingVo = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_DEFAULT_SETTING_OBJECT), CustomerDefaultSettingVo.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            customerDefaultSettingDao.update(addInfo, customerDefaultSettingVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
