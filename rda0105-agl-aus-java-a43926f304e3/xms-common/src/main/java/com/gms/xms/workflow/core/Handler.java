package com.gms.xms.workflow.core;

import com.gms.xms.common.context.ContextBase;

/**
 * Posted from Handler.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:29:12 PM
 */
public abstract class Handler implements Task {
    /*
     * (non-Javadoc)
     * 
     * @see com.gms.xms.workflow.core.Task#execute(com.gms.xms.common.context.ContextBase)
     */
    @Override
    public boolean execute(ContextBase context) {
        return true;
    }

    /**
     * Handle object
     *
     * @param context
     * @param exception
     * @return boolean (true: continue, false : exit)
     */
    public abstract boolean handle(ContextBase context, Exception exception);
}
