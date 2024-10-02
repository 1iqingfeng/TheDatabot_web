package org.thedatabot.thedatabot.registry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.common.Enum.Bot.MessageType;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
@Component
public class MessageHandlerRegistry {
    // 使用 EnumMap 来存储基于 MessageType 的处理器映射关系
    private final Map<MessageType, Object> handlerMap = new EnumMap<>(MessageType.class);

    // 根据 MessageType 获取处理器
    public Object getHandler(MessageType messageType) {
        return handlerMap.get(messageType);
    }

    // 根据 MessageType 注册处理器
    public void registerHandler(MessageType messageType, Object handler) {
        handlerMap.put(messageType, handler);
        log.info("Registered handler for message type: {}", messageType);
    }

    // 列出所有注册的处理器（仅作调试或检查用途）
    public void listAllHandlers() {
        handlerMap.forEach((messageType, handler) ->
                log.info("Handler for {} -> {}", messageType, handler.getClass().getName()));
    }
}
