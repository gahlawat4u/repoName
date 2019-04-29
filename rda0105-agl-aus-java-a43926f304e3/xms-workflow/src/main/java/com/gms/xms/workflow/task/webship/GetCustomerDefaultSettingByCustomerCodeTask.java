package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CustomerDefaultSettingDao;
import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetCustomerDefaultSettingTask
 * <p>
 * Author DatTV Date Mar 26, 2015
 */
public class GetCustomerDefaultSettingByCustomerCodeTask implements Task {
    private static final Log log = LogFactory.getLog(GetCustomerDefaultSettingByCustomerCodeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerDefaultSettingDao customerDefaultSettingDao = new CustomerDefaultSettingDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            long customerCode = Long.parseLong(context.getString(Attributes.WEBSHIP_CUSTOMER_CODE));
            CustomerDefaultSettingVo customerDefaultSettingVo = customerDefaultSettingDao.selectDetailByCustomerCode(customerCode);
            context.put(Attributes.CUSTOMER_DEFAULT_SETTING_RESULT, GsonUtils.toGson(customerDefaultSettingVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}
