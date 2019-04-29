package com.gms.xms.workflow.core;

import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

/**
 * Posted from FlowRule.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:28:45 PM
 */
class FlowRule extends Rule {
    private String attribute = "name";

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.commons.digester.Rule#begin(java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void begin(String namespace, String name, Attributes attributes) throws Exception {
        String value = attributes.getValue(attribute);
        Flow flow = new Flow();
        flow.setName(value);
        Workflow.getInstance().setFlow(flow);
    }
}
