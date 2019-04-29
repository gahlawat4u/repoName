package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.TrackingDao;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.TrackingVo;
import com.gms.xms.workflow.client.integration.response.TrackingResponse;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from DhlTrackingReadResponseTask
 * <p>
 * Author TanDT Date Apr 16, 2015
 */
public class DhlTrackingReadResponseTask implements Task {
    private static final Log log = LogFactory.getLog(DhlTrackingReadResponseTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        TrackingDao trackingDao = new TrackingDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        ShipmentVo shipmentVo = new ShipmentVo();
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            TrackingResponse response = GsonUtils.fromGson(context.getString(Attributes.DHL_DELIVERY_TRACKING_RESPONSE), TrackingResponse.class);
            if (response.getShipmentVo() != null) {
                shipmentVo = response.getShipmentVo();
            }
            if (response.getListTrackingVo() != null) {
                List<TrackingVo> listTracking = response.getListTrackingVo();
                TrackingVo trackingVo = new TrackingVo();
                trackingVo.setAirbillNumber(shipmentVo.getAirbillNumber());
                trackingVo.setShipmentId(shipmentVo.getShipmentId());
                trackingDao.delTracking(addInfo, trackingVo);
                boolean isDelivered = false;
                if (listTracking.size() == 1) {
                    String eventCode = listTracking.get(0).getEventCode();
                    trackingDao.insTracking(addInfo, listTracking.get(0));
                    if (eventCode.equals("OK")) {
                        isDelivered = true;
                    }
                } else {
                    for (int i = 0; i < listTracking.size(); i++) {
                        String eventCode = listTracking.get(i).getEventCode();
                        trackingDao.insTracking(addInfo, listTracking.get(i));
                        if (eventCode.equals("OK")) {
                            isDelivered = true;
                        }
                    }
                }
                if (isDelivered) {
                    trackingVo.setTrackStatus(Byte.parseByte("1"));
                    trackingDao.updateTracking(addInfo, trackingVo);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }
}
