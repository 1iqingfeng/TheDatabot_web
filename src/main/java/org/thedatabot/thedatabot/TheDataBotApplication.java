package org.thedatabot.thedatabot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication(scanBasePackages = "org.thedatabot.thedatabot")
@EnableAspectJAutoProxy
@MapperScan("org.thedatabot.thedatabot.mapper")
@EnableTransactionManagement
public class TheDataBotApplication implements CommandLineRunner{
    @Autowired
    private ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(TheDataBotApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        // String[] beanNames = applicationContext.getBeanDefinitionNames();
        // for (String beanName : beanNames) {
        //     System.out.println(beanName);
        // }
    }
}
