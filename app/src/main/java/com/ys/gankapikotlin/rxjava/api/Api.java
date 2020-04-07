package com.ys.gankapikotlin.rxjava.api;


import com.ys.gankapikotlin.rxjava.BaseApi;

public class Api {
    private static ApiService apiService;

    public static ApiService getApiService(String url){
        if(apiService == null){
            synchronized (Api.class){
                if(null == apiService){
                    apiService = BaseApi.getInstance().getRetrofit(url).create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
