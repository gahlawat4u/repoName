package com.gms.xms.workflow.task.adjustment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CarrierAdjustmentRejectSubmittedDaoService;
import com.gms.xms.workflow.client.integration.request.CarrierAdjustmentRequest;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Posted from CarrierAdjustmentRejectTask
 * <p>
 * Author TanDT Date Jun 1, 2015
 */
public class CarrierAdjustmentRejectTask implements Task {
    private static final Log log = LogFactory.getLog(CarrierAdjustmentRejectTask.class);
    private CarrierAdjustmentRejectSubmittedDaoService dao = new CarrierAdjustmentRejectSubmittedDaoService();

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            CarrierAdjustmentRequest request = GsonUtils.fromGson(context.get(Attributes.CARRIER_ADJUSTMENT_REQUEST), CarrierAdjustmentRequest.class);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            String[] listAdjId = request.getListAdjustmentId();
            List<Long> listAdjustmentId = new ArrayList<Long>();
            for (int i = 0; i < listAdjId.length; i++) {
                Long adjId = Long.parseLong(listAdjId[i].trim());
                if (adjId != 0) {
                    listAdjustmentId.add(adjId);
                }
            }
            dao.updateMultiCarrierAdjustmentById(addInfo, request.getUserId(), listAdjustmentId);
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
