package com.ng.service.manager;

import org.springframework.stereotype.Component;

/**
 * @author guoxing
 * @date 2020/7/30
 * @description 描述
 */
@Component
public class TestManager {
    public String sayHello(String nick) {
        return "hello:" + nick + "!";
    }
}
