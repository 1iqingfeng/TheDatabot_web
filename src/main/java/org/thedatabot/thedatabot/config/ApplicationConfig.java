package org.thedatabot.thedatabot.config;

import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thedatabot.thedatabot.annotation.MessageTypeHandler;
import org.thedatabot.thedatabot.common.Enum.Bot.MessageType;
import org.thedatabot.thedatabot.registry.MessageHandlerRegistry;

import java.util.Set;

@Configuration
public class ApplicationConfig {

    @Bean
    public MessageHandlerRegistry messageHandlerRegistry() {
        MessageHandlerRegistry registry = new MessageHandlerRegistry();

        // 扫描指定包中的所有带有 @MessageTypeHandler 的类
        Reflections reflections = new Reflections("org.thedatabot.thedatabot.handler.impl");
        Set<Class<?>> handlerClasses = reflections.getTypesAnnotatedWith(MessageTypeHandler.class);

        // 遍历扫描到的处理器类，并根据注解中的 MessageType 进行注册
        for (Class<?> handlerClass : handlerClasses) {
            MessageTypeHandler annotation = handlerClass.getAnnotation(MessageTypeHandler.class);
            MessageType messageType = annotation.value(); // 获取注解中的消息类型

            try {
                // 实例化处理器对象并注册到 MessageHandlerRegistry 中
                Object handlerInstance = handlerClass.getDeclaredConstructor().newInstance();
                registry.registerHandler(messageType, handlerInstance);
            } catch (Exception e) {
                // 如果处理器类实例化失败，则打印日志
                System.err.println("Failed to register handler: " + handlerClass.getName());
                e.printStackTrace();
            }
        }

        return registry;
    }
}
