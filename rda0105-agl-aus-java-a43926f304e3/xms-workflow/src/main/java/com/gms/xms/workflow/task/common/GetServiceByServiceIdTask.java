package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ServiceDao;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetServiceByServiceIdTask
 * <p>
 * Author HungNT Date Apr 16, 2015
 */
public class GetServiceByServiceIdTask implements Task {
    private static final Log log = LogFactory.getLog(GetServiceByServiceIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ServiceDao serviceDao = new ServiceDao();
        int serviceId = Integer.parseInt(context.get(Attributes.SERVICE_ID));

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ServiceVo serviceVo = serviceDao.selectById(serviceId);
            context.put(Attributes.SERVICE_RESULT, GsonUtils.toGson(serviceVo));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }

        return true;
    }

}
