package com.example.networkapplication.view;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.networkapplication.DetailActivity;
import com.example.networkapplication.model.NewsResult;
import com.example.networkapplication.adapter.MyAdapter;
import com.example.networkapplication.databinding.FragmentNewsListBinding;
import com.example.networkapplication.entity.News;
import com.example.networkapplication.utils.Page;
import com.example.networkapplication.service.NewsService;
import com.example.networkapplication.service.OnTabSelectedListener;
import com.example.networkapplication.viewmodel.NewsListViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsListFragment extends Fragment implements OnTabSelectedListener {

    FragmentNewsListBinding fragmentNewsListBinding;
    NewsListViewModel newsListViewModel;
    //    新闻类型
    String type;

    /**
     * 请求对象
     */
    Retrofit retrofit2;


    Page newsPage;

    /**
     * 是否已经初始化列表
     */
    boolean ifFirst;


    /**
     * 自定义适配器
     */
    MyAdapter mAdapter;

    List<News> news;

    NewsService newsService;

    public NewsListFragment() {
    }

    private void saveData(List<News> arrayList) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("data", MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String data = gson.toJson(arrayList);
        editor.putString("list", data);
        editor.apply();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentNewsListBinding = FragmentNewsListBinding.inflate(inflater, container, false);
        return fragmentNewsListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        String list = sharedPreferences.getString("list", "none");
        if (list != "none") {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<News>>() {
            }.getType();
            List<News> newsList = gson.fromJson(list, listType);

            showList(newsList);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (news != null) {
            saveData(news);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentNewsListBinding = null;
    }

    @Override
    public void onTabSelected(String type) {
        switch (type) {
            case "0":
                this.type = "top";
                break;
            case "1":
                this.type = "yule";
                break;
            case "2":
                this.type = "tiyu";
                break;
            case "3":
                this.type = "junshi";
                break;
        }
        newsPage = new Page(1, 5);
        searchNewsList(newsPage, this.type);

    }


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


    /**
     * 查询新闻列表
     */
    private void searchNewsList(@NonNull Page page, String type) {
        createController();
        newsService.getlistRepos("application/x-www-form-urlencoded", type, page.getPage(), page.getPageSize(), true
                        , "046ff458286b3d73e1d73785a8854c3e").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResult>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull NewsResult newsResult) {
                        List<News> data = newsResult.getResult().getData();
                        news = data;
//                    判断是否是初始化列表
                        if (ifFirst) {
                            Log.e("1", "1");
                            mAdapter.addNews(data);
                        } else {
                            Log.e("2", "2");

                            showList(data);
                        }
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


    /**
     * 初始化列表
     *
     * @param data
     */
    void showList(List<News> data) {
        ifFirst = true;
        mAdapter = new MyAdapter(data);
        fragmentNewsListBinding.newsList.setAdapter(mAdapter);
        jumpToDetail();

//        加载更多
        showMore();
//        刷新
        refreshData();
        // 添加横线动画
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        fragmentNewsListBinding.newsList.addItemDecoration(dividerItemDecoration);
        fragmentNewsListBinding.newsList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 上拉加载
     */
    private void showMore() {
        fragmentNewsListBinding.newsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                获取是否显示最后一行列表
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                assert layoutManager != null;
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newsPage.setPage(newsPage.getPage() + 1);
                            searchNewsList(newsPage, type);
                        }
                    }, 500);
                }
            }
        });
    }

    private void refreshData() {
        fragmentNewsListBinding.listRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<News> newDatas = new ArrayList<>();
                        for (int i = 0; i < 5; i++) {
                            News news = new News("更新数据", "https://ts1.cn.mm.bing.net/th/id/R-C.06c44964bcd38afe6ddec31a5d1eccda?rik=%2fm7F%2f8ZPKGFxJA&riu=http%3a%2f%2fd.hiphotos.baidu.com%2fzhidao%2fpic%2fitem%2f8435e5dde71190efad5c09cecf1b9d16fdfa6078.jpg&ehk=k%2fYLIJpBjz2Lk3QgDuzKlOH98ouUaxJ2B5yWiCr2usc%3d&risl=&pid=ImgRaw&r=0");
                            newDatas.add(news);
                        }
                        mAdapter.updateNews(newDatas);
                        fragmentNewsListBinding.listRefresh.setRefreshing(false);
                        Toast.makeText(getContext(), "更新了五条数据...", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        });

    }

    /**
     * 跳转至至详情页面
     */
    private void jumpToDetail() {
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("uniquekey", mAdapter.getDataList().get(position).getUniquekey());
                startActivity(intent);
            }
        });
    }

    public boolean getIfFirst() {
        return ifFirst;
    }

    public void setIfFirst(boolean ifFirst) {
        this.ifFirst = ifFirst;
    }
}