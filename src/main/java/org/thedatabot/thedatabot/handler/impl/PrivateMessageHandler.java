package org.thedatabot.thedatabot.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.annotation.MessageTypeHandler;
import org.thedatabot.thedatabot.common.Enum.Bot.MessageType;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;
import org.thedatabot.thedatabot.entity.pojo.IdCardInfo;
import org.thedatabot.thedatabot.handler.MessageHandler;
import org.thedatabot.thedatabot.service.IdCardInfoService;

import java.util.List;

@Component
@Slf4j
@MessageTypeHandler(MessageType.PRIVATE)
public class PrivateMessageHandler implements MessageHandler {
    @Autowired

    private  IdCardInfoService idCardInfoService;


    @Override
    public void handle(MessageEvent messageEvent) {
        // 处理私聊消息的逻辑
        List<IdCardInfo> idPrefix6AndName = idCardInfoService.findIdPrefix6AndNameBYShanghai10(messageEvent.getRawMessage());

        log.debug("收到私聊消息: " + messageEvent.getRawMessage());
        // log.debug("私聊消息发送者id: " + messageEvent.getUserId());
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

