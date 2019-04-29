package com.gms.xms.common.executor;

import com.gms.xms.common.context.ContextBase;

public interface IExecutable {
    public ContextBase process(ContextBase context);
}
