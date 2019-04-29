package com.gms.xms.workflow.client;

import java.util.Map;

public class WorkflowBaseClient {
    private Map<String, String> addInfo;

    public WorkflowBaseClient(Map<String, String> addInfo) {
        this.addInfo = addInfo;
    }

    public Map<String, String> getAddInfo() {
        return addInfo;
    }
}
