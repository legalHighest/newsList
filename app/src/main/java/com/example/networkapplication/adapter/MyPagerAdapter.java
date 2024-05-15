package com.example.networkapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.networkapplication.view.NewsListFragment;

import java.util.List;

public class MyPagerAdapter extends FragmentStateAdapter {

    //管理所有的fragment
    List<NewsListFragment> fragmentList;

    //    新闻类型
    String type;

    public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<NewsListFragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }


    @Override
    public int getItemCount() {
        return fragmentList != null ? fragmentList.size() : 0;
    }
}
