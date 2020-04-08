package com.ys.gankapikotlin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ys.gankapikotlin.adapter.GanApiAdapter;
import com.ys.gankapikotlin.adapter.SpaceItemDecoration;
import com.ys.gankapikotlin.base.BaseActivity;
import com.ys.gankapikotlin.model.GankApiModel;
import com.ys.gankapikotlin.mvp.presenter.GankApiPresenter;
import com.ys.gankapikotlin.utils.DensityUtil;
import com.ys.gankapikotlin.utils.SystemBarHelper;

import butterknife.BindView;

public class MainActivity extends BaseActivity<GankApiPresenter> {


    @BindView(R.id.view_height)
    View mHeight;

    @BindView(R.id.recycle_gank)
    RecyclerView mRecycleGank;
    private GanApiAdapter adapter;


    @Override
    protected void widgetClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;

    }

    @Override
    public void initData() {
//        mTvGank = findViewById(R.id.tv_gank);
        SystemBarHelper.setHeightAndPadding(this, mHeight);
        SystemBarHelper.immersiveStatusBar(this, 0f);
        getP().userGankApi(1);
    }

    @Override
    public void initListener() {

    }

    public void onGankAPiData(GankApiModel model) {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecycleGank.setLayoutManager(manager);

        mRecycleGank.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this,5)));
        adapter=new GanApiAdapter(this,model.getData(),R.layout.gank_item);
        mRecycleGank.setAdapter(adapter);
        Toast.makeText(this, model.getPage() + "", Toast.LENGTH_SHORT).show();

    }
}
