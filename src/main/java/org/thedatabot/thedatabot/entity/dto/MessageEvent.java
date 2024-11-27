package org.thedatabot.thedatabot.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

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

    // 修改为List类型以接收多个消息对象
    @JsonProperty("message")
    private List<Message> messages;

    @JsonProperty("message_format")
    private String messageFormat;

    @JsonProperty("post_type")
    private String postType;

    @JsonProperty("group_id")
    private long groupId;

    /**
     * 嵌套类：Sender
     */
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

    /**
     * 嵌套类：Message
     */
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

            @JsonProperty("id")
            private String id;

            @JsonProperty("qq")
            private String qq;

            @JsonProperty("file")
            private String file;

            @JsonProperty("url")
            private String url;

            @JsonProperty("file_size")
            private String fileSize;

            @JsonProperty("file_unique")
            private String fileUnique;
        }
    }
}