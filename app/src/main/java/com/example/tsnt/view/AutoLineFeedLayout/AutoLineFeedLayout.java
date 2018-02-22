package com.example.tsnt.view.AutoLineFeedLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-01-28 21:54
 * @Description: 自动换行的布局
 */

public class AutoLineFeedLayout extends ViewGroup {

    // 属性
    private static final int[] LAYOUT_ATTRS = {
            android.R.attr.horizontalSpacing,
            android.R.attr.verticalSpacing,
    };

    // 每行的高度
    private int rowHeight;

    // 子View之间水平间隔
    private int horizontalSpacing;

    // 子View之间竖直间隔
    private int verticalSpacing;

    // 所有行子View的集合
    private List<List<View>> allViewLists;

    public AutoLineFeedLayout(Context context) {
        this(context, null);
    }

    public AutoLineFeedLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLineFeedLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs, LAYOUT_ATTRS);
            horizontalSpacing = a.getDimensionPixelOffset(0, 0);
            verticalSpacing = a.getDimensionPixelOffset(1, 0);
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 测量所有子View
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        // 得到每行的剩余空间
        int width = MeasureSpec.getSize(widthMeasureSpec)
                - getPaddingLeft()
                - getPaddingRight();
        // 所有行子View的集合
        allViewLists = new ArrayList<>();
        // 当前行子View的集合
        List<View> currList = createNewList();
        // 容器的总宽
        int sumWidth = getPaddingLeft() + getPaddingRight();
        // 当前行剩余的宽度
        int leftWidth = width;
        // 遍历子View
        for (int i = 0; i < getChildCount(); ) {
            View childView = getChildAt(i);
            int measuredWidth = childView.getMeasuredWidth();
            if (currList.size() == 0) {
                // 如果是当前行没有子view
                currList.add(childView);
                leftWidth -= measuredWidth;
                sumWidth += measuredWidth;
                i++;
            } else if (leftWidth >= horizontalSpacing + measuredWidth) {
                // 如果是当前行有子view并且可以容纳新的子View
                currList.add(childView);
                leftWidth -= horizontalSpacing + measuredWidth;
                sumWidth += horizontalSpacing + measuredWidth;
                i++;
            } else {
                // 如果是当前行有子view并且无法容纳新的子View，则换到新的一行
                currList = createNewList();
                leftWidth = width;
            }
            rowHeight = Math.max(rowHeight, childView.getMeasuredHeight());
        }
        // 容器的总高
        int sumHeight = getPaddingTop() + getPaddingBottom();
        if (allViewLists.size() > 0) {
            sumHeight += allViewLists.size() * rowHeight;
            sumHeight += (allViewLists.size() - 1) * verticalSpacing;
        }
        // 测量之后的宽度
        int measureWidth = 0;
        // 测量之后的高度
        int measureHeight = 0;
        // 根据LayoutParams设置宽度
        if (getLayoutParams().width == LayoutParams.MATCH_PARENT) {
            measureWidth = widthMeasureSpec;
        } else {
            measureWidth = MeasureSpec.makeMeasureSpec(sumWidth, MeasureSpec.EXACTLY);
        }
        // 根据LayoutParams设置高度
        if (getLayoutParams().height == LayoutParams.MATCH_PARENT) {
            measureHeight = heightMeasureSpec;
        } else {
            measureHeight = MeasureSpec.makeMeasureSpec(sumHeight, MeasureSpec.EXACTLY);
        }
        // 设置测量宽高
        setMeasuredDimension(measureWidth, measureHeight);
    }

    /**
     * 创建一行子View的集合
     */
    private List createNewList() {
        List list = new ArrayList();
        allViewLists.add(list);
        return list;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 子View左边到父布局左边的距离
        int leftDistance;
        // 子View上边到父布局上边的距离
        int topDistance = getPaddingTop();
        for (int i = 0; i < allViewLists.size(); i++) {
            // 布局每一行
            List<View> curr = allViewLists.get(i);
            leftDistance = getPaddingLeft();
            for (int j = 0; j < curr.size(); j++) {
                // 布局每一行的每一个子View
                View childView = curr.get(j);
                childView.layout(leftDistance,
                        topDistance,
                        leftDistance + childView.getMeasuredWidth(),
                        topDistance + childView.getMeasuredHeight());
                leftDistance += horizontalSpacing + childView.getMeasuredWidth();
            }
            topDistance += verticalSpacing + rowHeight;
        }
    }
}
