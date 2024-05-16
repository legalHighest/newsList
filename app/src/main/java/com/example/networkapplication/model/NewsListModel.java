package com.example.networkapplication.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.networkapplication.entity.News;
import com.example.networkapplication.service.NewsService;
import com.example.networkapplication.utils.Page;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsListModel {

    NewsService newsService;
    /**
     * 请求对象
     */
    Retrofit retrofit2;

    /**
     * 初始化请求对象
     */
    private void createController() {
        retrofit2 = new Retrofit.Builder()
                .baseUrl("https://v.juhe.cn/")
//            添加 Gson 转换器工厂将返回结果转换成json
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        newsService = retrofit2.create(NewsService.class);
    }

    public  interface  OnNewsCallback{
        void onResponse(List<News> newsList);
    }

    /**
     * 查询新闻列表
     */
    private void searchNewsList(@NonNull Page page, String type,boolean isFilter,OnNewsCallback onNewsCallback) {
        createController();
        newsService.getlistRepos("application/x-www-form-urlencoded", type, page.getPage(), page.getPageSize(), isFilter
                        , "046ff458286b3d73e1d73785a8854c3e").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResult>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull NewsResult newsResult) {
                        List<News> data = newsResult.getResult().getData();
                        onNewsCallback.onResponse(data);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e("e", "请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
