package org.thedatabot.thedatabot.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.annotation.MessageTypeHandler;
import org.thedatabot.thedatabot.common.enums.Bot.MessageType;
import org.thedatabot.thedatabot.handler.MessageHandler;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;

@Component
@Slf4j
@MessageTypeHandler(MessageType.GROUP_NORMAL)
public class GroupNormalMessageHandler implements MessageHandler {
    public void handle(MessageEvent messageEvent) {
        // 处理普通群聊消息的逻辑
        log.debug("处理普通群聊消息: " + messageEvent);
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
