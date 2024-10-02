package org.thedatabot.thedatabot.handler;

import org.thedatabot.thedatabot.entity.dto.MessageEvent;

public interface MessageHandler {
    void handle(MessageEvent message);
    /**
     * 启用或禁用消息处理器。
     * @param enabled 是否启用
     */
    void setEnabled(boolean enabled);

    /**
     * 检查消息处理器是否启用。
     * @return true 如果启用, 否则返回 false
     */
    boolean isEnabled();
}