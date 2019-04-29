package com.gms.xms.workflow.core2;

import com.gms.xms.common.context.ContextBase2;

/**
 * Posted from Task.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:31:12 PM
 */
public interface Task2 {
    /**
     * execute task
     *
     * @param context - context
     * @return - boolean (true : continue, false : exit)
     * @throws Exception - on error
     */
    boolean execute(ContextBase2 context) throws Exception;
}