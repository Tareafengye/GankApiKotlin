package com.ys.gankapikotlin.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2019-09-1622:10
 * desc   :
 * version: 1.0
 */
public class JsonMapUtils {

    private static Gson gson;

    public static RequestBody mapToJson(Map<String, String> map) {

        gson = new GsonBuilder().disableHtmlEscaping().create();
        String json = gson.toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Log.d("mapToJson",json+"");
        return requestBody;
    }
}
