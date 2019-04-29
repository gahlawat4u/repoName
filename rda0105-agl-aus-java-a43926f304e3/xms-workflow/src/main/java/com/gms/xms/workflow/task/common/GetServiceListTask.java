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

import java.util.List;

/**
 * Posted from GetServiceListTask
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class GetServiceListTask implements Task {
    private static final Log log = LogFactory.getLog(GetServiceListTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ServiceDao serviceDao = new ServiceDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            List<ServiceVo> serviceList = serviceDao.selectAll();
            context.put(Attributes.SERVICE_LIST_RESULT, GsonUtils.toGson(serviceList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}
