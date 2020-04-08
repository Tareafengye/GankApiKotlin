package com.ys.gankapikotlin.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ys.gankapikotlin.R;

import java.io.File;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2020/4/85:00 PM
 * desc   :
 * version: 1.0
 */
public class ImageLoader {
    public ImageLoader(Context context) {

    }

    public static Builder createBuilder(Context context) {

        return new Builder(context);
    }

    public static class Builder {
        private Context mContext;
        private String url;
        ImageView imageView;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setImgUrl(String url) {
            this.url = url;
            return this;
        }



        public Builder setImageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }


        public ImageLoader onCreate() {
            ImageLoader loader = new ImageLoader(mContext);
            RequestOptions options = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher);
            File file = new GlideImageUtils(mContext).getCacheFile(url);
            if (null != file) {
                Glide.with(mContext).load(file).apply(options).into(imageView);
            } else {

                Glide.with(mContext).load(url).apply(options).into(imageView);
            }
            return loader;
        }
    }
}
