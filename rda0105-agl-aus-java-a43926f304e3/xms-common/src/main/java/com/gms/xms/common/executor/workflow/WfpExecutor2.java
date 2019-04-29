package com.gms.xms.common.executor.workflow;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.executor.IExecutable2;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from WfpExecutor.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:35:39 PM
 */
public class WfpExecutor2 implements IExecutable2 {
    private static final Log log = LogFactory.getLog(WfpExecutor2.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.gms.xms.common.executor.IExecutable#process(com.gms.xms.common.context.ContextBase)
     */
    @Override
    public ContextBase2 process(ContextBase2 context) {
        ContextBase2 result = null;
        try {
            ContextBase2 reqContext = new ContextBase2(null);
            reqContext.putAll(context);
            result = WorkFlowManager2.getInstance().process(reqContext);
        } catch (Exception e) {
            result = context;
            result.put(Attributes.ERROR_CODE, ErrorCode.WORKFLOW_EXCEPTION);
            log.error(e.getMessage(), e);
        }
        return result;
    }

}
