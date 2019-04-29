package test.com.gms.xms;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBase {
    private AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"persistence-context.xml", "workflow-context.xml"});

    public AbstractApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
