package com.ys.gankapikotlin.rxjava;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApi {

    private static BaseApi instance;

    public static BaseApi getInstance() {
        if (null == instance) {
            synchronized (BaseApi.class) {
                if (null == instance) {
                    instance = new BaseApi();
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit(String url) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(url)
                .client(RetrofitUtils.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return build;
    }


}
