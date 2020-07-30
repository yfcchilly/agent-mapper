package com.ng.service.controller;

import com.ng.service.entity.User;
import com.ng.service.manager.TestManager;
import com.ng.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guoxing
 * @date 2020/7/30
 * @description 描述
 */
@RestController
public class DefaultController {

    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TestManager testManager;

    @GetMapping(path = "/user/query", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public User queryUserById(@RequestParam(name = "id") long userId) {
        logger.info(testManager.sayHello("jack"));
        return userMapper.queryUser(userId);
    }
}
