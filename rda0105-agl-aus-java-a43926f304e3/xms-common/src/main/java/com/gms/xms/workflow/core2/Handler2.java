package com.gms.xms.workflow.core2;

import com.gms.xms.common.context.ContextBase2;

/**
 * Posted from Handler.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:29:12 PM
 */
public abstract class Handler2 implements Task2 {
    /*
     * (non-Javadoc)
     * 
     * @see com.gms.xms.workflow.core.Task#execute(com.gms.xms.common.context.ContextBase)
     */
    @Override
    public boolean execute(ContextBase2 context) {
        return true;
    }

    /**
     * Handle object
     *
     * @param context
     * @param exception
     * @return boolean (true: continue, false : exit)
     */
    public abstract boolean handle(ContextBase2 context, Exception exception);
}
