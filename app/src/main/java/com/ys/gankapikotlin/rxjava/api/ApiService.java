package com.ys.gankapikotlin.rxjava.api;


import com.ys.gankapikotlin.model.GankApiModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2020/4/75:04 PM
 * desc   :
 * version: 1.0
 */
public interface ApiService {

    /**
     * 发福利
     */
    @GET("data/category/Girl/type/Girl/page/{path}/count/10")
    Observable<GankApiModel> gankApi(@Path("path") int path);
}
