package com.gms.xms.workflow.service;

import java.util.Map;

/**
 * Posted from Jul 30, 2016 12:11:15 PM
 * <p>
 * Author dattrinh
 */
public class BaseService {
    private Map<String, String> context;

    public BaseService(Map<String, String> context) {
        super();
        this.context = context;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }
}
