package com.example.tsnt.view.SelfAdaptingViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by ting说你跳 on 2017/3/11.
 */

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mList;
    private List<String> mTitles;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        mList = list;
        mTitles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
