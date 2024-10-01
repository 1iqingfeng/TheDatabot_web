package org.thedatabot.thedatabot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication()
@EnableAspectJAutoProxy
@MapperScan("org.thedatabot.thedatabot.mapper")  // 替换为你的 Mapper 包路径
@EnableTransactionManagement
public class TheDataBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(TheDataBotApplication.class, args);
    }

}
