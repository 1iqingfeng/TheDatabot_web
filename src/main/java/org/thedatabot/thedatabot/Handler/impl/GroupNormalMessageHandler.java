package org.thedatabot.thedatabot.Handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.Handler.MessageHandler;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;

@Component
@Slf4j
public class GroupNormalMessageHandler implements MessageHandler {
    public void handle(MessageEvent messageEvent) {
        // 处理普通群聊消息的逻辑
        log.debug("处理普通群聊消息: " + messageEvent);
    }
}
