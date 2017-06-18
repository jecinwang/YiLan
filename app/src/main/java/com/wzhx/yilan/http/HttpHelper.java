package com.wzhx.yilan.http;

import android.content.Context;

import com.wzhx.yilan.YiLanApplication;
import com.wzhx.yilan.common.utils.FileUtil;
import com.wzhx.yilan.common.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wzhx on 2017/6/17.
 */

public class HttpHelper {
    private Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;

    private Context mContext;

    private HashMap<String, Object> mServiceMap;

    public HttpHelper() {

        mContext = YiLanApplication.getApplication();
        mServiceMap = new HashMap<>();

        File file = new File(FileUtil.getCacheDirPath(mContext), "HttpCache");
        int size = 1024 * 1024 * 20;
        Cache cache = new Cache(file, size);

        mOkHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new CacheInterceptor())
                .build();
    }

    /**
     * 创建ApiService
     * @param clazz
     * @param baseUrl
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> clazz, String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit.create(clazz);
    }

    public <T> T getService(Class<T> clazz, String baseUrl) {
        if (mServiceMap.containsKey(clazz.getName())) {
            return (T) mServiceMap.get(clazz);
        } else {
            Object obj = createService(clazz, baseUrl);
            mServiceMap.put(clazz.getName(), obj);
            return (T) obj;
        }
    }

    private class CacheInterceptor implements Interceptor {

        // 有网络时 设置缓存超时时间1个小时
        int maxAge = 0;
        // 无网络时，设置超时为1个月
        int maxStale = 60 * 60 * 24 * 30;

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            if (!NetworkUtils.isNetworkAvailable(mContext)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);
            if (NetworkUtils.isNetworkAvailable(mContext)) {
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }
}
