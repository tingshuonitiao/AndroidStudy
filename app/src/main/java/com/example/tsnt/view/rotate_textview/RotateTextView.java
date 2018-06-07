package com.example.tsnt.view.rotate_textview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatTextView;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-05-06 11:47
 * @Description:
 */

public class RotateTextView extends AppCompatTextView {
    public RotateTextView(Context context) {
        super(context);
    }

    public RotateTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RotateTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(90, getWidth() / 2, getHeight() / 2);
        super.onDraw(canvas);
    }
}
