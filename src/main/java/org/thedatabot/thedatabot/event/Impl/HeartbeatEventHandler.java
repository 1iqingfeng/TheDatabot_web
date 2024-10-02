package org.thedatabot.thedatabot.event.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.entity.dto.HeartbeatEvent;
import org.thedatabot.thedatabot.event.EventHandler;

@Component
@Slf4j
public class HeartbeatEventHandler implements EventHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(String message) throws Exception {
        HeartbeatEvent heartbeatEvent = objectMapper.readValue(message, HeartbeatEvent.class);
        // 处理心跳事件
        log.debug("处理心跳事件: " + heartbeatEvent.getStatus().isOnline());
    }
}
