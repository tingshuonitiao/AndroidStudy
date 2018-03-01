package com.example.tsnt.view.horizontal_percent_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-02-28 16:25
 * @Description: 横向百分比布局
 */

public class HorizontalPercentView extends View {

    // view的宽度
    private int width;
    // view的高度
    private int height;
    // 百分比
    private double percent = 0.7;
    // 边框宽度
    private float strokeWidth = 1;

    // 背景画笔
    private Paint backgroundPaint;
    // 百分比背景画笔
    private Paint percentBackgroundPaint;
    // 边框画笔
    private Paint strokePaint;
    // 百分比文字画笔
    private Paint percentTextPaint;

    public HorizontalPercentView(Context context) {
        this(context, null);
    }

    public HorizontalPercentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalPercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        // 初始化背景画笔
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.parseColor("#ffffff"));
        backgroundPaint.setStrokeWidth(height);
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setAntiAlias(true);
        // 使图形两头成半圆形
        backgroundPaint.setStrokeCap(Paint.Cap.ROUND);

        // 初始化百分比背景画笔
        percentBackgroundPaint = new Paint();
        percentBackgroundPaint.setColor(Color.parseColor("#22ff6340"));
        percentBackgroundPaint.setStrokeWidth(height);
        percentBackgroundPaint.setStyle(Paint.Style.FILL);
        percentBackgroundPaint.setAntiAlias(true);
        // 使图形两头成半圆形
        percentBackgroundPaint.setStrokeCap(Paint.Cap.ROUND);

        // 初始化边框画笔
        strokePaint = new Paint();
        strokePaint.setColor(Color.parseColor("#ff6340"));
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setAntiAlias(true);

        // 初始化边框画笔
        percentTextPaint = new Paint();
        percentTextPaint.setColor(Color.parseColor("#ff6340"));
        percentTextPaint.setStrokeWidth(strokeWidth);
        percentTextPaint.setStyle(Paint.Style.FILL);
        percentTextPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawPercentBackground(canvas);
        drawStroke(canvas);
        drawPercentText(canvas);
    }

    // 画背景
    private void drawBackground(Canvas canvas) {
        // 重新设置画笔宽度
        backgroundPaint.setStrokeWidth(height);
        canvas.drawLine(height / 2, height / 2, width - height / 2, height / 2, backgroundPaint);
    }

    // 画百分比背景
    private void drawPercentBackground(Canvas canvas) {
        // 重新设置画笔宽度
        percentBackgroundPaint.setStrokeWidth(height);
        float deltaX = (float) (percent * (width - height));
        canvas.drawLine(height / 2, height / 2, height / 2 + deltaX, height / 2, percentBackgroundPaint);
    }


    // 画边框
    private void drawStroke(Canvas canvas) {
        // 画左部半圆边框
        RectF leftSemicircle = new RectF(strokeWidth / 2, strokeWidth / 2, height - strokeWidth / 2, height - strokeWidth / 2);
        canvas.drawArc(leftSemicircle, 90, 180, false, strokePaint);
        // 画右部半圆边框
        RectF rightSemicircle = new RectF(width - height + strokeWidth / 2, strokeWidth / 2, width - strokeWidth / 2, height - strokeWidth / 2);
        canvas.drawArc(rightSemicircle, 270, 180, false, strokePaint);
        // 画上部直线边框
        canvas.drawLine(height / 2 - strokeWidth / 2, strokeWidth / 2, width - height / 2 + strokeWidth / 2, strokeWidth / 2, strokePaint);
        // 画下部直线边框
        canvas.drawLine(height / 2 - strokeWidth / 2, height - strokeWidth / 2, width - height / 2 + strokeWidth / 2, height - strokeWidth / 2, strokePaint);
    }

    // 画百分比文字
    private void drawPercentText(Canvas canvas) {
        int textSize = 2 * height / 3;
        percentTextPaint.setTextSize(textSize);
        String percentText = (int) (percent * 100) + "%";
        Rect rect = new Rect();
        percentTextPaint.getTextBounds(percentText, 0, percentText.length(), rect);
        float x = width / 2 - rect.width() / 2;
        float y = height / 2 + rect.height() / 2;
        canvas.drawText(percentText, x, y, percentTextPaint);
    }

    // 设置百分比
    public void setPercent(double percent) {
        this.percent = percent;
        postInvalidate();
    }
}
