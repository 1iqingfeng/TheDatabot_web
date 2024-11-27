package org.thedatabot.thedatabot.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.annotation.MessageTypeHandler;
import org.thedatabot.thedatabot.common.Enum.Bot.MessageType;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;
import org.thedatabot.thedatabot.entity.httpinterface.NapCatQQ;
import org.thedatabot.thedatabot.handler.MessageHandler;
import org.thedatabot.thedatabot.service.IdCardInfoService;
import org.thedatabot.thedatabot.service.QQInfoService;

@Component
@Slf4j
@MessageTypeHandler(MessageType.PRIVATE)
public class PrivateMessageHandler implements MessageHandler {
    private final IdCardInfoService idCardInfoService;
    private final NapCatQQ napCatQQClient;
    private final QQInfoService qqInfoService;

    public PrivateMessageHandler(IdCardInfoService idCardInfoService, NapCatQQ napCatQQClient,
    QQInfoService QQInfoService
    ) {
        this.idCardInfoService = idCardInfoService;
        this.napCatQQClient = napCatQQClient;
        this.qqInfoService = QQInfoService;
    }

    @Override
    public void handle(MessageEvent messageEvent){
        String text = messageEvent.getMessage().getData().getText();// 处理群聊消息的逻辑
        log.debug("处理私聊消息: " + text);
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
