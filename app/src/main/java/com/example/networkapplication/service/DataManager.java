package com.example.networkapplication.service;

import com.example.networkapplication.model.NewsResult;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Header;
import retrofit2.http.Query;

//model层
public class DataManager {
    private static DataManager dataManager;
    private NewsService newsService;

    public static DataManager getInstance() {
        return dataManager == null ? dataManager = new DataManager() : dataManager;
    }

    private DataManager() {
        newsService = RetrofitHelper.getInstance().getRetrofitService();
    }


    /**
     * 查询新闻列表
     *
     * @param header
     * @param type
     * @param page
     * @param pageSize
     * @param isFilter
     * @param key
     * @return
     */
    public Observable<NewsResult> getlistRepos(@Header("Content-Type") String header, @Query("type") String type, @Query("page") int page, @Query("page_size") int pageSize, @Query("is_filter") boolean isFilter, @Query("key") String key) {
        return newsService.getlistRepos(header, type, page, pageSize, isFilter, key);
    }

}
