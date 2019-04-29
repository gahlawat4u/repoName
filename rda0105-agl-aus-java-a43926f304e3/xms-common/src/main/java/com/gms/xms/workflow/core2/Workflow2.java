package com.gms.xms.workflow.core2;

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
public class Workflow2 {
    private static final Log log = LogFactory.getLog(Workflow2.class);
    private Map<String, Flow2> workflowMap = null;
    private Flow2 flow;

    private static class WorkflowHolder {
        private static Workflow2 instance = new Workflow2();
    }

    public static Workflow2 getInstance() {
        return WorkflowHolder.instance;
    }

    private Workflow2() {
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
    public void setFlow(Flow2 flow) {
        this.flow = flow;
    }

    public Flow2 getFlow(String name) {
        Flow2 result = null;
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
    public void addTask(String type, Task2 task) {
        flow.addTask(type, task);
    }

    public void init() {
        workflowMap = new ConcurrentHashMap<String, Flow2>();
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addRule("*/workflow2/flow", new FlowRule2());
        digester.addRule("*/workflow2/flow/task", new TaskRule2());
        try {
            digester.parse(Workflow2.class.getResource("/workflows2.xml"));
        } catch (Exception e) {
            log.error("Error on initializing workflow" + e.getMessage(), e);
        }
    }

    public Map<String, Flow2> getWorkflows() {
        return workflowMap;
    }

}
