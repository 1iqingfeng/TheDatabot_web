package org.thedatabot.thedatabot.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thedatabot.thedatabot.entity.httpinterface.NapCatQQ;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class NapCatQQConfig {

    private static final String BASE_URL = "http://127.0.0.1:5012/";

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Bean
    public NapCatQQ napCatQQClient(Retrofit retrofit) {
        return retrofit.create(NapCatQQ.class);
    }
}
