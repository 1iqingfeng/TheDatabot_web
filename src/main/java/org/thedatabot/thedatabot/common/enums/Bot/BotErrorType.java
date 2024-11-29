package org.thedatabot.thedatabot.common.enums.Bot;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BotErrorType {
    UNKNOWN_MESSAGE_TYPE("不清楚的消息类型"),
    USER_NOT_FOUND("用户未找到"),
    GROUP_NOT_FOUND("群组未找到"),
    MESSAGE_SEND_FAILED("消息发送失败"),
    AUTHENTICATION_FAILED("身份验证失败");
    private final String message;
}
