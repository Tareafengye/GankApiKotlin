package com.ys.gankapikotlin.adapter;

import android.content.Context;


import com.ys.gankapikotlin.base.BaseRecyclerAdapter;
import com.ys.gankapikotlin.holder.RecycleViewHolder;
import com.ys.gankapikotlin.model.GankApiModel;

import java.util.List;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2020/4/75:29 PM
 * desc   :
 * version: 1.0
 */
public class GanApiAdapter extends BaseRecyclerAdapter<GankApiModel.DataBean> {
    public GanApiAdapter(Context context, List<GankApiModel.DataBean> mData, int mLayoutId) {
        super(context, mData, mLayoutId);
    }

    @Override
    protected void convert(RecycleViewHolder holder, GankApiModel.DataBean item, int position) {

    }
}
