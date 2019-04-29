package com.gms.xms.common.executor.workflow;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.executor.IExecutable;
import com.gms.xms.workflow.core.WorkFlowManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from WfpExecutor.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:35:39 PM
 */
public class WfpExecutor implements IExecutable {
    private static final Log log = LogFactory.getLog(WfpExecutor.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.gms.xms.common.executor.IExecutable#process(com.gms.xms.common.context.ContextBase)
     */
    @Override
    public ContextBase process(ContextBase context) {
        ContextBase result = null;
        try {
            ContextBase reqContext = new ContextBase(null);
            reqContext.putAll(context);
            result = WorkFlowManager.getInstance().process(reqContext);
        } catch (Exception e) {
            result = context;
            result.put(Attributes.ERROR_CODE, ErrorCode.WORKFLOW_EXCEPTION);
            log.error(e.getMessage(), e);
        }
        return result;
    }

}
