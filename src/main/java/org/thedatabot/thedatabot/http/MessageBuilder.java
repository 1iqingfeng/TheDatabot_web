package org.thedatabot.thedatabot.http;

import org.thedatabot.thedatabot.entity.request.SendPrivateMessageRequest;
import java.util.ArrayList;
import java.util.List;

public class MessageBuilder {

    /**
     * 创建带有单条消息内容的消息列表
     *
     * @param content 消息内容
     * @return 消息列表
     */
    public static List<SendPrivateMessageRequest.Message> buildTextMessageList(String content) {
        List<SendPrivateMessageRequest.Message> messages = new ArrayList<>();
        SendPrivateMessageRequest.Message.Data data = new SendPrivateMessageRequest.Message.Data(content);
        SendPrivateMessageRequest.Message message = new SendPrivateMessageRequest.Message(data, "text");
        messages.add(message);
        return messages;
    }

    /**
     * 构建请求对象并设置消息列表
     *
     * @param userId  用户 ID
     * @param content 消息内容
     * @return 构建后的请求对象
     */
    public static SendPrivateMessageRequest buildRequest(Long userId, String content) {
        SendPrivateMessageRequest request = new SendPrivateMessageRequest();
        request.setUser_id(userId);
        request.setMessage(buildTextMessageList(content));
        return request;
    }
}
