package com.pcz.thinking.in.spring.bean.scope.custom;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalScope implements Scope {
    /**
     * scope名
     */
    public static final String SCOPE_NAME = "thread_local";

    /**
     * scope线程上下文
     */
    private static final NamedThreadLocal<Map<String, Object>> SCOPE_CONTEXT =
            new NamedThreadLocal<Map<String, Object>>(SCOPE_NAME) {
                @Override
                protected Map<String, Object> initialValue() {
                    return new HashMap<>();
                }
            };

    private Map<String, Object> getContext() {
        return SCOPE_CONTEXT.get();
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = getContext();

        Object value = context.get(name);
        if (value == null) {
            value = objectFactory.getObject();
            context.put(name, value);
        }

        return value;
    }

    @Override
    public Object remove(String name) {
        return getContext().remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }

    @Override
    public Object resolveContextualObject(String key) {
        return getContext();
    }

    @Override
    public String getConversationId() {
        return String.valueOf(Thread.currentThread().getId());
    }
}
