package com.gms.xms.workflow.core2;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Posted from WfpExecutor.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:32:15 PM
 */
public class WfpExecutor2 implements IWorkflow2<ContextBase2> {
    private static final Log log = LogFactory.getLog(WfpExecutor2.class);
    private Task2 task = null;
    private String wfName = null;
    private static Workflow2 workflow;

    static {
        if (Workflow2.getInstance().getWorkflows() == null) {
            Workflow2.getInstance().init();
            workflow = Workflow2.getInstance();
        }
    }

    public WfpExecutor2(String wfName) throws Exception {
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
    public ContextBase2 process(ContextBase2 context) throws Exception {
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
