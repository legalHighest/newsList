package com.example.networkapplication.presenter;

import android.util.Log;

import com.example.networkapplication.databinding.FragmentNewsListBinding;
import com.example.networkapplication.model.NewsListModel;
import com.example.networkapplication.view.NewsListFragment;

public class NewsListPresenter {
    NewsListModel newsListModel;

    NewsListFragment newsListFragment;


    public  NewsListPresenter(NewsListFragment newsListFragment) {
        this.newsListFragment=newsListFragment;
        newsListModel=new NewsListModel();
    }

   void showListOrshowMore(){
       if (newsListFragment.getIfFirst()) {
           Log.e("1", "1");
           mAdapter.addNews(data);
       } else {
           Log.e("2", "2");
           newsListFragment.showList(data);
       }
   }
}
