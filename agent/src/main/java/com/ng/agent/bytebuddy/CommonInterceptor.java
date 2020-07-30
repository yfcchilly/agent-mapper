package com.ng.agent.bytebuddy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class CommonInterceptor {
    @RuntimeType
    public static Object intercept(@SuperCall Callable<?> superMethod, @Origin Method method,
                                   @AllArguments Object[] args) throws Throwable {
        Object result = superMethod.call();
        System.out.println("enhancer call class=" + method.getDeclaringClass().getName()
                + ", method=" + method.getName());
        return result;
    }
}
