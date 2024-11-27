package org.thedatabot.thedatabot.handler.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.annotation.MessageTypeHandler;
import org.thedatabot.thedatabot.common.Enum.Bot.MessageType;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;
import org.thedatabot.thedatabot.entity.httpinterface.NapCatQQ;
import org.thedatabot.thedatabot.handler.MessageHandler;

@Component
@Slf4j
@MessageTypeHandler(MessageType.PRIVATE)
@AllArgsConstructor
public class PrivateMessageHandler implements MessageHandler {
    private final NapCatQQ napCatQQClient;

    @Override
    public void handle(MessageEvent messageEvent){
    }

    @Override
    public void setEnabled(boolean enabled) {
        // 实现必要的启用/禁用逻辑
    }

    @Override
    public boolean isEnabled() {
        return false;  // 默认返回 false 或根据实际情况修改
    }
}
