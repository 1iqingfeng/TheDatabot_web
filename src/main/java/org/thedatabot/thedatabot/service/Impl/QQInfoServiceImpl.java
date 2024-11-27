package org.thedatabot.thedatabot.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;
import org.thedatabot.thedatabot.entity.httpinterface.NapCatQQ;
import org.thedatabot.thedatabot.entity.httpinterface.SKGApiResponse;
import org.thedatabot.thedatabot.entity.request.SendPrivateMessageRequest;
import org.thedatabot.thedatabot.entity.response.SKGResponse;
import org.thedatabot.thedatabot.http.EmptyCallback;
import org.thedatabot.thedatabot.http.MessageBuilder;
import org.thedatabot.thedatabot.service.QQInfoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Service
@Slf4j
public class QQInfoServiceImpl implements QQInfoService {
    private final SKGApiResponse skgApiResponse;
    private final NapCatQQ napCatQQClient;
    public QQInfoServiceImpl(SKGApiResponse skgApiResponse, NapCatQQ napCatQQClient) {
        this.skgApiResponse = skgApiResponse;
        this.napCatQQClient = napCatQQClient;
    }

    @Override
    public void QQInfoBySKGResponse(String searchKey, MessageEvent messageEvent) {
        skgApiResponse.getQQInfo(searchKey).enqueue(new Callback<SKGResponse>() {

            @Override
            public void onResponse(Call<SKGResponse> call, Response<SKGResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // 处理成功的响应
                    SKGResponse data = response.body();
                    log.debug("查询的qq信息{}",data);
                    if (data.getPhone()==null){
                        SendPrivateMessageRequest request = MessageBuilder.buildRequest(messageEvent.getUserId(), "服务器出现查询问题");
                        napCatQQClient.sendPrivateMessage(request).enqueue(new EmptyCallback<>());
                        return;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder
                            .append("电话号: ").append(data.getPhone()).append("\n")
                            .append("电话地区: ").append(data.getPhonediqu()).append("\n")
                            .append("微博账号: ").append(data.getWb()).append("\n")
                            .append("英雄联盟: ").append(data.getLol()).append("\n")
                            .append("老密码: ").append(data.getQqlm());
                    SendPrivateMessageRequest request = MessageBuilder.buildRequest(messageEvent.getUserId(), stringBuilder.toString());
                    napCatQQClient.sendPrivateMessage(request).enqueue(new EmptyCallback<>());
                } else {
                    // 处理失败的响应
                    log.error("获取信息失败，状态码: {}, 错误消息: {}", response.code(), response.message());
                }
            }

            @Override
            public void onFailure(Call<SKGResponse> call, Throwable t) {
                // 处理请求失败的情况
                log.error("请求失败，错误信息: {}", t.getMessage());
            }
        });
    }
}
