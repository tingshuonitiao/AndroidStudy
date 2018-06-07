package com.example.tsnt.view.rotate_textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.tsnt.utils.DisplayUtil;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-05-06 13:56
 * @Description:
 */

public class CustomTextView extends View {

    private String content;
    private Paint paint;
    private Rect textBound;

    private Context context;

    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        // 初始化参数
        content = "CustomTextView";
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(DisplayUtil.dp2px(context, 16));
        // 获得绘制文本的宽和高
        textBound = new Rect();
        paint.getTextBounds(content, 0, content.length(), textBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.makeMeasureSpec(textBound.right - textBound.left, MeasureSpec.EXACTLY);
        int height = MeasureSpec.makeMeasureSpec(textBound.bottom - textBound.top, MeasureSpec.EXACTLY);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(content, 0, textBound.bottom - textBound.top, paint);
    }
}
