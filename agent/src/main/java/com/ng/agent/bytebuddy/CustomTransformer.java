package com.ng.agent.bytebuddy;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.not;

/**
 * @author guoxing
 * @date 2020/4/24
 * @description 描述
 */
public class CustomTransformer extends AbstractTransformer {

    private ElementMatcher<MethodDescription> methodMatcher;

    public CustomTransformer(Class customInterceptor,
                             ElementMatcher<MethodDescription> methodMatcher) {
        super(customInterceptor);
        this.methodMatcher = methodMatcher;
    }

    public CustomTransformer(Class customInterceptor) {
        super(customInterceptor);
    }

    @Override
    protected DynamicType.Builder.MethodDefinition.ImplementationDefinition builderTransform(
            DynamicType.Builder<?> builder, ClassFileLocator.Compound compound) {
        ElementMatcher.Junction<MethodDescription> elementMatcher = not(ElementMatchers.<MethodDescription>isPrivate()
                .or(ObjectMethodElementMatchers.INSTANCE));
        return builder.method(methodMatcher != null ? elementMatcher.and(methodMatcher) : elementMatcher);
    }
}
