package com.ys.gankapikotlin.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ys.gankapikotlin.R;
import com.ys.gankapikotlin.base.BaseRecyclerAdapter;
import com.ys.gankapikotlin.holder.RecycleViewHolder;
import com.ys.gankapikotlin.model.GankApiModel;
import com.ys.gankapikotlin.utils.DensityUtil;
import com.ys.gankapikotlin.utils.GlideImageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2020/4/75:29 PM
 * desc   :
 * version: 1.0
 */
public class GanApiAdapter extends BaseRecyclerAdapter<GankApiModel.DataBean> {
    private Context mContext;
    private List<Integer> height = new ArrayList<>();

    public GanApiAdapter(Context context, List<GankApiModel.DataBean> mData, int mLayoutId) {
        super(context, mData, mLayoutId);
        this.mContext = context;


    }

    @Override
    protected void convert(RecycleViewHolder holder, GankApiModel.DataBean item, int position) {

        ImageView img_recycle = holder.getView(R.id.img_recycle);
        holder.setText(R.id.tv_gank_context, item.getDesc());
        int x = new Random().nextInt((DensityUtil.getScreenWidth(mContext) - 4 * 2)) + 200;
        ViewGroup.LayoutParams params = img_recycle.getLayoutParams();
        params.height = x;

        img_recycle.setLayoutParams(params);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
        //把图片缓存在本地，优先加载本地，不消耗流量

        File file = new GlideImageUtils(mContext).getCacheFile(item.getUrl());
        if (null!=file) {
            Glide.with(mContext).load(file).apply(options).into(img_recycle);
        }else {
            Glide.with(mContext).load(item.getUrl()).apply(options).into(img_recycle);
        }
    }
}
