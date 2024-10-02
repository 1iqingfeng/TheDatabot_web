package org.thedatabot.thedatabot.event.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;
import org.thedatabot.thedatabot.event.EventHandler;
@Component
@Slf4j
public class MessageEventHandler implements EventHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(String message) throws Exception {
        MessageEvent messageEvent = objectMapper.readValue(message, MessageEvent.class);
        // 处理消息事件
        log.debug("处理消息事件: {}" , messageEvent);
    }
}
