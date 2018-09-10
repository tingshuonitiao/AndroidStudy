package com.example.tsnt.view.countdownview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.example.tsnt.R;

import static com.example.tsnt.view.countdownview.CountDownView.HOUR;
import static com.example.tsnt.view.countdownview.CountDownView.MINUTE;
import static com.example.tsnt.view.countdownview.CountDownView.SECOND;

public class CountDownViewTestActivity extends AppCompatActivity {

    private CountDownView countDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_count_down_view_test);
        countDownView = (CountDownView) findViewById(R.id.count_down_view);
        countDownView.setTime(SystemClock.currentThreadTimeMillis() + 78 * HOUR + 34 * MINUTE + 27 * SECOND);
        countDownView.startCountDown();
    }

    @Override
    protected void onDestroy() {
        countDownView.cancelCountDown();
        super.onDestroy();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, CountDownViewTestActivity.class);
        context.startActivity(intent);
    }
}
