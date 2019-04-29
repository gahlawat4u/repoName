package com.gms.xms.workflow.core2;

import com.gms.xms.common.context.ContextBase2;

/**
 * Posted from IWorkflow.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:30:37 PM
 *
 * @param <T>
 */
public interface IWorkflow2<T extends ContextBase2> {
    /**
     * Process workflow
     *
     * @param context - context
     * @return - context result
     * @throws Exception -on error
     */
    ContextBase2 process(T context) throws Exception;
}
