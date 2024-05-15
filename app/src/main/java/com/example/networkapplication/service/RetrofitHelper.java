package com.example.networkapplication.service;

import com.example.networkapplication.utils.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static RetrofitHelper retrofitHelper;
    private NewsService newsService;

    public static RetrofitHelper getInstance() {
        return retrofitHelper == null ? retrofitHelper = new RetrofitHelper() : retrofitHelper;
    }

    private RetrofitHelper() {
        // 初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL.getUrl())
                .addConverterFactory(GsonConverterFactory.create()) // json解析
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        newsService = retrofit.create(NewsService.class);
    }

    public NewsService getRetrofitService() {
        return newsService;
    }
}
