package com.example.tsnt.view.horizontal_scollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by ting说你跳 on 2017/3/28.
 */

public class MyHorizontalScrollView1 extends ViewGroup {
    private int mChildCount;
    private int mChildIndex;
    private int mChildWidth;

    private int mLastXIntercept;
    private int mLastYIntercept;
    private int mLastX;
    private int mLastY;

    private Scroller        mScroller;
    private VelocityTracker mVelocityTracker;

    private String TAG = "MyHorizontalScrollView";


    public MyHorizontalScrollView1(Context context) {
        super(context);
        init();
    }

    public MyHorizontalScrollView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyHorizontalScrollView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (mScroller == null) {
            mScroller = new Scroller(getContext());
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mChildCount = getChildCount();
        int resultWidth;
        int resultHeight;
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (mChildCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
                final View childView = getChildAt(0);
                resultWidth = childView.getMeasuredWidth() * mChildCount;
                resultHeight = childView.getMeasuredHeight();
                setMeasuredDimension(resultWidth, resultHeight);
            } else if (heightSpecMode == MeasureSpec.AT_MOST) {
                final View childView = getChildAt(0);
                resultHeight = childView.getMeasuredHeight();
                setMeasuredDimension(widthSpecSize, resultHeight);
            } else if (widthSpecMode == MeasureSpec.AT_MOST) {
                final View childView = getChildAt(0);
                resultWidth = childView.getMeasuredWidth() * mChildCount;
                setMeasuredDimension(resultWidth, heightSpecSize);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        for (int i = 0; i < mChildCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                mChildWidth = childView.getMeasuredWidth();
                childView.layout(childLeft, 0, childLeft + childView.getMeasuredWidth(), childView.getMeasuredHeight());
                childLeft += childView.getMeasuredWidth();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
        }
        mLastX = x;
        mLastY = y;
        mLastXIntercept = x;
        mLastYIntercept = y;
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.d(TAG, "getX()=" + x);
        Log.d(TAG, "getScrollX()=" + getScrollX());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int goalX = getScrollX() + mLastX - x;
                goalX = Math.max(0, Math.min(goalX, mChildWidth * (mChildCount - 1)));
                scrollTo(goalX, 0);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                // TODO: 2017/4/7   I don't know why sometimes the xVelocity is wrong
                Log.d(TAG, "xVelocity=" + xVelocity);
                if (Math.abs(xVelocity) >= 10) {
                    Log.d(TAG, "before childIndex=" + mChildIndex);
                    mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
                    Log.d(TAG, "after childIndex=" + mChildIndex);
                } else {
                    mChildIndex = (getScrollX() + mChildWidth / 2) / mChildWidth;
                }
                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildCount - 1));
                smoothScrollBy(mChildIndex * mChildWidth - getScrollX());
                mVelocityTracker.clear();
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    private void smoothScrollBy(int dx) {
        mScroller.startScroll(getScrollX(), 0, dx, 0, 1000);
        invalidate();
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
