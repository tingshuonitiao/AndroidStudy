package com.example.tsnt.view.horizontal_scollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by ting说你跳 on 2017/4/9.
 */

public class MyListView extends ListView {
    private String TAG = "MyListView";
    private MyHorizontalScrollView2 mMyHorizontalScrollView2;
    private int                     mLastX;
    private int                     mLastY;

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMyHorizontalScrollView2(MyHorizontalScrollView2 myHorizontalScrollView2) {
        mMyHorizontalScrollView2 = myHorizontalScrollView2;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent");
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMyHorizontalScrollView2.requestDisallowInterceptTouchEvent(true);
                Log.d(TAG, "MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    mMyHorizontalScrollView2.requestDisallowInterceptTouchEvent(false);
                }
                Log.d(TAG, "MotionEvent.ACTION_MOVE");
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }

    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent");
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMyHorizontalScrollView2.requestDisallowInterceptTouchEvent(true);
                Log.d(TAG, "MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
            // TODO: 2017/4/9 why it can not receive MotionEvent.ACTION_MOVE
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    mMyHorizontalScrollView2.requestDisallowInterceptTouchEvent(false);
                }
                Log.d(TAG, "MotionEvent.ACTION_MOVE");
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.onInterceptTouchEvent(ev);
    }*/
}
