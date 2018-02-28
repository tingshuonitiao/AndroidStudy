package com.example.tsnt.view.banner;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tsnt.R;

import java.util.ArrayList;
import java.util.List;

public class BannerTestActivity extends AppCompatActivity {
    private Banner banner;
    private List<String> imageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_test);
        initView();
        loadData();
    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(500);
                imageUrls = new ArrayList<>();
                imageUrls.add(BannerConfig.DEFAULT_IMAGE_URL_1);
                imageUrls.add(BannerConfig.DEFAULT_IMAGE_URL_2);
                imageUrls.add(BannerConfig.DEFAULT_IMAGE_URL_3);
                imageUrls.add(BannerConfig.DEFAULT_IMAGE_URL_4);
                imageUrls.add(BannerConfig.DEFAULT_IMAGE_URL_5);
                onSuccess();
            }
        }).start();
    }

    private void onSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void loadImage(String url, ImageView imageView) {
                        Glide.with(BannerTestActivity.this)
                                .load(url)
                                .centerCrop()
                                .into(imageView);
                    }
                }).setImageUrls(imageUrls)
                        .start();
            }
        });
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, BannerTestActivity.class);
        context.startActivity(intent);
    }
}
