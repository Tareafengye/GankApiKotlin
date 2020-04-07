package com.ys.gankapikotlin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ys.gankapikotlin.base.BaseActivity;
import com.ys.gankapikotlin.model.GankApiModel;
import com.ys.gankapikotlin.mvp.presenter.GankApiPresenter;

public class MainActivity extends BaseActivity<GankApiPresenter> {



    @Override
    protected void widgetClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        getP().userGankApi(1);
    }

    @Override
    public void initListener() {

    }
    public void onGankAPiData(GankApiModel model){
        Toast.makeText(this, model.getPage()+"", Toast.LENGTH_SHORT).show();

    }
}
