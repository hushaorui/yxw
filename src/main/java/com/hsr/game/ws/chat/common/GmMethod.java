package com.hsr.game.ws.chat.common;

import java.lang.reflect.Method;

public class GmMethod {
    private GmExecutor executor;
    private Method method;

    public GmMethod() {}

    public GmMethod(GmExecutor executor, Method method) {
        this.executor = executor;
        this.method = method;
    }
    public GmExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(GmExecutor executor) {
        this.executor = executor;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
