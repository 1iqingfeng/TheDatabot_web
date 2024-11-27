package org.thedatabot.thedatabot.event.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.annotation.EventHandlerType;
import org.thedatabot.thedatabot.common.Enum.Bot.MessageType;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;
import org.thedatabot.thedatabot.event.EventHandler;
import org.thedatabot.thedatabot.handler.MessageHandler;
import org.thedatabot.thedatabot.registry.MessageHandlerRegistry;

@Slf4j
@Component
@EventHandlerType("\"post_type\":\"message\"")
@AllArgsConstructor
public class MessageEventHandler implements EventHandler {

    private final ObjectMapper objectMapper;

    private final MessageHandlerRegistry messageHandlerRegistry;

    public void handle(String message) throws Exception {
        // 解析消息事件
        MessageEvent messageEvent = objectMapper.readValue(message, MessageEvent.class);
        log.debug("Received message event: {}", messageEvent);

        // 获取消息类型
        MessageType messageType = MessageType.fromString(messageEvent.getMessageType());

        // 从注册表中获取对应的处理器
        MessageHandler handler = (MessageHandler) messageHandlerRegistry.getHandler(messageType);
        if (handler != null) {
            // log.debug("Found handler for message type: {}", messageType);
            handler.handle(messageEvent); // 处理消息事件
        } else {
            log.warn("No handler found for message type: {}", messageType);
        }
    }
}
