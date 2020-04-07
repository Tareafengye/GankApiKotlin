package com.ys.gankapikotlin.listenner;

import android.view.View;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2019-09-0315:08
 * desc   :
 * version: 1.0
 */
public interface ItemClickListener {
    void onItemClick(View v, int position);

    boolean onItemLongClick(View v, int position);
}
