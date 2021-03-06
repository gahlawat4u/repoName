package com.gms.xms.workflow.task.adjustment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CreditNoteDaoService;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from EditAdjustmentTask
 * <p>
 * Author DatTV Date Jun 10, 2015 10:04:53 PM
 */
public class EditAdjustmentTask implements Task {
    private static final Log log = LogFactory.getLog(EditAdjustmentTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        CreditNoteDaoService creditNoteDaoService = new CreditNoteDaoService();
        try {
            AirbillAdjustmentVo adjustmentVo = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_OBJECT), AirbillAdjustmentVo.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            creditNoteDaoService.editAdjustment(addInfo, adjustmentVo);
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
