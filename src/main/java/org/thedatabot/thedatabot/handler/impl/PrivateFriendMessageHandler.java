package org.thedatabot.thedatabot.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.handler.MessageHandler;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;

@Component
@Slf4j
public class PrivateFriendMessageHandler implements MessageHandler {
    public void handle(MessageEvent messageEvent) {
        // 处理好友私聊消息的逻辑
        log.debug("处理好友私聊消息: " + messageEvent);
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
