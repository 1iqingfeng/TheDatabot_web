package org.thedatabot.thedatabot.event.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.entity.dto.LifecycleEvent;
import org.thedatabot.thedatabot.event.EventHandler;


@Component
@Slf4j
public class LifecycleEventHandler implements EventHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(String message) throws Exception {
        LifecycleEvent lifecycleEvent = objectMapper.readValue(message, LifecycleEvent.class);
        // 处理生命周期事件
        log.debug("处理生命周期事件: " + lifecycleEvent.getSubType());
    }
}
