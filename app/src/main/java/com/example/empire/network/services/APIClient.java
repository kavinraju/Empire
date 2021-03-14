package com.example.empire.network.services;

import com.example.empire.network.utils.Endpoints;
import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    public static Retrofit getClient() {
        if (okHttpClient == null) {
            initializeOkHttpClient();
        }

        if (retrofit == null) {
            Moshi moshi = new Moshi.Builder().build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Endpoints.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build();
        }

        return retrofit;
    }

    private static void initializeOkHttpClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.addInterceptor(chain -> chain.proceed(
                chain.request().newBuilder().build()
        ));
        okHttpClient = builder.build();
    }
}
