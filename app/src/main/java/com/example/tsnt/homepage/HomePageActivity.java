package com.example.tsnt.homepage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tsnt.R;
import com.example.tsnt.androidbase.intentservice.TestIntentServiceActivity;
import com.example.tsnt.androidbase.service_lifecycle.ServiceActivity;
import com.example.tsnt.bitmap.ImageLoader.ImageLoaderActivity;
import com.example.tsnt.view.AutoFixedLayout.AutoFixedLayout;
import com.example.tsnt.view.AutoFixedLayout.AutoFixedLayoutTestActivity;
import com.example.tsnt.view.AutoLineFeedLayout.AutoLineFeedLayoutTestActivity;
import com.example.tsnt.view.Banner.BannerTestActivity;
import com.example.tsnt.view.Gauge.GaugeActivity;
import com.example.tsnt.view.HorizontalScollview.Demo1Activity;
import com.example.tsnt.view.HorizontalScollview.Demo2Activity;
import com.example.tsnt.view.MaterialDesign.AppBarLayoutTestActivity;
import com.example.tsnt.view.SelfAdaptingViewPager.MainActivity;
import com.example.tsnt.view.base.ViewTestActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-02-19 14:11
 * @Description: 首页
 */

public class HomePageActivity extends AppCompatActivity {

    private Context context;

    private RecyclerView homePageRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = HomePageActivity.this;
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_home_page);
        homePageRecyclerView = (RecyclerView) findViewById(R.id.home_page_recyclerView);
        homePageRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        initRecyclerView();
    }

    private void initRecyclerView() {
        HomePageListAdapter adapter = new HomePageListAdapter(initModuleEntityList());
        homePageRecyclerView.setAdapter(adapter);
    }

    private List<ModuleEntity> initModuleEntityList() {
        List<ModuleEntity> list = new ArrayList<>();
        list.add(initAutoFixedLayout());
        list.add(initAutoLineFeedLayout());
        list.add(initBanner());
        list.add(initViewTest());
        list.add(initGauge());
        list.add(initHorizontalScollview1());
        list.add(initHorizontalScollview2());
        list.add(initMaterialDesign());
        list.add(initSelfAdaptingViewPager());
        list.add(initIntentServicetTest());
        list.add(initServiceLifecyle());
        list.add(initImageLoader());
        return list;
    }

    // ---------- 以下初始化View相关的模块 ----------

    private ModuleEntity initAutoFixedLayout() {
        return new ModuleEntity("AutoFixedLayout", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoFixedLayoutTestActivity.launch(context);
            }
        });
    }

    private ModuleEntity initAutoLineFeedLayout() {
        return new ModuleEntity("AutoLineFeedLayout", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoLineFeedLayoutTestActivity.launch(context);
            }
        });
    }

    private ModuleEntity initBanner() {
        return new ModuleEntity("Banner", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BannerTestActivity.launch(context);
            }
        });
    }

    private ModuleEntity initViewTest() {
        return new ModuleEntity("ViewTest", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewTestActivity.launch(context);
            }
        });
    }

    private ModuleEntity initGauge() {
        return new ModuleEntity("Gauge", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GaugeActivity.launch(context);
            }
        });
    }

    private ModuleEntity initHorizontalScollview1() {
        return new ModuleEntity("HorizontalScollview1", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Demo1Activity.launch(context);
            }
        });
    }

    private ModuleEntity initHorizontalScollview2() {
        return new ModuleEntity("HorizontalScollview2", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Demo2Activity.launch(context);
            }
        });
    }

    private ModuleEntity initMaterialDesign() {
        return new ModuleEntity("MaterialDesign", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppBarLayoutTestActivity.launch(context);
            }
        });
    }

    private ModuleEntity initSelfAdaptingViewPager() {
        return new ModuleEntity("SelfAdaptingViewPager", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.launch(context);
            }
        });
    }

    // ---------- 以下初始化Service相关的模块 ----------

    private ModuleEntity initIntentServicetTest() {
        return new ModuleEntity("IntentServicetTest", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestIntentServiceActivity.launch(context);
            }
        });
    }

    private ModuleEntity initServiceLifecyle() {
        return new ModuleEntity("ServiceLifecyle", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceActivity.launch(context);
            }
        });
    }

    // ---------- 以下初始化ImageLoader模块 ----------

    private ModuleEntity initImageLoader() {
        return new ModuleEntity("ImageLoader", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoaderActivity.launch(context);
            }
        });
    }
}
