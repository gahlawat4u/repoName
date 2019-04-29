package com.gms.xms.common.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Posted from SpringContextLoader.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:49:02 PM
 */
public class SpringContextLoader implements ApplicationContextAware {
    private static ApplicationContext ctx = null;

    public static ApplicationContext getContext() {
        return ctx;
    }

    @SuppressWarnings("static-access")
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

}