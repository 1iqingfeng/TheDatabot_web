package org.thedatabot.thedatabot.Handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.Handler.MessageHandler;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;

@Component
@Slf4j
public class PrivateFriendMessageHandler implements MessageHandler {
    public void handle(MessageEvent messageEvent) {
        // 处理好友私聊消息的逻辑
        log.debug("处理好友私聊消息: " + messageEvent);
    }
}
