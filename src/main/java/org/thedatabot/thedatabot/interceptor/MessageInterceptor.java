package org.thedatabot.thedatabot.interceptor;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.event.EventHandler;
import org.thedatabot.thedatabot.event.Impl.HeartbeatEventHandler;
import org.thedatabot.thedatabot.event.Impl.LifecycleEventHandler;
import org.thedatabot.thedatabot.event.Impl.MessageEventHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MessageInterceptor {
    private final List<EventHandler> eventHandlers = new ArrayList<>();
    @Autowired
    private LifecycleEventHandler lifecycleEventHandler;

    @Autowired
    private HeartbeatEventHandler heartbeatEventHandler;

    @Autowired
    private MessageEventHandler messageEventHandler;
    @PostConstruct
    public void init() {
        eventHandlers.add(lifecycleEventHandler);
        eventHandlers.add(heartbeatEventHandler);
        eventHandlers.add(messageEventHandler);
    }

    public void intercept(String message) throws Exception {
        for (EventHandler handler : eventHandlers) {
            // 判断消息类型并调用相应的处理器
            if (message.contains("\"meta_event_type\":\"lifecycle\"") && handler instanceof LifecycleEventHandler) {
                handler.handle(message);
                return;
            } else if (message.contains("\"meta_event_type\":\"heartbeat\"") && handler instanceof HeartbeatEventHandler) {
                handler.handle(message);
                return;
            } else if (message.contains("\"post_type\":\"message\"") && handler instanceof MessageEventHandler) {
                handler.handle(message);
                return;
            }
        }
        log.warn("未找到合适的消息类型{}", message);
    }
}
