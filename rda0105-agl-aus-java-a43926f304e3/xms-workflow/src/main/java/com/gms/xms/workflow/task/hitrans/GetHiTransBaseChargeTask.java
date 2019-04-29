package com.gms.xms.workflow.task.hitrans;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author tkvcl
 */
public class GetHiTransBaseChargeTask implements Task {
    private static final Log log = LogFactory.getLog(GetHiTransBaseChargeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, "There is an error in calculating base charge, please contact admin.");
            return false;
        }

        return true;
    }
}
