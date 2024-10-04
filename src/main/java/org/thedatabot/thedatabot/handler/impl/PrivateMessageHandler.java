package org.thedatabot.thedatabot.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.annotation.MessageTypeHandler;
import org.thedatabot.thedatabot.common.Enum.Bot.MessageType;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;
import org.thedatabot.thedatabot.entity.httpinterface.NapCatQQ;
import org.thedatabot.thedatabot.entity.pojo.IdCardInfo;
import org.thedatabot.thedatabot.entity.request.SendPrivateMessageRequest;
import org.thedatabot.thedatabot.entity.response.NapCatQQBaseResponse;
import org.thedatabot.thedatabot.handler.MessageHandler;
import org.thedatabot.thedatabot.service.IdCardInfoService;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@MessageTypeHandler(MessageType.PRIVATE)
public class PrivateMessageHandler implements MessageHandler {
    private final IdCardInfoService idCardInfoService;
    private final NapCatQQ napCatQQClient;

    public PrivateMessageHandler(IdCardInfoService idCardInfoService, NapCatQQ napCatQQClient) {
        this.idCardInfoService = idCardInfoService;
        this.napCatQQClient = napCatQQClient;
    }

    @Override
    public void handle(MessageEvent messageEvent) {
        List<SendPrivateMessageRequest.Message> messages = new ArrayList<>();
        SendPrivateMessageRequest request = new SendPrivateMessageRequest();
        String rawMessage = messageEvent.getRawMessage();

        // 设置用户 ID
        request.setUser_id(messageEvent.getUserId());
        SendPrivateMessageRequest.Message.Data data = new SendPrivateMessageRequest.Message.Data("");

        // 判断是否为身份证查询请求
        if (rawMessage.startsWith("sfz")) {
            String searchKey = rawMessage.substring(3).trim();  // 去除前缀 "sfz" 并去除首尾空格
            log.debug("收到身份证查询请求，搜索关键字: {}", searchKey);

            // 执行数据库查询
            List<IdCardInfo> idPrefix6AndNameList = idCardInfoService.findIdPrefix6AndNameBYShanghai10(searchKey);

            // 根据查询结果设置消息
            if (idPrefix6AndNameList == null) {
                data.setText("查询失败，请稍后再试。");
            } else if (idPrefix6AndNameList.isEmpty()) {
                data.setText("未找到相关结果，请确认输入的身份证前缀。");
            } else if (idPrefix6AndNameList.size() > 100) {
                data.setText("结果数量过多（>10），消息限制无法回显。请提供更详细的查询条件。");
            } else {
                StringBuilder result = new StringBuilder();
                result.append("查询结果如下：\n");
                int size = idPrefix6AndNameList.size();
                for (int i = 0; i < size; i++) {
                    IdCardInfo idCardInfo = idPrefix6AndNameList.get(i);
                    result.append(idCardInfo.getName())
                            .append(" : ")
                            .append(idCardInfo.getIdCardNumber());

                    // 如果不是最后一条记录，则添加换行符
                    if (i < size - 1) {
                        result.append("\n");
                    }
                }
                data.setText(result.toString());
            }
        } else {
            // 非身份证查询请求的其他处理逻辑
            // data.setText("未知指令，请输入正确的身份证查询格式（例如：sfz310000）。");
            return;
        }

        // 创建消息对象并设置内容
        SendPrivateMessageRequest.Message message = new SendPrivateMessageRequest.Message(data, "text");
        messages.add(message);

        // 设置消息列表
        request.setMessage(messages);

        // 调试输出请求内容
        log.debug("发送请求: {}", request);

        // 发送消息
        napCatQQClient.sendPrivateMessage(request).enqueue(new retrofit2.Callback<>() {
            @Override
            public void onResponse(Call<NapCatQQBaseResponse> call, Response<NapCatQQBaseResponse> response) {
                if (response.isSuccessful()) {
                    log.debug("消息发送成功: {}", response.body());
                } else {
                    log.error("消息发送失败，状态码: {}, 错误消息: {}", response.code(), response.message());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<NapCatQQBaseResponse> call, Throwable t) {
                log.error("消息发送失败，错误信息: {}", t.getMessage());
            }
        });
    }

    @Override
    public void setEnabled(boolean enabled) {
        // 实现必要的启用/禁用逻辑
    }

    @Override
    public boolean isEnabled() {
        return false;  // 默认返回 false 或根据实际情况修改
    }
}
