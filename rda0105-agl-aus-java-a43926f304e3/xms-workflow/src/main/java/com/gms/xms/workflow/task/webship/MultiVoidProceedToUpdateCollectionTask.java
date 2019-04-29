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
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from MultiVoidProceedToUpdateCollectionTask
 * <p>
 * Author TanDT Date May 7, 2015
 */
public class MultiVoidProceedToUpdateCollectionTask implements Task {
    private static final Log log = LogFactory.getLog(MultiVoidProceedToUpdateCollectionTask.class);

    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            HistoryRequest request = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESQUEST), HistoryRequest.class);
            if (request.getListShipmentId() != null) {
                String[] listShipmentId = request.getListShipmentId();
                for (int i = 0; i < listShipmentId.length; i++) {
                    if (!"".equals(listShipmentId[i].trim())) {
                        Long shipmentId = Long.parseLong(listShipmentId[i].trim());
                        ScheduleCollectionDao scheduleCollectionDao = new ScheduleCollectionDao();
                        Integer coutSchedule = scheduleCollectionDao.selectListScheduleCollectionBySmId(shipmentId).size();
                        if (coutSchedule > 0) {
                            ScheduleCollectionVo scheduleCollectionVo = new ScheduleCollectionVo();
                            scheduleCollectionVo.setConfirmationNo(request.getCollectionNoNew());
                            scheduleCollectionVo.setShipmentId(shipmentId);
                            scheduleCollectionDao.updateScheduleCollectionBySmId(addInfo, scheduleCollectionVo);
                        } else {
                            ShipmentVo shipmentVo = new ShipmentVo();
                            ShipmentDao shipmentDao = new ShipmentDao();
                            shipmentVo.setShipmentId(shipmentId);
                            shipmentVo.setDhlNote(request.getCollectionNoNew());
                            shipmentDao.update(addInfo, shipmentVo);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
