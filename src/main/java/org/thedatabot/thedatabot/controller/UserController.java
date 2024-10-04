package org.thedatabot.thedatabot.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thedatabot.thedatabot.common.Result.Result;
import org.thedatabot.thedatabot.entity.pojo.User;
import org.thedatabot.thedatabot.mapper.UserMapper;
import org.thedatabot.thedatabot.service.IdCardInfoService;
import org.thedatabot.thedatabot.service.UserService;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IdCardInfoService idCardInfoService;
    @GetMapping
    @DS("master")
    public Result<String> setUser() {
        return Result.success("success");
    }
    @GetMapping("/test1")
    public Result<String> setUser1() {
        User test1 = User.builder().name("test1").build();
        userMapper.insert(test1);
        return Result.success("success");
    }
    @GetMapping("/test2")
    @DS("dfdfdf")
    public Result<String> setUser2() {
        User test2 = User.builder().name("test2").build();
        userMapper.insert(test2);
        return Result.success("success");
    }

}
