package org.thedatabot.thedatabot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication()
@EnableAspectJAutoProxy
public class TheDataBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(TheDataBotApplication.class, args);
    }

}
