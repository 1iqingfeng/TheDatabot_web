package org.thedatabot.thedatabot.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thedatabot.thedatabot.entity.httpinterface.NapCatQQ;
import org.thedatabot.thedatabot.entity.httpinterface.SKGApiResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class NapCatQQConfig {

    // 第一个 BASE_URL
    private static final String BASE_URL_1 = "http://127.0.0.1:5012/";
    // 第二个 BASE_URL
    private static final String BASE_URL_2 = "https://api.xywlapi.cc/";

    @Bean
    public Retrofit retrofit1() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Bean
    public Retrofit retrofit2() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Bean
    public NapCatQQ napCatQQClient(Retrofit retrofit1) {
        return retrofit1.create(NapCatQQ.class);
    }

    @Bean
    public SKGApiResponse SKGApiResponse(Retrofit retrofit2) {
        return retrofit2.create(SKGApiResponse.class);
    }
}
