package com.wzhx.yilan.http;

import android.content.Context;

import com.wzhx.yilan.http.api.ZhihuApi;
import com.wzhx.yilan.http.listener.OnResultListener;
import com.wzhx.yilan.http.observer.DataObserver;
import com.wzhx.yilan.news.model.News;
import com.wzhx.yilan.news.model.NewsTimeLine;
import com.wzhx.yilan.news.model.SplashImage;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wzhx on 2017/6/17.
 */

public class HttpUtils {

    private static HttpUtils sHttpUtils;
    private HttpHelper mHttpHelper;

    private HttpUtils() {
        mHttpHelper = new HttpHelper();
    }

    public static HttpUtils getInstance() {
        if (sHttpUtils == null) {
            synchronized (HttpUtils.class) {
                if (sHttpUtils == null) {
                    sHttpUtils = new HttpUtils();
                }
            }
        }
        return sHttpUtils;
    }

    public void getSplashImage(Context context, OnResultListener<SplashImage> onResultListener) {
        ZhihuApi zhihuApi = mHttpHelper.createService(ZhihuApi.class, BaseUrls.ZHIHU_BASE_URL);
        zhihuApi.getSplashImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DataObserver<SplashImage>(context, onResultListener));
    }

    public void getLatestNews(Context context, OnResultListener<NewsTimeLine> onResultListener) {
        ZhihuApi zhihuApi = mHttpHelper.createService(ZhihuApi.class, BaseUrls.ZHIHU_BASE_URL);
        zhihuApi.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DataObserver<NewsTimeLine>(context, onResultListener));
    }

    public void getBeforetNews(Context context, String time, OnResultListener<NewsTimeLine> onResultListener) {
        ZhihuApi zhihuApi = mHttpHelper.createService(ZhihuApi.class, BaseUrls.ZHIHU_BASE_URL);
        zhihuApi.getBeforetNews(time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DataObserver<NewsTimeLine>(context, onResultListener));
    }

    public void getDetailNews(Context context, String id, OnResultListener<News> onResultListener) {
        ZhihuApi zhihuApi = mHttpHelper.createService(ZhihuApi.class, BaseUrls.ZHIHU_BASE_URL);
        zhihuApi.getDetailNews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DataObserver<News>(context, onResultListener));
    }
}
