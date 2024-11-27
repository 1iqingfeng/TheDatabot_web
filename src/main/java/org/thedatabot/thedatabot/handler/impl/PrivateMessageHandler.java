package org.thedatabot.thedatabot.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.annotation.MessageTypeHandler;
import org.thedatabot.thedatabot.common.Enum.Bot.MessageType;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;
import org.thedatabot.thedatabot.entity.httpinterface.NapCatQQ;
import org.thedatabot.thedatabot.entity.request.SendPrivateMessageRequest;
import org.thedatabot.thedatabot.handler.MessageHandler;
import org.thedatabot.thedatabot.http.EmptyCallback;
import org.thedatabot.thedatabot.http.MessageBuilder;
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
        String rawMessage = messageEvent.getRawMessage().toLowerCase().trim();
        String responseMessage;
        if (rawMessage.startsWith("sfz")) {
            String searchKey = rawMessage.substring(3).trim();
            log.debug("收到身份证查询请求，搜索关键字: {}", searchKey.trim());
            responseMessage = idCardInfoService.findIdPrefix6AndNameBYShanghai10(searchKey);
            SendPrivateMessageRequest request = MessageBuilder.buildRequest(messageEvent.getUserId(), responseMessage);
            napCatQQClient.sendPrivateMessage(request).enqueue(new EmptyCallback<>());
        }else if (rawMessage.startsWith("qq")){
            // 异步考虑直接内部发送不返回了
            String searchKey = rawMessage.substring(2).trim();
            qqInfoService.QQInfoBySKGResponse(searchKey,messageEvent);
        }
        else {
            return;  // 非匹配请求，直接返回
        }

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
