package org.thedatabot.thedatabot.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
public class SendPrivateMessageRequest {
    private long user_id;  // 用户 ID
    private List<Message> message; // 消息内容列表

    @Data
    @AllArgsConstructor
    public static class Message {
        private Data data; // 数据内容
        private String type; // 消息类型

        @lombok.Data
        @AllArgsConstructor
        public static class Data {
            private String text; // 消息文本
        }
    }
}
