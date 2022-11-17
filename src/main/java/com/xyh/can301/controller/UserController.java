package com.xyh.can301.controller;

import com.alibaba.fastjson.JSON;
import com.xyh.can301.entity.Result;
import com.xyh.can301.entity.User;
import com.xyh.can301.service.Impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public String register(@RequestBody String userInfo){
        User u = JSON.parseObject(userInfo, User.class);
        log.info("user to be added: " + u.getUname());
        if(userService.getUid(u.getUname()) != -1){
            return JSON.toJSONString(Result.create(300, "username already exists, change another name"));
        }
        int success = userService.saveUser(u);
        if (success == 1) {
            return JSON.toJSONString(Result.create(200, "add user successfully", u));
        } else {
            return JSON.toJSONString(Result.create(400, "failed to add user "));
        }

    }

    @PostMapping (value = "/login")
    public String login(@RequestBody String json) {
        User u = JSON.parseObject(json, User.class);
        String username = u.getUname();
        String password = u.getUpassword();
        log.info("user logging in: " + username);

        int userid = (int)userService.getUid(username);
        if(userid == -1){
            Result result = Result.create(300, "user do not exist");
            log.info("login failed: " + username);
            return JSON.toJSONString(result);
        }
        if (!Objects.equals(userService.getUserPassword(userid), "null") && userService.getUserPassword(userid).equals(password)) {
            log.info("login successful");
            Result result = Result.success("get user successful", userid);
            return JSON.toJSONString(result);
        }else {
            Result result = Result.create(300, "wrong userid or password ");
            return JSON.toJSONString(result);
        }

    }
}
