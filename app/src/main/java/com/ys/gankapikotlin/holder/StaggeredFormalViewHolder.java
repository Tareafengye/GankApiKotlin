package com.ys.gankapikotlin.holder;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ys.gankapikotlin.R;
import com.ys.gankapikotlin.model.GankApiModel;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2020/4/811:06 AM
 * desc   :
 * version: 1.0
 */
public class StaggeredFormalViewHolder extends RecyclerView.ViewHolder {

    private ImageView productImg;
    private TextView descriptionText;
    private Context mContext;

    public static StaggeredFormalViewHolder newInstance(ViewGroup viewGroup){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gank_item, viewGroup, false);
        return new StaggeredFormalViewHolder(itemView);
    }

    private StaggeredFormalViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        productImg = (ImageView) itemView.findViewById(R.id.img_recycle);
        int width = getScreenWidthPx();
        int margin = dip2px(mContext, 5 * 4);
        int w = (width - margin) / 2;
        ViewGroup.LayoutParams layoutParams = productImg.getLayoutParams();
        layoutParams.width = w;
        layoutParams.height = w;
        productImg.setLayoutParams(layoutParams);
        descriptionText = (TextView) itemView.findViewById(R.id.tv_gank_context);
    }

    /**
     * 刷新itemView并对其子view填充数据
     * @param vo
     */
    public void onBind(GankApiModel.DataBean vo) {
        if (!TextUtils.isEmpty(vo.getUrl())) {
            productImg.setImageURI(Uri.parse(vo.getUrl()));
        }
        if (!TextUtils.isEmpty(vo.getDesc())) {
            descriptionText.setText(vo.getDesc());
            descriptionText.setVisibility(View.VISIBLE);
        } else {
            descriptionText.setVisibility(View.GONE);
        }
    }

    private int getScreenWidthPx() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private int getScreenHeightPx() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px(像素)
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 在对itemView的childView添加点击监听时，可以将v.getContext()传入，因为从23.3.0开始它不再返回Activity，而是返回
     * TintContextWrapper
     * https://stackoverflow.com/questions/38814267/android-support-v7-widget-tintcontextwrapper-cannot-be-cast
     * @param context
     * @return
     */
    private Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }
}
