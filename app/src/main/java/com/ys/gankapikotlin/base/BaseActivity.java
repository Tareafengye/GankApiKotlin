package com.ys.gankapikotlin.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ys.gankapikotlin.mvp.iml.IView;
import com.ys.gankapikotlin.utils.StaticUtils;

import java.lang.reflect.ParameterizedType;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import butterknife.ButterKnife;
import retrofit2.HttpException;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView, View.OnClickListener{

    private P p;
    private View emptyView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData();
        initListener();
    }


      /**
     * View点击
     **/
    protected abstract void widgetClick(View v);


    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void showLongToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 获取Presenter
     */
    public P getP(){
        if(p == null){
            //泛型形式实例化P层
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            Class<P> clazz = (Class<P>) parameterizedType.getActualTypeArguments()[0];
            try {
                p = clazz.newInstance();
            }catch (Exception e){
                StaticUtils.loge(e.getMessage());
            }
        }
        if(p != null){
            if(!p.isAttachedV()){
                p.attachView(this);
            }
        }
        return p;
    }

    /**
     * 错误处理
     */
    public void onError(Throwable e){
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(getP()!=null){
            getP().detachView();
        }
        p = null;
    }


    /**
     * @param clazz 启动activity
     */
    public void start(@NonNull Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetClick(v);

    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

}
