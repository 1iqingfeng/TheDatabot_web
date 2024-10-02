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

    @JsonProperty("message")
    private List<Message> message;

    @JsonProperty("message_format")
    private String messageFormat;

    @JsonProperty("post_type")
    private String postType;

    @JsonProperty("group_id")
    private long groupId;

    @Data // 这个注解会自动生成 getter、setter 等方法
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

    @Data // 这个注解会自动生成 getter、setter 等方法
    public static class Message {
        @JsonProperty("type")
        private String type;

        @JsonProperty("data")
        private Data data;

        @lombok.Data // 这个注解会自动生成 getter、setter 等方法
        public static class Data {
            @JsonProperty("text")
            private String text;

            @JsonProperty("id")
            private String id;

            @JsonProperty("content")
            private List<String> content;
        }
    }
}
