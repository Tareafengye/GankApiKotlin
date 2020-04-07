package com.ys.gankapikotlin.mvp.presenter;


import com.ys.gankapikotlin.MainActivity;
import com.ys.gankapikotlin.base.BasePresenter;
import com.ys.gankapikotlin.rxjava.api.Api;
import com.ys.gankapikotlin.rxjava.api.Constants;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2020/4/74:59 PM
 * desc   :
 * version: 1.0
 */
public class GankApiPresenter extends BasePresenter<MainActivity> {
    public void userGankApi(int pash) {
        request(Api.getApiService(Constants.BASE_URL).gankApi(pash), model -> {
            //网络请求成功返回数据
            getV().onGankAPiData(model);

        });
    }
}
