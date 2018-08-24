package com.example.tsnt.view.circle_progress_view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.tsnt.R;

public class CircleProgressViewActivity extends AppCompatActivity {

    private CircleProgressView yejiProgress;
    private CircleProgressView shishouProgress;
    private CircleProgressView daikanProgress;
    private CircleProgressView1 scoreProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (scoreProgress != null) {
            scoreProgress.releaseResource();
        }
    }

    private void initView() {
        setContentView(R.layout.activity_circle_progress_view);
        yejiProgress = (CircleProgressView) findViewById(R.id.yeji_progress);
        daikanProgress = (CircleProgressView) findViewById(R.id.daikan_progress);
        shishouProgress = (CircleProgressView) findViewById(R.id.shishou_progress);
        scoreProgress = (CircleProgressView1) findViewById(R.id.score_progress);
    }

    private void initData() {
        // 设置业绩
        yejiProgress.setUnit("万");
        yejiProgress.setBottomText("本年业绩");
        yejiProgress.setRoundProgressColor(Color.parseColor("#FC596B"));
        yejiProgress.setProgress(51.10d);
        yejiProgress.setMaxProgress(100d);
        // 设置开单
        daikanProgress.setUnit("单");
        daikanProgress.setBottomText("本年开单");
        daikanProgress.setRoundProgressColor(Color.parseColor("#FFA54C"));
        daikanProgress.setProgress(0.12d);
        daikanProgress.setMaxProgress(1d);
        // 设置实收
        shishouProgress.setUnit("万");
        shishouProgress.setBottomText("本年实收");
        shishouProgress.setRoundProgressColor(Color.parseColor("#6F9DFD"));
        shishouProgress.setProgress(100d);
        shishouProgress.setMaxProgress(0);
        // 设置信用分
        scoreProgress.setScore(725);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, CircleProgressViewActivity.class);
        context.startActivity(intent);
    }
}
