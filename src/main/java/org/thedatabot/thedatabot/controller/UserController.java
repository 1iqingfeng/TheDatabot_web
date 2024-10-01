package org.thedatabot.thedatabot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thedatabot.thedatabot.common.Result.Result;
import org.thedatabot.thedatabot.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping
    public Result<String> getAllUsers() {
        return Result.success("success");
    }

}
