package com.gms.xms.workflow.task.adjustment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CreditNoteDaoService;
import com.gms.xms.workflow.client.integration.request.AdjustmentRequest;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from SaveAdjustmentRequestsTask
 * <p>
 * Author DatTV Date May 20, 2015 8:38:32 PM
 */
public class SaveAdjustmentRequestsTask implements Task {
    private static final Log log = LogFactory.getLog(SaveAdjustmentRequestsTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            AdjustmentRequest request = GsonUtils.fromGson(context.get(Attributes.ADJUSTMENT_REQUEST), AdjustmentRequest.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());

            // Do DAO service to update airbill adjustment
            CreditNoteDaoService daoService = new CreditNoteDaoService();
            daoService.saveAdjustmentRequests(addInfo, request.getCreditNote(), request.getAdjustmentList());
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
