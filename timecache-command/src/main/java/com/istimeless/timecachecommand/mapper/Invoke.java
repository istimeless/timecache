package com.istimeless.timecachecommand.mapper;

import com.istimeless.timecachecore.operate.Operate;

import java.lang.reflect.Method;

public class Invoke {

    private Operate operate;

    private Method method;

    private Object[] parameters;

    public Operate getOperate() {
        return operate;
    }

    public void setOperate(Operate operate) {
        this.operate = operate;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
