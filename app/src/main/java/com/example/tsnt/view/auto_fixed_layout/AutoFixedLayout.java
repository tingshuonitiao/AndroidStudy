package com.example.tsnt.view.auto_fixed_layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by tingshuonitiao on 18/1/20.
 */

public class AutoFixedLayout extends LinearLayout {
    public AutoFixedLayout(Context context) {
        super(context);
    }

    public AutoFixedLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoFixedLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 检查子控件的个数
        int childCount = getChildCount();
        if (childCount != 2) return;

        // 获取容器的宽度
        int containerWidth = MeasureSpec.getSize(widthMeasureSpec);

        // 获取两个子控件
        View unsizedView = getChildAt(0);
        View fixedSizeView = getChildAt(1);

        // 获取固定子控件的宽度
        // 这里做了特殊处理，是因为LinearLayout的源码中onMeasure()有如下操作：
        // Determine how big this child would like to be. If this or
        // previous children have given a weight, then we allow it to
        // use all available space (and we will shrink things later
        // if needed).
        fixedSizeView.measure(MeasureSpec.makeMeasureSpec(containerWidth, MeasureSpec.AT_MOST),
                heightMeasureSpec);
        int fixedSizeWidth = fixedSizeView.getMeasuredWidth();

        // 调整不定长度子控件的宽度
        if (fixedSizeWidth != 0) {
            int unsizedWidth = unsizedView.getMeasuredWidth();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) unsizedView.getLayoutParams();
            int leftMargin = layoutParams.leftMargin;
            int rightMargin = layoutParams.rightMargin;
            unsizedWidth = Math.min(unsizedWidth, containerWidth - fixedSizeWidth - leftMargin - rightMargin);
            unsizedView.measure(MeasureSpec.makeMeasureSpec(unsizedWidth, MeasureSpec.EXACTLY),
                    heightMeasureSpec);
        }
    }
}
