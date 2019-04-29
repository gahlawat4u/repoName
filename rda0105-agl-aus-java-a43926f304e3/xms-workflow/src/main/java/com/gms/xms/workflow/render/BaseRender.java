package com.gms.xms.workflow.render;

import java.util.Map;

/**
 * Posted from BaseRender
 * <p>
 * Author @author HungNT Dec 24, 2015
 */
public class BaseRender {
    private Map<String, String> addInfo;

    public BaseRender(Map<String, String> addInfo) {
        this.addInfo = addInfo;
    }

    public Map<String, String> getAddInfo() {
        return addInfo;
    }
}
