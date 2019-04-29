package com.gms.xms.workflow.core2;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Posted from WorkFlowManager.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:34:14 PM
 */
public class WorkFlowManager2 {

    private static class WorkFlowManagerHolder {
        private static WorkFlowManager2 instance = new WorkFlowManager2();
    }

    public static WorkFlowManager2 getInstance() {
        return WorkFlowManagerHolder.instance;
    }

    private static Map<String, WfpExecutor2> wfpMaps = new ConcurrentHashMap<String, WfpExecutor2>();
    Object lock = new Object();

    private WorkFlowManager2() {
        wfpMaps.clear();
    }

    /**
     * process flow
     *
     * @param context - context
     * @return - context result
     * @throws Exception - on error
     */
    public ContextBase2 process(ContextBase2 context) throws Exception {
        String wfpName = (String) context.get(Attributes.WFP_NAME);
        WfpExecutor2 executor = null;

        if (wfpMaps.containsKey(wfpName)) {
            executor = wfpMaps.get(wfpName);
        } else {
            try {
                executor = new WfpExecutor2(wfpName);
            } catch (Exception e) {
                throw e;
            }
            if (executor != null) {
                synchronized (lock) {
                    wfpMaps.put(wfpName, executor);
                }
            }
        }
        if (executor != null) {
            context = executor.process(context);
        } else
            throw new Exception("not found " + wfpName);
        return context;
    }

}
