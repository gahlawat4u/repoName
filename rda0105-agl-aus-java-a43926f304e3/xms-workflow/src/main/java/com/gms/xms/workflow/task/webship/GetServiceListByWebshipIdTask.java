package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ServiceDao;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from GetServiceListByWebshipIdTask
 * <p>
 * Author DatTV Date Mar 27, 2015
 */
public class GetServiceListByWebshipIdTask implements Task {
    private static final Log log = LogFactory.getLog(GetServiceListByWebshipIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ServiceDao serviceDao = new ServiceDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        int webshipId = Integer.parseInt(context.getString(Attributes.WEBSHIP_ID));
        int carrierType = Integer.parseInt(context.getString(Attributes.CARRIER_TYPE));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("webshipId", webshipId);
        map.put("carrierType", carrierType);
        try {
            List<ServiceVo> serviceList = serviceDao.getServiceListByWebshipId(map);
            context.put(Attributes.SERVICE_LIST_RESULT, GsonUtils.toGson(serviceList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}
