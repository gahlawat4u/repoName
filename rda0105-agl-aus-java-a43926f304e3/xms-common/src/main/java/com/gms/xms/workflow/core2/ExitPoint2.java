package com.gms.xms.workflow.core2;

import com.gms.xms.common.context.ContextBase2;

/**
 * Posted from ExitPoint.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:25:38 PM
 */
public abstract class ExitPoint2 implements Task2 {
    @Override
    public boolean execute(ContextBase2 context2) throws Exception {
        return true;
    }

    public abstract boolean process(ContextBase2 context2) throws Exception;
}
