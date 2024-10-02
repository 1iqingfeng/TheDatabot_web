package org.thedatabot.thedatabot.config;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thedatabot.thedatabot.annotation.MessageTypeHandler;
import org.thedatabot.thedatabot.common.Enum.Bot.MessageType;
import org.thedatabot.thedatabot.registry.MessageHandlerRegistry;
import org.thedatabot.thedatabot.scanner.EventHandlerScanner;

import java.util.Set;

@Configuration
@Slf4j
public class ApplicationConfig {

    @Autowired
    private EventHandlerScanner eventHandlerScanner;

    @Bean
    public MessageHandlerRegistry messageHandlerRegistry() {
        MessageHandlerRegistry registry = new MessageHandlerRegistry();
        // 扫描并注册消息处理器
        registerMessageHandlers(registry);
        return registry;
    }

    private void registerMessageHandlers(MessageHandlerRegistry registry) {
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
    }

}
