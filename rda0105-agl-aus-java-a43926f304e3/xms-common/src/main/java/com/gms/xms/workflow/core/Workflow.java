package com.gms.xms.workflow.core;

import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Posted from Workflow.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:32:44 PM
 */

/**
 * Posted from Workflow.java
 * <p/>
 * Author Toantq Date Mar 22, 2015 Time: 5:33:20 PM
 */
public class Workflow {
    private static final Log log = LogFactory.getLog(Workflow.class);
    private Map<String, Flow> workflowMap = null;
    private Flow flow;

    private static class WorkflowHolder {
        private static Workflow instance = new Workflow();
    }

    public static Workflow getInstance() {
        return WorkflowHolder.instance;
    }

    private Workflow() {
    }

    /**
     * add flow
     */
    public void addFlow() {
        workflowMap.put(flow.getName(), flow);
    }

    /**
     * set flow
     *
     * @param flow
     */
    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public Flow getFlow(String name) {
        Flow result = null;
        try {
            result = workflowMap.get(name);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    /**
     * add task to flow
     *
     * @param type
     *            - type of task
     * @param task
     *            -task object
     */
    public void addTask(String type, Task task) {
        flow.addTask(type, task);
    }

    public void init() {
        workflowMap = new ConcurrentHashMap<String, Flow>();
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addRule("*/workflow/flow", new FlowRule());
        digester.addRule("*/workflow/flow/task", new TaskRule());
        try {
            digester.parse(Workflow.class.getResource("/workflows.xml"));
        } catch (Exception e) {
            log.error("Error on initializing workflow" + e.getMessage(), e);
        }
    }

    public Map<String, Flow> getWorkflows() {
        return workflowMap;
    }

}
