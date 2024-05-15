package com.example.networkapplication;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.networkapplication.model.NewsDetailResult;
import com.example.networkapplication.databinding.ActivityDetailBinding;
import com.example.networkapplication.entity.Detail;
import com.example.networkapplication.service.NewsService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding activityDetailBinding;
    /**
     * 请求对象
     */
    Retrofit retrofit2;


    /**
     * 新闻服务类
     */
    NewsService newsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = activityDetailBinding.getRoot();
        EdgeToEdge.enable(this);
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Bundle bundle = getIntent().getExtras();
        /**
         * 新闻id
         */
        String uniquekey = bundle.getString("uniquekey");
        searchDetail(uniquekey);

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
     * 查询新闻详情
     */
    private void searchDetail(String uniquekey) {
        createController();


        newsService.searchDetailByUniquekey("application/x-www-form-urlencoded", uniquekey
                        , "046ff458286b3d73e1d73785a8854c3e")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDetailResult>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull NewsDetailResult newsDetailResult) {

                        Detail detail = newsDetailResult.getResult().getDetail();
                        activityDetailBinding.title.setText(detail.getTitle());
                        activityDetailBinding.author.setText("作者:" + detail.getAuthor_name());
                        activityDetailBinding.content.setText(Html.fromHtml(newsDetailResult.getResult().getContent()));
                        activityDetailBinding.date.setText(detail.getDate());
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        throw new RuntimeException("请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}