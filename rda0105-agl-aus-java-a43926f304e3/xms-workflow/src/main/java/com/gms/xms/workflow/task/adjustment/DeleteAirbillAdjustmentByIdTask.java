package com.gms.xms.workflow.task.adjustment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.AirbillAdjustmentDao;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from DeleteAirbillAdjustmentByIdTask
 * <p>
 * Author DatTV Date May 14, 2015 5:09:33 PM
 */
public class DeleteAirbillAdjustmentByIdTask implements Task {
    private static final Log log = LogFactory.getLog(DeleteAirbillAdjustmentByIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao();
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        try {
            Long adjustmentId = Long.valueOf(context.get(Attributes.AIRBILL_ADJUSTMENT_ID));
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to delete airbill adjustment by id
            adjustmentDao.deleteById(addInfo, adjustmentId);
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
