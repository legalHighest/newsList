package com.example.networkapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.networkapplication.entity.News;

import java.util.List;

public class NewsListViewModel extends ViewModel {
    private MutableLiveData<List<News>> news = new MutableLiveData<>();

    public NewsListViewModel(List<News> news) {
        loadNews(news);
    }

    public MutableLiveData<List<News>> getUsers() {
        return news;
    }

    private void loadNews(List<News> news) {
        // 通常这里会是调用 Repository 层的代码
        List<News> dummyUsers = news;
        this.news.setValue(dummyUsers);
    }


}
