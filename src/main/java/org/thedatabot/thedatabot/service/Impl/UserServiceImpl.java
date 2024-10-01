package org.thedatabot.thedatabot.service.Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thedatabot.thedatabot.mapper.UserMapper;
import org.thedatabot.thedatabot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
}
