package com.ng.agent.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.JavaModule;

/**
 * @author guoxing
 * @date 2020/4/24
 * @description 描述
 */
public abstract class AbstractTransformer implements AgentBuilder.Transformer {

    private final Class interceptor;

    protected AbstractTransformer(Class interceptor) {
        this.interceptor = interceptor;
    }

    protected abstract ImplementationDefinition builderTransform(DynamicType.Builder<?> builder,
                                                                 ClassFileLocator.Compound compound);

    @Override
    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                            TypeDescription typeDescription,
                                            ClassLoader classLoader,
                                            JavaModule module) {

        ClassFileLocator.Compound compound = new ClassFileLocator.Compound(
                ClassFileLocator.ForClassLoader.of(classLoader),
                ClassFileLocator.ForClassLoader.ofSystemLoader());

        return builderTransform(builder, compound).intercept(
                MethodDelegation.to(TypePool.Default.of(compound).describe(interceptor.getName()).resolve()));
    }
}
