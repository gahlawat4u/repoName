package com.gms.xms.workflow.core;

import com.gms.xms.common.config.SpringContextLoader;
import org.apache.commons.digester.Rule;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;

/**
 * Posted from TaskRule.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:31:57 PM
 */
class TaskRule extends Rule {
    private String classAttr = "className";
    private String refAttr = "ref";
    private String typeAttr = "type";

    public TaskRule() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.commons.digester.Rule#begin(java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void begin(String namespace, String name, Attributes attributes) throws Exception {
        String value = attributes.getValue(classAttr);
        String type = attributes.getValue(typeAttr);
        if (StringUtils.isBlank(type))
            throw new Exception("attribute type is null in workflow");
        Task task;
        if (StringUtils.isNotBlank(value)) {
            Class<?> clazz = digester.getClassLoader().loadClass(value);
            task = (Task) clazz.newInstance();
            Workflow.getInstance().addTask(type, task);
        } else {
            value = attributes.getValue(refAttr);
            Workflow.getInstance().addTask(type, (Task) SpringContextLoader.getContext().getBean(value));
        }
        Workflow.getInstance().addFlow();
    }
}
