package com.example.tsnt.view.Banner;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tsnt.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.view.ViewPager.SCROLL_STATE_IDLE;

/**
 * Created by zhangxiaozong on 17/12/24.
 */

public class Banner extends FrameLayout implements ViewPager.OnPageChangeListener {
    private Context context;

    private boolean isAutoPlay = true;

    private int sumOfItem;
    private int sumOfImage;
    private int currPosition;
    private int delayTime = BannerConfig.DEFAULT_DELAY_TIME;
    private int scrollTime = BannerConfig.DEFAULT_SCROLL_TIME;
    private int indicatorWidth = BannerConfig.DEFAULT_INDICATOR_WIDTH;
    private int indicatorSelectedResId = R.drawable.banner_indicator_gray;
    private int indicatorUnselectedResId = R.drawable.banner_indicator_white;

    private List<String> imageUrls;
    private List<ImageView> imageList;
    private List<ImageView> indicatorList;

    private ImageLoader imageLoader;
    private BannerScroller scroller;
    private BannerAdapter bannerAdapter;

    private ViewPager viewPager;
    private LinearLayout indicator;

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = inflate(getContext(), R.layout.layout_banner, this);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        indicator = (LinearLayout) view.findViewById(R.id.indicator);
        initScroller();
    }

    private void initScroller() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            scroller = new BannerScroller(context);
            scroller.setDuration(scrollTime);
            field.set(viewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setImageList(List<String> imageUrls) {
        if (imageUrls == null || imageUrls.size() == 0) return;
        if (imageLoader == null) {
            throw new RuntimeException("ImageLoader is not setted");
        }
        if (imageList == null) {
            imageList = new ArrayList<>();
        } else {
            imageList.clear();
        }
        for (int i = 0; i < imageUrls.size() + 2; i++) {
            ImageView imageView = new ImageView(context);
            if (i == 0) {
                String lastUrl = imageUrls.get(imageUrls.size() - 1);
                imageLoader.loadImage(lastUrl, imageView);
            } else if (i == imageUrls.size() + 1) {
                String firstUrl = imageUrls.get(0);
                imageLoader.loadImage(firstUrl, imageView);
            } else {
                String url = imageUrls.get(i - 1);
                imageLoader.loadImage(url, imageView);
            }
            imageList.add(imageView);
        }
        sumOfItem = imageList.size();
    }

    private void initIndicator() {
        if (indicatorList == null) {
            indicatorList = new ArrayList<>();
        } else {
            indicatorList.clear();
        }
        indicator.removeAllViews();
        if (imageUrls == null || imageUrls.size() == 0) return;
        for (int i = 0; i < imageUrls.size(); i++) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(indicatorWidth, indicatorWidth);
            layoutParams.leftMargin = indicatorWidth;
            layoutParams.rightMargin = indicatorWidth;
            imageView.setLayoutParams(layoutParams);
            if (i == 0) {
                imageView.setImageResource(indicatorSelectedResId);
            } else {
                imageView.setImageResource(indicatorUnselectedResId);
            }
            indicatorList.add(imageView);
            indicator.addView(imageView);
        }
    }

    private void setViewPager() {
        if (bannerAdapter == null) {
            bannerAdapter = new BannerAdapter(imageList);
        }
        viewPager.setAdapter(bannerAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(1);
    }

    public void startAutoPlay() {
        playHandler.sendEmptyMessageDelayed(BannerConfig.AUTO_PLAY, delayTime);
    }

    public void stopAutoPlay() {
        playHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isAutoPlay) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    stopAutoPlay();
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_OUTSIDE:
                    startAutoPlay();
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d(BannerConfig.TAG, "onPageSelected, position = " + position);
        indicatorList.get((currPosition - 1 + sumOfImage) % sumOfImage).setImageResource(indicatorUnselectedResId);
        indicatorList.get((position - 1 + sumOfImage) % sumOfImage).setImageResource(indicatorSelectedResId);
        currPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case SCROLL_STATE_IDLE:
                if (currPosition == 0) {
                    viewPager.setCurrentItem(sumOfItem - 2, false);
                } else if (currPosition == sumOfItem - 1) {
                    viewPager.setCurrentItem(1, false);
                }
                break;
        }
    }

    private PlayHandler playHandler = new PlayHandler();

    class PlayHandler extends android.os.Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(currPosition + 1);
            playHandler.sendEmptyMessageDelayed(BannerConfig.AUTO_PLAY, delayTime);
        }
    }

    //---------- 对外提供的使用方法 ----------

    public Banner setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
        return this;
    }

    public Banner setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        sumOfImage = imageUrls.size();
        return this;
    }

    public Banner setAutoPlay(boolean canAutoPlay) {
        this.isAutoPlay = canAutoPlay;
        return this;
    }

    public void start() {
        setImageList(imageUrls);
        initIndicator();
        setViewPager();
        if (isAutoPlay) startAutoPlay();
    }
}
