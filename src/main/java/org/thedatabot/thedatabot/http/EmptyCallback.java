package org.thedatabot.thedatabot.http;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmptyCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        // 空实现
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        // 空实现
    }
}
