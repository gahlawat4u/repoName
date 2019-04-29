package com.gms.xms.workflow.core2;

import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

/**
 * Posted from FlowRule.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:28:45 PM
 */
class FlowRule2 extends Rule {
    private String attribute = "name";

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.commons.digester.Rule#begin(java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void begin(String namespace, String name, Attributes attributes) throws Exception {
        String value = attributes.getValue(attribute);
        Flow2 flow = new Flow2();
        flow.setName(value);
        Workflow2.getInstance().setFlow(flow);
    }
}
