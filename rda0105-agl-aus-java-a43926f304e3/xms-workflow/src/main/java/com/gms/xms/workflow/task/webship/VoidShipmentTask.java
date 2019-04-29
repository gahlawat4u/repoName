package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ScheduleCollectionDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.client.integration.request.HistoryRequest;
import com.gms.xms.workflow.client.integration.response.HistoryResponse;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.Map;

/**
 * Posted from VoidShipmentTask
 * <p>
 * Author TanDT Date Apr 20, 2015
 */
public class VoidShipmentTask implements Task {
    private static final Log log = LogFactory.getLog(VoidShipmentTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentDao shipmentDao = new ShipmentDao();
        ScheduleCollectionDao scheduleCollectionDao = new ScheduleCollectionDao();
        Date date = new Date();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            HistoryRequest request = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESQUEST), HistoryRequest.class);
            HistoryResponse response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
            if (request.getShipmentId() != null && request.getSchID() == 0) {
                ShipmentVo shipmentVo = new ShipmentVo();
                shipmentVo.setShipmentId(request.getShipmentId());
                shipmentVo.setCreateDate(date);
                shipmentVo.setStatus(1);
                shipmentDao.update(addInfo, shipmentVo);
            }
            if (request.getSchID() != 0) {
                ScheduleCollectionVo scheduleCollectionVo = new ScheduleCollectionVo();
                scheduleCollectionVo.setStatus(Byte.parseByte("0"));
                scheduleCollectionVo.setShipmentId(request.getShipmentId());
                scheduleCollectionVo.setCreateDate(date);
                // Put signal to the context to log update schedule collection as Cancel Pickup.
                context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Cancel Pickup");
                scheduleCollectionDao.updateScheduleCollectionById(addInfo, scheduleCollectionVo);
            }
            context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
