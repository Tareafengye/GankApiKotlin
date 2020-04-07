package com.ys.gankapikotlin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ys.gankapikotlin.base.BaseActivity;
import com.ys.gankapikotlin.model.GankApiModel;
import com.ys.gankapikotlin.mvp.presenter.GankApiPresenter;

public class MainActivity extends BaseActivity<GankApiPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void widgetClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
    public void onGankAPiData(GankApiModel model){}
}
