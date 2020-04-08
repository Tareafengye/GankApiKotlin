package com.ys.gankapikotlin;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.ys.gankapikotlin.adapter.GanApiAdapter;
import com.ys.gankapikotlin.adapter.SpaceItemDecoration;
import com.ys.gankapikotlin.base.BaseActivity;
import com.ys.gankapikotlin.listenner.ItemClickListener;
import com.ys.gankapikotlin.model.GankApiModel;
import com.ys.gankapikotlin.model.GankBeanModel;
import com.ys.gankapikotlin.mvp.presenter.GankApiPresenter;
import com.ys.gankapikotlin.utils.DensityUtil;
import com.ys.gankapikotlin.utils.GlideImageLoader;
import com.ys.gankapikotlin.utils.SystemBarHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<GankApiPresenter> implements XRecyclerView.LoadingListener {


    @BindView(R.id.view_height)
    View mHeight;

    @BindView(R.id.recycle_gank)
    XRecyclerView mRecycleGank;
    Banner banner;
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

        getP().userGankApi(page);
        getP().gankBanner();

        View heightView= LayoutInflater.from(this).inflate(R.layout.gank_height_view,null,true);
        banner=heightView.findViewById(R.id.banner);
        mRecycleGank.addHeaderView(heightView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecycleGank.setLayoutManager(manager);
        mRecycleGank.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(this, 5)));
        adapter = new GanApiAdapter(this, list, R.layout.gank_item);
        mRecycleGank.setAdapter(adapter);
        mRecycleGank.setHasFixedSize(true);
        mRecycleGank.setNestedScrollingEnabled(false);
        mRecycleGank.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //刷新效果
        mRecycleGank.setLoadingListener(this);
        adapter.setmItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View v, int position) {
                return false;
            }
        });

        // 开始时自动刷新
//        mSmartRefreshLayout.autoRefresh();

    }


    @Override
    public void initListener() {

    }

    public void onGankAPiData(GankApiModel model) {
        list.addAll(model.getData());
        adapter.notifyDataSetChanged();
        mRecycleGank.refreshComplete();  // 停止刷新状态
        mRecycleGank.loadMoreComplete();
    }
    public void onFinishError() {
        adapter.notifyDataSetChanged();
        mRecycleGank.refreshComplete();  // 停止刷新状态
        mRecycleGank.loadMoreComplete();
    }
    public void initBanner(GankBeanModel model) {

        List<String> url = new ArrayList<>();
        for (int i = 0; i < model.getData().size(); i++) {
            url.add(model.getData().get(i).getImage());
        }

        banner.setImages(url)
                .setDelayTime(3000)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                })
                .start();
    }
    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onRefresh() {
        page = 1;
        getP().userGankApi(page);
        mRecycleGank.loadMoreComplete();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        page++;
        getP().userGankApi(page);
        mRecycleGank.loadMoreComplete();
        adapter.notifyDataSetChanged();
    }
}
