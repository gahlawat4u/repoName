package com.gms.xms.workflow.core;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from WfpExecutor.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:32:15 PM
 */
public class WfpExecutor implements IWorkflow<ContextBase> {
    private static final Log log = LogFactory.getLog(WfpExecutor.class);
    private Task task = null;
    private String wfName = null;
    private static Workflow workflow;

    static {
        if (Workflow.getInstance().getWorkflows() == null) {
            Workflow.getInstance().init();
            workflow = Workflow.getInstance();
        }
    }

    public WfpExecutor(String wfName) throws Exception {
        this.wfName = wfName;
        try {
            task = workflow.getFlow(this.wfName);
            if (task == null) {
                log.error("Task is null in work flow : " + wfName);
                throw new RuntimeException("Task is null in workflow");
            }
        } catch (Exception e) {
            log.error("Fail to create workflow-" + wfName + "| errMsg-" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public String getWfName() {

        return wfName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.gms.xms.workflow.core.IWorkflow#process(com.gms.xms.common.context.ContextBase)
     */
    @Override
    public ContextBase process(ContextBase context) throws Exception {
        log.info(wfName + " is started: " + context.toString());
        context.put(Attributes.WFP_NAME, wfName);
        try {
            task.execute(context);
        } catch (Exception e) {
            log.error("Exception occured in workflow " + wfName + " : " + e.getMessage(), e);
        }

        return context;
    }

}
