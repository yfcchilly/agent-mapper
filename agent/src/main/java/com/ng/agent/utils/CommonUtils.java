package com.ng.agent.utils;

import com.ng.agent.bytebuddy.ObjectMethodElementMatchers;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.isStatic;
import static net.bytebuddy.matcher.ElementMatchers.nameContains;
import static net.bytebuddy.matcher.ElementMatchers.nameMatches;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.not;

/**
 * @author guoxing
 * @date 2020/7/30
 * @description 描述
 */
public class CommonUtils {

    public static ElementMatcher<TypeDescription> typeDef(String className) {
        if (isEmpty(className)) {
            return null;
        }
        ElementMatcher.Junction<TypeDescription> classJunction = null;
        classJunction = className.contains("*") ? nameMatches(className) : named(className);
        return classJunction.and(not(nameContains("$auxiliary$")))
                .and(not(nameContains("BySpringCGLIB$")))
                .and(not(isStatic()));
    }

    public static ElementMatcher<MethodDescription> methodDef(List<String> methodNames,
                                                              boolean isIncludePrivateFunction) {
        ElementMatcher.Junction<MethodDescription> basicDef;
        if (isIncludePrivateFunction) {
            basicDef = not(ObjectMethodElementMatchers.INSTANCE);
        } else {
            basicDef = not(ElementMatchers.<MethodDescription>isPrivate().or(ObjectMethodElementMatchers.INSTANCE));
        }

        ElementMatcher.Junction<MethodDescription> methodJunction = null;
        if (isNotEmpty(methodNames)) {
            int idx = 0;
            for (String methodName : methodNames) {
                if (isNotEmpty(methodName)) {
                    if (idx == 0) {
                        methodJunction = methodName.contains("*") ? nameMatches(methodName):named(methodName);
                    } else {
                        methodJunction = methodJunction.or(methodName.contains("*") ? nameMatches(methodName):named(methodName));
                    }
                    idx++;
                }
            }
        }

        return methodJunction == null ? basicDef : basicDef.and(methodJunction);
    }

    public static boolean isEmpty(final Object[] array) {
        return getLength(array) == 0;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }

    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }

    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }
}
