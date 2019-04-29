package com.gms.xms.workflow.core2;

import com.gms.xms.common.config.SpringContextLoader;
import org.apache.commons.digester.Rule;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;

/**
 * Posted from TaskRule.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:31:57 PM
 */
class TaskRule2 extends Rule {
    private String classAttr = "className";
    private String refAttr = "ref";
    private String typeAttr = "type";

    public TaskRule2() {
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
        Task2 task;
        if (StringUtils.isNotBlank(value)) {
            Class<?> clazz = digester.getClassLoader().loadClass(value);
            task = (Task2) clazz.newInstance();
            Workflow2.getInstance().addTask(type, task);
        } else {
            value = attributes.getValue(refAttr);
            Workflow2.getInstance().addTask(type, (Task2) SpringContextLoader.getContext().getBean(value));
        }
        Workflow2.getInstance().addFlow();
    }
}
