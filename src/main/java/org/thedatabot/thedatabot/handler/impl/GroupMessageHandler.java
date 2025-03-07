package org.thedatabot.thedatabot.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.annotation.MessageTypeHandler;
import org.thedatabot.thedatabot.common.enums.Bot.MessageType;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;
import org.thedatabot.thedatabot.handler.MessageHandler;

@Component
@Slf4j
@MessageTypeHandler(MessageType.GROUP)
public class GroupMessageHandler implements MessageHandler {
    private boolean enabled = true;

    public void handle(MessageEvent messageEvent) {
        if (!enabled) {
            return; // 如果处理器被禁用，则不处理消息
        }
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
