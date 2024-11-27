package org.thedatabot.thedatabot.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.thedatabot.thedatabot.config.SingleMessageDeserializer;

@Data
public class MessageEvent {

    @JsonProperty("self_id")
    private long selfId;

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("time")
    private long time;

    @JsonProperty("message_id")
    private long messageId;

    @JsonProperty("message_seq")
    private long messageSeq;

    @JsonProperty("real_id")
    private long realId;

    @JsonProperty("message_type")
    private String messageType;

    @JsonProperty("sender")
    private Sender sender;

    @JsonProperty("raw_message")
    private String rawMessage;

    @JsonProperty("font")
    private int font;

    @JsonProperty("sub_type")
    private String subType;

    // 将 message 字段解析为单个对象（数组中的第一个元素）
    @JsonProperty("message")
    @JsonDeserialize(using = SingleMessageDeserializer.class)
    private Message message;

    @JsonProperty("message_format")
    private String messageFormat;

    @JsonProperty("post_type")
    private String postType;

    @JsonProperty("group_id")
    private long groupId;

    @Data
    public static class Sender {
        @JsonProperty("user_id")
        private long userId;

        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("card")
        private String card;

        @JsonProperty("role")
        private String role;
    }

    @Data
    public static class Message {
        @JsonProperty("type")
        private String type;

        @JsonProperty("data")
        private Data data;

        public String getText() {
            return data != null ? data.getText() : null;
        }

        @lombok.Data
        public static class Data {
            @JsonProperty("text")
            private String text;
        }
    }
}
