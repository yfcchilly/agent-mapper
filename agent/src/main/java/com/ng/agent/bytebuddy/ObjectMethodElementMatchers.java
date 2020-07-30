package com.ng.agent.bytebuddy;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

/**
 * @author guoxing
 * @date 2020/4/28
 * @description 描述
 */
public class ObjectMethodElementMatchers implements ElementMatcher<MethodDescription> {

    public final static ObjectMethodElementMatchers INSTANCE = new ObjectMethodElementMatchers();

    private final Junction<MethodDescription> matcher;

    private ObjectMethodElementMatchers() {
        matcher = named("getClass").and(takesArguments(0))
                .or(named("equals").and(takesArguments(Object.class)))
                .or(named("hashCode").and(takesArguments(0)))
                .or(named("wait").and(takesArguments(0)))
                .or(named("wait").and(takesArguments(long.class)))
                .or(named("wait").and(takesArguments(long.class, int.class)))
                .or(named("toString").and(takesArguments(0)))
                .or(named("clone").and(takesArguments(0)))
                .or(named("notify").and(takesArguments(0)))
                .or(named("notifyAll").and(takesArguments(0)))
                .or(named("finalize").and(takesArguments(0)));
    }
    @Override
    public boolean matches(MethodDescription target) {
        return matcher.matches(target);
    }
}