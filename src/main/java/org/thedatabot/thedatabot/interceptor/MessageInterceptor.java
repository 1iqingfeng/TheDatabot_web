package org.thedatabot.thedatabot.interceptor;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.event.EventHandler;
import org.thedatabot.thedatabot.registry.MessageHandlerRegistry;
import org.thedatabot.thedatabot.scanner.EventHandlerScanner;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MessageInterceptor {
    private Map<String, EventHandler> eventHandlerMap = new HashMap<>();


    @Autowired
    private EventHandlerScanner eventHandlerScanner;
    private MessageHandlerRegistry messageHandlerRegistry;
    @PostConstruct
    public void init() {
        try {
            // 扫描指定包并注册事件处理器
            eventHandlerScanner.scanAndRegisterHandlers("org.thedatabot.thedatabot.event.Impl",messageHandlerRegistry);
            this.eventHandlerMap = eventHandlerScanner.getEventHandlerMap();
            if (this.eventHandlerMap == null || this.eventHandlerMap.isEmpty()) {
                log.warn("没有找到任何事件处理器");
            }
        } catch (Exception e) {
            log.error("初始化 MessageInterceptor 时发生错误: ", e);
            throw e; // 重新抛出异常，确保 Spring 能捕获
        }
    }
    public void intercept(String message) throws Exception {
        for (Map.Entry<String, EventHandler> entry : eventHandlerMap.entrySet()) {
            String metaEventType = entry.getKey();
            EventHandler handler = entry.getValue();
            if (message.contains(metaEventType)) {
                handler.handle(message);
                return;
            }
        }
        log.warn("未找到合适的消息类型{}", message);
    }
}
