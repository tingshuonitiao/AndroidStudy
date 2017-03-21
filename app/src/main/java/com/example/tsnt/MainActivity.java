package com.example.tsnt;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ting说你跳 on 2017/3/11.
 */

public class MainActivity extends AppCompatActivity {

    private TabLayout             mTabLayout;
    private SelfAdaptingViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        List<Fragment> list = new ArrayList<>();
        list.add(new BlueFragment());
        list.add(new RedFragment());
        List<String> titles = new ArrayList<>();
        titles.add("Blue Fragment");
        titles.add("Red Fragment");

        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewpager = (SelfAdaptingViewPager) findViewById(R.id.viewpager);
        mViewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), list, titles));
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewpager.resetHeight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
