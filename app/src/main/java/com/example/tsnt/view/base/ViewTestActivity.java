package com.example.tsnt.view.base;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tsnt.R;

/**
 * Created by ting说你跳 on 2017/5/6.
 */

public class ViewTestActivity extends Activity implements View.OnClickListener {
    private String TAG = "ViewTestActivity";

    private View mSlider;
    private View mScrolltoBtn;
    private View mLayoutBtn;
    private View mTranslateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtest);
        initView();
    }

    private void initView() {
        mSlider = findViewById(R.id.slider);
        mScrolltoBtn = findViewById(R.id.scrollto);
        mLayoutBtn = findViewById(R.id.layout);
        mTranslateBtn = findViewById(R.id.translate);

        mScrolltoBtn.setOnClickListener(this);
        mLayoutBtn.setOnClickListener(this);
        mTranslateBtn.setOnClickListener(this);
        mSlider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scrollto:
                mSlider.scrollBy(0, 10);
                break;
            case R.id.layout:
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSlider.getLayoutParams();
                params.bottomMargin += 10;
                mSlider.requestLayout();
                break;
            case R.id.translate:
                ObjectAnimator translationY = ObjectAnimator.ofFloat(mSlider, "translationY", -500);
                translationY.start();
                break;
            case R.id.slider:
                Toast.makeText(ViewTestActivity.this, "I'm clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        Log.d(TAG, "top=" + mSlider.getTop());
        Log.d(TAG, "y=" + mSlider.getY());
        Log.d(TAG, "translationY=" + mSlider.getTranslationY());
        Log.d(TAG, "scrollY=" + mSlider.getScrollY());
        Log.d(TAG, "--------------------------------------------");
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, ViewTestActivity.class);
        context.startActivity(intent);
    }
}
