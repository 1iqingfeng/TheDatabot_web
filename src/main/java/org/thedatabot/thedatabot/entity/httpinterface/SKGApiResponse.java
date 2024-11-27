package org.thedatabot.thedatabot.entity.httpinterface;

import org.thedatabot.thedatabot.entity.response.SKGResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SKGApiResponse {
    @GET("qqcx2023")
    Call<SKGResponse> getQQInfo(@Query("qq") String qq);
}
