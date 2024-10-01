package org.thedatabot.thedatabot;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thedatabot.thedatabot.entity.pojo.User;
import org.thedatabot.thedatabot.mapper.UserMapper;

@SpringBootTest
class TheDataBotApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    @DS("master")
    void userMap() {

        User nihao = User.builder().name("nihao ").build();
        userMapper.insert(nihao);
    }
    @Test
    void contextLoads() {
    }

}
