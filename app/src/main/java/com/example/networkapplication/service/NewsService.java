package com.example.networkapplication.service;

import com.example.networkapplication.model.NewsDetailResult;
import com.example.networkapplication.model.NewsResult;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NewsService {



//    @GET("toutiao/index")
//    Call<ResponseBody> getlistRepos(@Header("Content-Type") String header, @Query("type") String type, @Query("page") int page, @Query("page_size") int pageSize, @Query("is_filter") boolean isFilter, @Query("key") String key);

    /**
     * 根据新闻类型查询默认新闻头条
     *
     * @param header
     * @param type
     * @param key
     * @return
     */
    @GET("toutiao/index")
    Observable<NewsResult> getlistRepos(@Header("Content-Type") String header, @Query("type") String type, @Query("page") int page, @Query("page_size") int pageSize, @Query("is_filter") boolean isFilter, @Query("key") String key);


    /**
     * 根据新闻主键查询新闻详情
     * @param header
     * @param uniquekey
     * @param key
     * @return
     */
    @GET("toutiao/content")
    Observable<NewsDetailResult> searchDetailByUniquekey(@Header("Content-Type") String header, @Query("uniquekey") String uniquekey, @Query("key") String key);

}


