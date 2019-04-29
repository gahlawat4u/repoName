package com.gms.xms.workflow.core;

import com.gms.xms.common.context.ContextBase;

/**
 * Posted from IWorkflow.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:30:37 PM
 *
 * @param <T>
 */
public interface IWorkflow<T extends ContextBase> {
    /**
     * Process workflow
     *
     * @param context - context
     * @return - context result
     * @throws Exception -on error
     */
    ContextBase process(T context) throws Exception;
}
