package com.example.tsnt.view.Gauge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by ting说你跳 on 2017/6/15.
 */

public class GaugeView extends ViewGroup {
    public static String TAG = "GaugeView";

    private int mCountOfLines;
    private int mScreenWidth;
    private int mDistance;
    private int mLastInterceptedX;
    private int mLastInterceptedY;
    private int mLastX;
    private int mLastY;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mMaxX;
    private int mStart;
    private int mGap;

    public GaugeView(Context context) {
        super(context);
        init();
    }

    public GaugeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GaugeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mStart = 200;
        int end = 1000;
        mGap = 5;
        mDistance = 40;
        mCountOfLines = (end - mStart) / mGap + 1;
        mMaxX = (mCountOfLines - 1) * mDistance;

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = wm.getDefaultDisplay().getWidth();

        if (mScroller == null) {
            mScroller = new Scroller(getContext());
            mVelocityTracker = VelocityTracker.obtain();
        }

//        addGaugeText();
    }

    //添加刻度线文字
    private void addGaugeText() {
        for (int i = 0; i < mCountOfLines; i++) {
            TextView textView = new TextView(getContext());
            textView.setBackgroundColor(Color.parseColor("#000000"));
            textView.setTextColor(Color.parseColor("#000000"));
            textView.setText("111");
            addView(textView);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            addView(textView,params);
        }
    }

    private void smoothScrollBy(int deltaX) {
        mScroller.startScroll(getScrollX(), 0, deltaX, 0, 1000);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /*int childLeft = mScreenWidth / 2;
        for (int i = 0; i < getChildCount(); i++) {
            Log.d(TAG, "child" + i);
            View child = getChildAt(0);
            Log.d(TAG, "Width=" + child.getMeasuredWidth());
            Log.d(TAG, "Height=" + child.getMeasuredHeight());
            child.layout(childLeft, 0, childLeft + child.getMeasuredWidth(), child.getMeasuredHeight());
            childLeft += 5 * mDistance;
        }*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        //刻度线的数组
        float[] pts = new float[mCountOfLines * 4];
        for (int i = 0; i < mCountOfLines; i++) {
            pts[i * 4] = mScreenWidth / 2 + mDistance * i;
            pts[i * 4 + 1] = 200;
            pts[i * 4 + 2] = mScreenWidth / 2 + mDistance * i;
            if (i % 5 == 0) {
                pts[i * 4 + 3] = 125;
            } else {
                pts[i * 4 + 3] = 150;
            }
        }
        canvas.drawLines(pts, paint);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int Y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastInterceptedX;
                int deltaY = Y - mLastInterceptedY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = Y;
        mLastInterceptedX = x;
        mLastInterceptedY = Y;
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int Y = (int) event.getY();
        int goalX;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                goalX = (getScrollX() + mLastX - x);
                goalX = Math.max(0, Math.min(goalX, mMaxX));
                scrollTo(goalX, 0);
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(100, 2000);
                float xVelocity = mVelocityTracker.getXVelocity();
                Log.d(TAG, "xVelocity=" + xVelocity);
                if (Math.abs(xVelocity) > 10) {
                    goalX = getScrollX() - (int) xVelocity;
                    goalX = Math.max(0, Math.min(goalX, mMaxX));
                    smoothScrollBy(goalX - getScrollX());
                }
                mVelocityTracker.clear();
                break;
        }
        mLastX = x;
        mLastY = Y;
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }
}
