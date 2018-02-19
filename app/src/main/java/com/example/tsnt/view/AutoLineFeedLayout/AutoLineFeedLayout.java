package com.example.tsnt.view.AutoLineFeedLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-01-28 21:54
 * @Description: 自动换行的布局
 */

public class AutoLineFeedLayout extends ViewGroup {

    private List<List<View>> allViewLists = new ArrayList<>();

    public AutoLineFeedLayout(Context context) {
        this(context, null);
    }

    public AutoLineFeedLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLineFeedLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取父布局的参数
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        // 得到每行的剩余空间
        int leftWidth = parentWidth - paddingLeft - paddingRight;
        // 设置第一行的
        // 遍历子View
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int measuredWidth = childView.getMeasuredWidth();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
