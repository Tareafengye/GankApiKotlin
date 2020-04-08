package com.ys.gankapikotlin.base;

import android.util.Log;
import android.widget.Toast;


import com.ys.gankapikotlin.app.App;
import com.ys.gankapikotlin.mvp.iml.IPresenter;
import com.ys.gankapikotlin.mvp.iml.IView;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2020/4/74:51 PM
 * desc   :
 * version: 1.0
 */
public class BasePresenter <V extends IView> implements IPresenter<V> {

    private WeakReference<V> v;

    @Override
    public void attachView(V view) {
        v = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (v.get() != null) {
            v.clear();
        }
        v = null;
    }

    @Override
    public V getV() {
        if (v == null || v.get() == null) {
            return null;
        }
        return v.get();
    }

    @Override
    public boolean isAttachedV() {
        return v != null && v.get() != null;
    }

    /**
     * 网络请求的封装
     */
    protected <M> void request(Observable<M> api, final OnRespListener<M> listener) {
        api.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<M>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(M value) {
                        if (getV() != null) {
                            listener.onSuccess(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getV() != null) {
                            listener.onFailed(e);
                            onFailed(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 网络请求的封装
     *  将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
     *  以此决定是否重新订阅 & 发送原来的 Observable，即轮询
     *  此处有2种情况：
     * 1. 若返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable，即轮询结束
     *  2. 若返回其余事件，则重新订阅 & 发送原来的 Observable，即继续轮询
     * ————————————————
     * 原文链接：https://blog.csdn.net/carson_ho/article/details/78558790
     */
    protected <M> void requestWheel(Observable<M> api, final OnRespListener<M> listener) {
        api.repeatWhen(objectObservable -> objectObservable.flatMap((Function<Object, ObservableSource<?>>)
                throwable -> Observable.just(1).delay(3000, TimeUnit.MILLISECONDS)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<M>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(M value) {
                        if (getV() != null) {
                            listener.onSuccess(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getV() != null) {
                            onFailed(e);
                            listener.onFailed(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
    /**
     * 错误处理
     */
    public void onFailed(Throwable e){
        if (e instanceof UnknownHostException) {
            showLongToast("请打开网络");
        } else if (e instanceof SocketTimeoutException) {
            showLongToast("请求超时");
        } else if (e instanceof ConnectException) {
            showLongToast("连接失败");
        } else if (e instanceof HttpException) {
            showLongToast("请求超时");
        }else {
            showLongToast("请求失败:"+e.getMessage());
            Log.d("aaaaaaaaawe",e.getMessage()+"");
        }
    }
    public void showLongToast(String msg){
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_LONG).show();
    }
    public interface OnRespListener<M> {
        void onSuccess(M value);

        void onFailed(Throwable e);
    }
}
