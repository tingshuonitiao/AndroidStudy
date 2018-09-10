package com.example.tsnt.view.countdownview;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.tsnt.R;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-09-10 15:29
 * @Description:
 */

public class CountDownView extends FrameLayout {

    public static final long SECOND = 1000;
    public static final long MINUTE = 60 * SECOND;
    public static final long HOUR = 60 * MINUTE;

    public static final String TAG = CountDownView.class.getSimpleName();

    private long deadline;

    private TextView hourNum1Tv;
    private TextView hourNum2Tv;
    private TextView minNum1Tv;
    private TextView minNum2Tv;
    private TextView secNum1Tv;
    private TextView secNum2Tv;

    private CountDownTimer countDownTimer;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.layout_count_down, this);
        hourNum1Tv = (TextView) findViewById(R.id.hour_num1_tv);
        hourNum2Tv = (TextView) findViewById(R.id.hour_num2_tv);
        minNum1Tv = (TextView) findViewById(R.id.min_num1_tv);
        minNum2Tv = (TextView) findViewById(R.id.min_num2_tv);
        secNum1Tv = (TextView) findViewById(R.id.sec_num1_tv);
        secNum2Tv = (TextView) findViewById(R.id.sec_num2_tv);
    }

    /**
     * 设置截止时间
     *
     * @param deadline
     */
    public void setTime(long deadline) {
        this.deadline = deadline;
    }

    /**
     * 更新时间显示
     *
     * @param time
     */
    private void updateTime(long time) {
        long hour = time / HOUR;
        long min = time % HOUR / MINUTE;
        long sec = time % HOUR % MINUTE / SECOND;
        if (hour >= 100) {
            hour = 99;
        }
        hourNum1Tv.setText(hour / 10 + "");
        hourNum2Tv.setText(hour % 10 + "");
        minNum1Tv.setText(min / 10 + "");
        minNum2Tv.setText(min % 10 + "");
        secNum1Tv.setText(sec / 10 + "");
        secNum2Tv.setText(sec % 10 + "");
    }

    /**
     * 开始倒计时
     */
    public void startCountDown() {
        // 剩余时间
        long leftTime = deadline - SystemClock.currentThreadTimeMillis();
        if (leftTime < 1000) return;
        countDownTimer = new CountDownTimer(leftTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "----- onTick() -----");
                updateTime(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "----- onFinish() -----");
            }
        };
        countDownTimer.start();
    }

    /**
     * 结束倒计时
     */
    public void cancelCountDown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
