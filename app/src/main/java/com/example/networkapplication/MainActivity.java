package com.example.networkapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.example.networkapplication.adapter.MyPagerAdapter;
import com.example.networkapplication.databinding.ActivityMainBinding;
import com.example.networkapplication.view.NewsListFragment;
import com.example.networkapplication.viewmodel.TestClass;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding activityMainBinding;

    List<NewsListFragment> fragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        EdgeToEdge.enable(this);
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        /**
         * 初始化viewPager2
         */
       fragmentList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            fragmentList.add(new NewsListFragment());
        }

        MyPagerAdapter adapter = new MyPagerAdapter(this, fragmentList);
        //禁用预加载
        activityMainBinding.viewPager2.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        activityMainBinding.viewPager2.setAdapter(adapter);
        activityMainBinding.viewPager2.setUserInputEnabled(false);
        divTab();
        setChangeListener();
    }





    /**
     * 设置tab和viewPager的关联文本
     */
    void divTab() {
        String[] newsTypes = {"体育", "八卦", "科技", "娱乐"};
        new TabLayoutMediator(activityMainBinding.tabMode, activityMainBinding.viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                // 获取新闻类型文本
                String newsType = newsTypes[position];
                // 创建并设置TabView
                TextView tabView = new TextView(activityMainBinding.tabMode.getContext());
                tabView.setText(newsType);
                // 将TabView绑定到Tab
                tab.setCustomView(tabView);

            }
        }).attach();
    }


    /**
     * 滑动监听
     */
    void setChangeListener() {
        activityMainBinding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            /**
             * 实现viewPager切换tab字体大小颜色变化
             * @param position Position index of the new selected page.
             */
            @Override
            public void onPageSelected(int position) {

                //获取总的TabLayout的个数
                int tabCount = activityMainBinding.tabMode.getTabCount();

                //遍历选择所有的Tab,如果判断是当前的Tab则进行相关操作，不是则还原操作
                for (int i = 0; i < tabCount; i++) {
                    TabLayout.Tab tab = activityMainBinding.tabMode.getTabAt(i);    //取得tab

                    //通过tab获取tabView
                    TextView tabView = (TextView) tab.getCustomView();

                    if (tab.getPosition() == position) {
                        tabView.setTextSize(20);
                        NewsListFragment fragment = fragmentList.get(position);
                        fragment.onTabSelected(String.valueOf(position));
                        tabView.setTypeface(Typeface.DEFAULT_BOLD);
                        tabView.setTextColor(Color.parseColor("#B22222"));
                    } else {
                        tabView.setTextSize(15);
                        tabView.setTypeface(Typeface.MONOSPACE);
                        tabView.setTextColor(Color.parseColor("#000000"));
                    }
                    super.onPageSelected(position);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


    }


}