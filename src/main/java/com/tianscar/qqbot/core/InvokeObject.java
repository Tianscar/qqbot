package com.tianscar.qqbot.core;

import com.tianscar.qqbot.listener.EventHandler;

import java.lang.reflect.Method;

public class InvokeObject {

    public final Object object;
    private final Method method;
    private final int priority;
    private final boolean ignoreCancelled;

    public InvokeObject(Object object, Method method) {
        this.object = object;
        this.method = method;
        EventHandler eventHandler = method.getAnnotation(EventHandler.class);
        priority = eventHandler.priority();
        ignoreCancelled = eventHandler.ignoreCancelled();
    }

    public void invoke(Object... params) {
        try {
            method.setAccessible(true);
            method.invoke(object, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isIgnoreCancelled() {
        return ignoreCancelled;
    }

}
