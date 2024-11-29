package org.thedatabot.thedatabot.entity.httpinterface;

import org.springframework.web.bind.annotation.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface  VMYApiServer  {
    @GET()
    @Streaming()
    Call<ResponseBody> get_muyu(@Url String url);
}