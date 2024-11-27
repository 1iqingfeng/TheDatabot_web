package org.thedatabot.thedatabot.service;

import org.thedatabot.thedatabot.entity.dto.MessageEvent;

public interface QQInfoService {
    void QQInfoBySKGResponse(String searchKey, MessageEvent messageEvent);
}
