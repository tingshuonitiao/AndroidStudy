package com.example.tsnt.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tsnt.R;
import com.example.tsnt.android_base.intentservice.TestIntentServiceActivity;
import com.example.tsnt.android_base.service_lifecycle.ServiceActivity;
import com.example.tsnt.arouter.ARouterTest1Activity;
import com.example.tsnt.bitmap.ImageLoader.ImageLoaderActivity;
import com.example.tsnt.eventbus.EventBusAActivity;
import com.example.tsnt.mvvm.TestActivity;
import com.example.tsnt.recyclerview.RecyclerViewMainActivity;
import com.example.tsnt.recyclerview.RecyclerViewTest1Activity;
import com.example.tsnt.view.auto_fixed_layout.AutoFixedLayoutTestActivity;
import com.example.tsnt.view.auto_line_feed_layout.AutoLineFeedLayoutTestActivity;
import com.example.tsnt.view.banner.BannerTestActivity;
import com.example.tsnt.view.base.ViewTestActivity;
import com.example.tsnt.view.center_pop_view.CenterPopDialog;
import com.example.tsnt.view.circle_imageview.CircleImageViewTestActivity;
import com.example.tsnt.view.circle_progress_view.CircleProgressViewActivity;
import com.example.tsnt.view.gauge.GaugeActivity;
import com.example.tsnt.view.generate_poster.GeneratePosterActivity;
import com.example.tsnt.view.gluttonous_snake.GluttonousSnakeActivity;
import com.example.tsnt.view.horizontal_percent_view.HorizontalPercentViewActivity;
import com.example.tsnt.view.horizontal_scollview.Demo1Activity;
import com.example.tsnt.view.horizontal_scollview.Demo2Activity;
import com.example.tsnt.view.limit_scroll_edittext.LimitScrollEditTextActivity;
import com.example.tsnt.view.material_design.AppBarLayoutTestActivity;
import com.example.tsnt.view.rotate_textview.RotateTextView;
import com.example.tsnt.view.rotate_textview.RotateTextViewActivity;
import com.example.tsnt.view.self_adapting_view_pager.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tingshuonitiao
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
        initRecyclerView();
    }

    private void initRecyclerView() {
        homePageRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        HomePageListAdapter adapter = new HomePageListAdapter(initModuleEntityList());
        homePageRecyclerView.setAdapter(adapter);
    }

    private List<ModuleEntity> initModuleEntityList() {
        List<ModuleEntity> list = new ArrayList<>();
        list.add(initAutoFixedLayout());
        list.add(initAutoLineFeedLayout());
        list.add(initBanner());
        list.add(initViewTest());
        list.add(initCenterPopView());
        list.add(initCircleImageView());
        list.add(initCircleProgressView());
        list.add(initGauge());
        list.add(initGeneratePoster());
        list.add(initGluttonousSnake());
        list.add(initHorizontalPercentView());
        list.add(initHorizontalScollview1());
        list.add(initHorizontalScollview2());
        list.add(initLimitScrollEditText());
        list.add(initMaterialDesign());
        list.add(initRotateTextView());
        list.add(initSelfAdaptingViewPager());
        list.add(initIntentServicetTest());
        list.add(initServiceLifecyle());
        list.add(initImageLoader());
        list.add(initMVVM());
        list.add(initEventBus());
        list.add(initRecyclerViewModel());
        list.add(initARouter());
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

    private ModuleEntity initCenterPopView() {
        return new ModuleEntity("CenterPopView", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CenterPopDialog centerPopDialog = new CenterPopDialog();
                centerPopDialog.show(HomePageActivity.this.getFragmentManager(), "CenterPopView");
            }
        });
    }

    private ModuleEntity initCircleImageView() {
        return new ModuleEntity("CircleImageView", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleImageViewTestActivity.launch(context);
            }
        });
    }

    private ModuleEntity initCircleProgressView() {
        return new ModuleEntity("CircleProgressView", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleProgressViewActivity.launch(context);
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

    private ModuleEntity initGeneratePoster() {
        return new ModuleEntity("GeneratePoster", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneratePosterActivity.launch(context);
            }
        });
    }

    private ModuleEntity initGluttonousSnake() {
        return new ModuleEntity("GluttonousSnake", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GluttonousSnakeActivity.launch(context);
            }
        });
    }

    private ModuleEntity initHorizontalPercentView() {
        return new ModuleEntity("HorizontalPercentView", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HorizontalPercentViewActivity.launch(context);
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

    private ModuleEntity initLimitScrollEditText() {
        return new ModuleEntity("LimitScrollEditText", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LimitScrollEditTextActivity.launch(context);
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

    private ModuleEntity initRotateTextView() {
        return new ModuleEntity("RotateTextView", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateTextViewActivity.launch(context);
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

    // ---------- 以下初始化MVVM模块 ----------

    private ModuleEntity initMVVM() {
        return new ModuleEntity("MVVM", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestActivity.launch(context);
            }
        });
    }

    // ---------- 以下初始化EventBus模块 ----------

    private ModuleEntity initEventBus() {
        return new ModuleEntity("EventBus", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusAActivity.launch(context);
            }
        });
    }

    // ---------- 以下初始化RecyclerView模块 ----------

    private ModuleEntity initRecyclerViewModel() {
        return new ModuleEntity("RecyclerView", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewMainActivity.launch(context);
            }
        });
    }

    // ---------- 以下初始化ARouter模块 ----------
    private ModuleEntity initARouter() {
        return new ModuleEntity("ARouter", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouterTest1Activity.launch(context);
            }
        });
    }
}
