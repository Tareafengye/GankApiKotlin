package com.ys.gankapikotlin.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.ys.gankapikotlin.R;
import com.ys.gankapikotlin.base.BaseRecyclerAdapter;
import com.ys.gankapikotlin.holder.RecycleViewHolder;
import com.ys.gankapikotlin.model.GankApiModel;
import com.ys.gankapikotlin.utils.DensityUtil;

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
        for (int i = 0; i < mData.size(); i++) {
            int x = new Random().nextInt((DensityUtil.getScreenWidth(mContext) - 8 * 2)) + 200;
            height.add(x);
        }

    }

    @Override
    protected void convert(RecycleViewHolder holder, GankApiModel.DataBean item, int position) {

        ImageView img_recycle = holder.getView(R.id.img_recycle);
        holder.setText(R.id.tv_gank_context,item.getDesc());
        //获取item高度
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) img_recycle.getLayoutParams();
//        float itemWidth = (DensityUtil.getScreenWidth(mContext) - 8 * 2) ;
//        params.width = (int) itemWidth;
//        params.height = height.get(position);
        ViewGroup.LayoutParams params = img_recycle.getLayoutParams();
        params.height = height.get(position);
        img_recycle.setLayoutParams(params);
        Glide.with(mContext).load(item.getUrl()).into(img_recycle);
    }
}
