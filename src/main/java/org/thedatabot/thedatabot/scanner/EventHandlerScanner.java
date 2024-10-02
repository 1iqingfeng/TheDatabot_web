package org.thedatabot.thedatabot.scanner;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.annotation.EventHandlerType;
import org.thedatabot.thedatabot.annotation.MessageTypeHandler;
import org.thedatabot.thedatabot.common.Enum.Bot.MessageType;
import org.thedatabot.thedatabot.event.EventHandler;
import org.thedatabot.thedatabot.handler.MessageHandler;
import org.thedatabot.thedatabot.registry.MessageHandlerRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
@Getter
public class EventHandlerScanner {
    private final Map<String, EventHandler> eventHandlerMap = new HashMap<>();
    private final Map<MessageType, MessageHandler> messageHandlerMap = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    // 扫描并注册事件处理器和消息处理器
    public void scanAndRegisterHandlers(String basePackage, MessageHandlerRegistry messageHandlerRegistry) {
        if (applicationContext == null) {
            log.error("ApplicationContext is null");
            return; // 避免后续代码执行
        }

        Reflections reflections = new Reflections(basePackage);

        // 1. 扫描带有 @EventHandlerType 注解的处理器
        Set<Class<?>> eventHandlerClasses = reflections.getTypesAnnotatedWith(EventHandlerType.class);
        for (Class<?> handlerClass : eventHandlerClasses) {
            if (EventHandler.class.isAssignableFrom(handlerClass)) {
                try {
                    EventHandler handler = (EventHandler) applicationContext.getBean(handlerClass);
                    EventHandlerType annotation = handlerClass.getAnnotation(EventHandlerType.class);
                    eventHandlerMap.put(annotation.value(), handler);
                    log.info("Registered EventHandler: {} -> {}", annotation.value(), handler.getClass().getName());
                } catch (Exception e) {
                    log.error("Error creating instance of EventHandler: {}", handlerClass.getName(), e);
                }
            }
        }

        // 2. 扫描带有 @MessageTypeHandler 注解的处理器
        Set<Class<?>> messageHandlerClasses = reflections.getTypesAnnotatedWith(MessageTypeHandler.class);
        for (Class<?> handlerClass : messageHandlerClasses) {
            if (MessageHandler.class.isAssignableFrom(handlerClass)) {
                try {
                    // 实例化消息处理器
                    MessageHandler handler = (MessageHandler) applicationContext.getBean(handlerClass);
                    MessageTypeHandler annotation = handlerClass.getAnnotation(MessageTypeHandler.class);
                    MessageType messageType = annotation.value();

                    // 注册到内部 Map 和 MessageHandlerRegistry
                    messageHandlerMap.put(messageType, handler);
                    messageHandlerRegistry.registerHandler(messageType, handler);
                    log.info("Registered MessageTypeHandler: {} -> {}", messageType, handler.getClass().getName());
                } catch (Exception e) {
                    log.error("Error creating instance of MessageHandler: {}", handlerClass.getName(), e);
                }
            }
        }

        // 打印注册结果，便于检查
        log.info("EventHandlerScanner registered EventHandlers: {}", eventHandlerMap.keySet());
        log.info("EventHandlerScanner registered MessageTypeHandlers: {}", messageHandlerMap.keySet());
    }
}
