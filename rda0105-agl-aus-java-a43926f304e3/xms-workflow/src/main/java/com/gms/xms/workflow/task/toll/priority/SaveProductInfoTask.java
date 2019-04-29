package com.gms.xms.workflow.task.toll.priority;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from SaveProductInfoTask
 * <p>
 * Author TANDT
 */
public class SaveProductInfoTask implements Task {
    private static final Log log = LogFactory.getLog(SaveProductInfoTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
