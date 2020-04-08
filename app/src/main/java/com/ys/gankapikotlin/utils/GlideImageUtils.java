package com.ys.gankapikotlin.utils;

import android.content.Context;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.SafeKeyGenerator;
import com.bumptech.glide.signature.EmptySignature;

import java.io.File;
import java.io.IOException;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2020/4/84:50 PM
 * desc   :
 * version: 1.0
 */
public class GlideImageUtils {
    private Context context;
    public GlideImageUtils(Context context){
        this.context=context;
    }

    public File getCacheFile(String id) {
        OriginalKey originalKey = new OriginalKey(id, EmptySignature.obtain());
        SafeKeyGenerator safeKeyGenerator = new SafeKeyGenerator();
        String safeKey = safeKeyGenerator.getSafeKey(originalKey);
        try {
            DiskLruCache diskLruCache = DiskLruCache.open(new File(context.getCacheDir(), DiskCache.Factory.DEFAULT_DISK_CACHE_DIR), 1, 1, DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE);
            DiskLruCache.Value value = diskLruCache.get(safeKey);
            if (value != null) {
                return value.getFile(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
