package com.benharvey.stackoverflowusers.networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by benharvey on 3/31/18.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    private static OkHttpClient httpClient =
            new OkHttpClient.Builder().build();

    private static Retrofit retrofit =
            new Retrofit.Builder()
            .baseUrl(BASE_URL)
                    .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();

    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
