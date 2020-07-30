package com.ng.agent;

import com.ng.agent.bytebuddy.CommonInterceptor;
import com.ng.agent.bytebuddy.CustomTransformer;
import com.ng.agent.listener.DebugListener;
import com.ng.agent.utils.CommonUtils;
import com.ng.agent.utils.WelcomeBoot;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;

/**
 * @author guoxing
 * @date 2020/7/30
 * @description 描述
 */
public class AgentBoot {

    public static void premain(String agentArgs, Instrumentation inst) {
        // boot slogan
        WelcomeBoot.slogan();

        AgentBuilder agentBuilder = new AgentBuilder.Default()
                .with(DebugListener.getInstance())
                .ignore(ElementMatchers.nameStartsWith("net.bytebuddy"));

        // it work well
        agentBuilder = agentBuilder.type(CommonUtils.typeDef("com.ng.service.manager.TestManager"))
                .transform(new CustomTransformer(CommonInterceptor.class,
                        CommonUtils.methodDef(Arrays.asList("sayHello"), false)));

        // this will throw NoSuchMethodException
        agentBuilder = agentBuilder.type(CommonUtils.typeDef("com.ng.service.mapper.UserMapper"))
                .transform(new CustomTransformer(CommonInterceptor.class,
                        CommonUtils.methodDef(Arrays.asList("queryUser"), false)));

        agentBuilder.installOn(inst);
    }
}
