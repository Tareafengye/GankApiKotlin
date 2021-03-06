package com.ys.gankapikotlin.mvp.presenter;


import com.ys.gankapikotlin.MainActivity;
import com.ys.gankapikotlin.base.BasePresenter;
import com.ys.gankapikotlin.model.GankApiModel;
import com.ys.gankapikotlin.model.GankBeanModel;
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
        request(Api.getApiService(Constants.BASE_URL).gankApi(pash), new OnRespListener<GankApiModel>() {
            @Override
            public void onSuccess(GankApiModel value) {
                if (value.getData().size() > 0 || value.getData() != null) {

                    getV().onGankAPiData(value);
                }
            }

            @Override
            public void onFailed(Throwable e) {
                getV().onFinishError();

            }
        });

    }
    public void gankBanner() {
        request(Api.getApiService(Constants.BASE_URL).gankBanner(), new OnRespListener<GankBeanModel>() {
            @Override
            public void onSuccess(GankBeanModel value) {
                if (value.getData().size() > 0 || value.getData() != null) {

                    getV().initBanner(value);
                }
            }

            @Override
            public void onFailed(Throwable e) {
                getV().onFinishError();

            }
        });

    }
}
