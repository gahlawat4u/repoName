package com.gms.xms.workflow.task.adjustmentrequest;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CreditNoteDaoService;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from EditAdjustmentTask
 * </p>
 *
 * @author hungnt - Nov 3, 2015
 */
public class EditAdjustmentTask implements Task {
    private static final Log log = LogFactory.getLog(EditAdjustmentTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        CreditNoteDaoService creditNoteDaoService = new CreditNoteDaoService();
        try {
            AirbillAdjustmentRequestVo adjustmentVo = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_OBJECT), AirbillAdjustmentRequestVo.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            creditNoteDaoService.editAdjustmentRequest(addInfo, adjustmentVo);
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
