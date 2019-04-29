package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.workflow.client.WebshipHistoryClient;
import com.gms.xms.workflow.client.integration.request.HistoryRequest;
import com.gms.xms.workflow.client.integration.response.HistoryResponse;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from MultiVoidProceedToVoidTask
 * <p>
 * Author TanDT Date May 6, 2015
 */
public class MultiVoidProceedToVoidTask implements Task {
    private static final Log log = LogFactory.getLog(MultiVoidProceedToVoidTask.class);

    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        WebshipHistoryClient client = new WebshipHistoryClient(addInfo);
        HistoryRequest request = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESQUEST), HistoryRequest.class);
        HistoryResponse response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        try {
            if (request.getListShipmentId() != null) {
                String[] listShipmentId = request.getListShipmentId();
                for (int i = 0; i < listShipmentId.length; i++) {
                    if (!"".equals(listShipmentId[i].trim())) {
                        Long shipmentId = Long.parseLong(listShipmentId[i].trim());
                        request.setShipmentId(shipmentId);
                        request.setSchID(0);
                        response = client.voidShipmentHistoryAc(request);
                    }
                }

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
