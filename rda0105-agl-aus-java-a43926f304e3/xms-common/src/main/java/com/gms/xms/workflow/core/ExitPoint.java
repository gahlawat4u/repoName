package com.gms.xms.workflow.core;

import com.gms.xms.common.context.ContextBase;

/**
 * Posted from ExitPoint.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:25:38 PM
 */
public abstract class ExitPoint implements Task {
    @Override
    public boolean execute(ContextBase context) throws Exception {
        return true;
    }

    public abstract boolean process(ContextBase context) throws Exception;
}
