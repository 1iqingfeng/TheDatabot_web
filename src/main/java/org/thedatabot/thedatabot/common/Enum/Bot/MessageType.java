package org.thedatabot.thedatabot.common.Enum.Bot;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    PRIVATE("private", 1, "私聊消息"),
    PRIVATE_FRIEND("private.friend", 2, "私聊消息 - 好友"),
    PRIVATE_GROUP("private.group", 3, "私聊消息 -群聊临时消息"),
    GROUP("group", 101, "群聊消息"),
    GROUP_NORMAL("group.normal", 102, "群聊消息 - 群聊普通消息");

    private final String type;
    private final int code;
    private final String description;
    public static MessageType fromString(String type) {
        for (MessageType messageType : MessageType.values()) {
            // 使用自定义值进行比较
            if (messageType.getType().equals(type)) {
                return messageType;
            }
        }
        throw new IllegalArgumentException("不清楚的消息类型" + type);
    }
}
