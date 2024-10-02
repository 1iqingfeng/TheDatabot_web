package org.thedatabot.thedatabot.Handler;

import org.thedatabot.thedatabot.entity.dto.MessageEvent;

public interface MessageHandler {
    void handle(MessageEvent message);
}