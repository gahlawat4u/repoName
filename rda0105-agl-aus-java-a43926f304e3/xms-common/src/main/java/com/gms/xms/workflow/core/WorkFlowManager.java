package com.gms.xms.workflow.core;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Posted from WorkFlowManager.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:34:14 PM
 */
public class WorkFlowManager {

    private static class WorkFlowManagerHolder {
        private static WorkFlowManager instance = new WorkFlowManager();
    }

    public static WorkFlowManager getInstance() {
        return WorkFlowManagerHolder.instance;
    }

    private static Map<String, WfpExecutor> wfpMaps = new ConcurrentHashMap<String, WfpExecutor>();
    Object lock = new Object();

    private WorkFlowManager() {
        wfpMaps.clear();
    }

    /**
     * process flow
     *
     * @param context - context
     * @return - context result
     * @throws Exception - on error
     */
    public ContextBase process(ContextBase context) throws Exception {
        String wfpName = context.get(Attributes.WFP_NAME);
        WfpExecutor executor = null;

        if (wfpMaps.containsKey(wfpName)) {
            executor = wfpMaps.get(wfpName);
        } else {
            try {
                executor = new WfpExecutor(wfpName);
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
