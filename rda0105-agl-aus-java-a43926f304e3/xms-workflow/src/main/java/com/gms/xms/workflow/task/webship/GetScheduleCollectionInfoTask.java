package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ScheduleCollectionDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.client.integration.request.ScheduleCollectionRequest;
import com.gms.xms.workflow.client.integration.response.ScheduleCollectionResponse;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GetScheduleCollectionInfoTask implements Task {
    private static final Log log = LogFactory.getLog(GetScheduleCollectionInfoTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        ScheduleCollectionDao dao = new ScheduleCollectionDao();
        ShipmentDao shipmentDao = new ShipmentDao();
        try {
            ScheduleCollectionRequest request = GsonUtils.fromGson(Attributes.HISTORY_RESQUEST, ScheduleCollectionRequest.class);
            ScheduleCollectionResponse response = GsonUtils.fromGson(Attributes.HISTORY_RESPONSE, ScheduleCollectionResponse.class);
            if (request.getShipmentId() != null) {
                ScheduleCollectionVo scheduleCollectionVo = dao.selectScheduleCollectionBySmId(request.getShipmentId());
                ShipmentVo shipmentVo = shipmentDao.getShipmentForHistoryDetail(request.getShipmentId());
                response.setShipmentVo(shipmentVo);
                response.setScheduleCollectionVo(scheduleCollectionVo);
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
