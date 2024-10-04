package org.thedatabot.thedatabot.entity.httpinterface;

import org.thedatabot.thedatabot.entity.request.SendPrivateMessageRequest;
import org.thedatabot.thedatabot.entity.response.NapCatQQBaseResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NapCatQQ {
    @POST("send_private_msg")
    Call<NapCatQQBaseResponse> sendPrivateMessage(@Body SendPrivateMessageRequest request);

    // @POST("send_group_msg")
    // Call<ApiResponse> sendGroupMessage(@Body SendGroupMessageRequest request);
    //
    // @POST("delete_msg")
    // Call<ApiResponse> deleteMessage(@Body DeleteMessageRequest request);
    //
    // @POST("get_msg")
    // Call<ApiResponse> getMessage(@Body GetMessageRequest request);
    //
    // @POST("send_like")
    // Call<ApiResponse> sendLike(@Body SendLikeRequest request);
    //
    // @POST("set_group_kick")
    // Call<ApiResponse> setGroupKick(@Body SetGroupKickRequest request);
    //
    // @POST("set_group_ban")
    // Call<ApiResponse> setGroupBan(@Body SetGroupBanRequest request);
}
