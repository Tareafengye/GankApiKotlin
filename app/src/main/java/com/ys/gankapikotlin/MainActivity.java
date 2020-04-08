package com.ys.gankapikotlin;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ys.gankapikotlin.adapter.GanApiAdapter;
import com.ys.gankapikotlin.adapter.SpaceItemDecoration;
import com.ys.gankapikotlin.base.BaseActivity;
import com.ys.gankapikotlin.model.GankApiModel;
import com.ys.gankapikotlin.mvp.presenter.GankApiPresenter;
import com.ys.gankapikotlin.utils.DensityUtil;
import com.ys.gankapikotlin.utils.SystemBarHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<GankApiPresenter> {


    @BindView(R.id.view_height)
    View mHeight;

    @BindView(R.id.recycle_gank)
    RecyclerView mRecycleGank;
    @BindView(R.id.smartLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    private GanApiAdapter adapter;
    private List<GankApiModel.DataBean> list = new ArrayList<>();
    private int page = 1;
    private boolean isFinishs;


    @Override
    protected void widgetClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;

    }

    @Override
    public void initData() {
        SystemBarHelper.setHeightAndPadding(this, mHeight);
        SystemBarHelper.immersiveStatusBar(this, 0f);

        getP().userGankApi(page, true);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecycleGank.setLayoutManager(manager);
        mRecycleGank.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this, 5)));
        adapter = new GanApiAdapter(this, list, R.layout.gank_item);
        mRecycleGank.setAdapter(adapter);
        // 刷新事件
        mSmartRefreshLayout.setOnRefreshListener(refreshlayout -> {
                    page = 1;
                    getP().userGankApi(page, true);
                }
        );

        // 加载更多事件
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            getP().userGankApi(page, false);

        });

        // 开始时自动刷新
//        mSmartRefreshLayout.autoRefresh();

    }


    @Override
    public void initListener() {

    }

    public void onGankAPiData(GankApiModel model, boolean isFinish) {

        list.addAll(model.getData());
        if (isFinish) {
            mSmartRefreshLayout.finishRefresh(true);
        } else {
            mSmartRefreshLayout.finishLoadMore(true);
        }

        adapter.notifyDataSetChanged();


    }



    public void onFinishError() {
        mSmartRefreshLayout.finishRefresh();
        adapter.notifyDataSetChanged();
    }
}
