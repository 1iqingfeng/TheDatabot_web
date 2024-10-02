package org.thedatabot.thedatabot.event.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.Handler.MessageHandler;
import org.thedatabot.thedatabot.Handler.impl.*;
import org.thedatabot.thedatabot.common.Enum.Bot.MessageType;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;
import org.thedatabot.thedatabot.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MessageEventHandler implements EventHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PrivateMessageHandler privateMessageHandler;
    @Autowired
    private PrivateFriendMessageHandler privateFriendMessageHandler;
    @Autowired
    private PrivateGroupMessageHandler privateGroupMessageHandler;
    @Autowired
    private GroupMessageHandler groupMessageHandler;
    @Autowired
    private GroupNormalMessageHandler groupNormalMessageHandler;
    private final List<MessageHandler> messageEventHandlers = new ArrayList<>();
    @PostConstruct
    public void init() {
        messageEventHandlers.add(privateMessageHandler);
        messageEventHandlers.add(privateFriendMessageHandler);
        messageEventHandlers.add(privateGroupMessageHandler);
        messageEventHandlers.add(groupMessageHandler);
        messageEventHandlers.add(groupNormalMessageHandler);
    }
    @Override
    public void handle(String message) throws Exception {

        MessageEvent messageEvent = objectMapper.readValue(message, MessageEvent.class);
        // 处理消息事件
        log.debug("处理消息事件: {}" , messageEvent.getMessageType());
        try {
            MessageType messageType = MessageType.fromString(messageEvent.getMessageType());
            for (MessageHandler handler : messageEventHandlers) {
                if (messageType == MessageType.PRIVATE) {
                    // 处理私聊消息
                    handler.handle(messageEvent);
                    return;
                } else if (messageType == MessageType.PRIVATE_FRIEND) {
                    // 处理好友私聊消息
                    handler.handle(messageEvent);
                    return;
                } else if (messageType == MessageType.PRIVATE_GROUP) {
                    // 处理群临时私聊消息
                    handler.handle(messageEvent);
                    return;
                } else if (messageType == MessageType.GROUP) {
                    // 处理群聊消息
                    handler.handle(messageEvent);
                    return;
                } else if (messageType == MessageType.GROUP_NORMAL) {
                    // 处理普通群聊消息
                    handler.handle(messageEvent);
                    return;
                } else {
                    log.warn("未处理的消息类型: {}", messageType);
                }
            }

        } catch (Exception e) {
            log.error("消息处理失败", e);
            throw new RuntimeException(e);
        }
    }
}
