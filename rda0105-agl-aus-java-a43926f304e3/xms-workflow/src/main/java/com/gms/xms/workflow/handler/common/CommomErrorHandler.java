package com.gms.xms.workflow.handler.common;

import com.gms.xms.common.context.ContextBase;
import com.gms.xms.workflow.core.Handler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommomErrorHandler extends Handler {
    private static Log log = LogFactory.getLog(CommomErrorHandler.class);

    @Override
    public boolean handle(ContextBase context, Exception exception) {
        try {
            log.error("CommomErrorHandler");
        } catch (Exception e) {
            log.error("Fail to Handle Error.| ErrMsg-" + e.getMessage(), e);
        }
        return true;
    }

}
