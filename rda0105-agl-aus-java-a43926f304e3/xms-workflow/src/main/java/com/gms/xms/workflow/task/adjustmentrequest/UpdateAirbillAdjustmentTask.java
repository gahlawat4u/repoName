package com.gms.xms.workflow.task.adjustmentrequest;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.adjustmentrequest.AirbillAdjustmentRequestDao;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from UpdateAirbillAdjustmentTask
 * </p>
 *
 * @author hungnt - Nov 4, 2015
 */
public class UpdateAirbillAdjustmentTask implements Task {
    private static final Log log = LogFactory.getLog(UpdateAirbillAdjustmentTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            AirbillAdjustmentRequestVo adjustmentRequestVo = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_OBJECT), AirbillAdjustmentRequestVo.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            AirbillAdjustmentRequestDao adjustmentRequestDao = new AirbillAdjustmentRequestDao();
            adjustmentRequestDao.update(addInfo, adjustmentRequestVo);
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
