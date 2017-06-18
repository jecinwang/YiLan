package com.wzhx.yilan.http.api;

import com.wzhx.yilan.news.model.News;
import com.wzhx.yilan.news.model.NewsTimeLine;
import com.wzhx.yilan.news.model.SplashImage;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wzhx on 2017/6/17.
 */

public interface ZhihuApi {
    @GET("start-image/1080*1920")
    Observable<SplashImage> getSplashImage();

    @GET("news/latest")
    Observable<NewsTimeLine> getLatestNews();

    @GET("news/before/{time}")
    Observable<NewsTimeLine> getBeforetNews(@Path("time") String time);

    @GET("news/{id}")
    Observable<News> getDetailNews(@Path("id") String id);
}
