package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentNoteDao;
import com.gms.xms.txndb.vo.ShipmentNoteVo;
import com.gms.xms.workflow.client.integration.request.HistoryRequest;
import com.gms.xms.workflow.client.integration.response.HistoryResponse;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from InsertShipmentNoteTask
 * <p>
 * Author TanDT Date Apr 25, 2015
 */
public class InsertShipmentNoteTask implements Task {
    private static final Log log = LogFactory.getLog(InsertShipmentNoteTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentNoteDao dao = new ShipmentNoteDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            HistoryRequest request = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESQUEST), HistoryRequest.class);
            HistoryResponse response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
            ShipmentNoteVo shipmentNoteVo = request.getShipmentNoteVo();
            Integer statusNodeInsert = dao.insertShipmentNote(addInfo, shipmentNoteVo);
            response.setStatusInsertNode(statusNodeInsert);
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
