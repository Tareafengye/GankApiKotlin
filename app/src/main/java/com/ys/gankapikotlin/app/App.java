package com.ys.gankapikotlin.app;

import android.app.Application;
import android.content.Context;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2020/4/74:57 PM
 * desc   :
 * version: 1.0
 */
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instance=this;
    }

    public static Context getContext(){
        return context;
    }
    private static App instance;
    public static App getInstance() {

        return instance;
    }
}
