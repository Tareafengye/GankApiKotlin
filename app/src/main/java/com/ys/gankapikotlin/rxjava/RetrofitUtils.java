package com.ys.gankapikotlin.rxjava;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author : liutiantian
 * e-mail : Tareafengye@163.com
 * date   : 2019-09-1209:36
 * desc   :
 * version: 1.0
 */
public class RetrofitUtils {
    public static String TAG = "LogInterceptor";
    /**
     * 获取OkHttpClient
     * 用于打印请求参数
     *
     * @return OkHttpClient
     */
    public static OkHttpClient getOkHttpClient() {
        // 日志显示级别
        // 新建log拦截器
        Interceptor loggingInterceptor = chain -> {

            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration=endTime-startTime;
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.d(TAG,"\n");
            Log.d(TAG,"----------Start----------------");
            Log.d(TAG, "| "+request.toString());
            String method=request.method();
            if("POST".equals(method)){
                StringBuilder sb = new StringBuilder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int i = 0; i < body.size(); i++) {
                        sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                    }
                    sb.delete(sb.length() - 1, sb.length());
                    Log.d(TAG, "| RequestParams:{"+sb.toString()+"}");
                }
            }
            Log.d(TAG, "| Response:" + content);
            Log.d(TAG,"----------End:"+duration+"毫秒----------");
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        };
        // 定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        // OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);

        return httpClientBuilder.build();
    }
    public static class LoggerInterceptor implements Interceptor {
        private static String TAG = "LoggerInterceptor";
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.i(TAG, String.format("发送请求:%s on %s%n%s%n%s",
                    request.url(), chain.connection(), request.headers(), request.body()));
            return chain.proceed(request);
        }
    }
}
