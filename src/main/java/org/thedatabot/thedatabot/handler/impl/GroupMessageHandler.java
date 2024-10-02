package org.thedatabot.thedatabot.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.handler.MessageHandler;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;

@Component
@Slf4j

public class GroupMessageHandler implements MessageHandler {
    private boolean enabled = true;

    public void handle(MessageEvent messageEvent) {
        if (!enabled) {
            return; // 如果处理器被禁用，则不处理消息
        }
        // 处理群聊消息的逻辑
        log.debug("处理群聊消息: " + messageEvent);
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
