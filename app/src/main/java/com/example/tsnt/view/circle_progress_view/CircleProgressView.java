package com.example.tsnt.view.circle_progress_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.tsnt.utils.NumberFormatUtil;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-14 12:04
 * @Description: 请控制CircleProgressView的长宽比为3:2
 */

public class CircleProgressView extends View {

    private int width;

    private int height;

    private double mProgress = 0;

    private double mMaxProgress = 100;

    private int mRoundBackgroundColor = Color.parseColor("#EEEEEE");

    private int mRoundProgressColor = Color.RED;

    private int mTxtColor = Color.BLACK;

    private int mCircleLineStrokeWidth = 10;

    private int mCenterTxtStrokeWidth = 2;

    private int mBottomTxtStrokeWidth = 8;

    private String mUnit = "";

    private String mBottomText = "";

    private RectF mBottomeRectF;

    private RectF mTopRectF;

    private Paint mPaint;

    private Context mContext;

    private Typeface dingTypeFace;

    public CircleProgressView(Context context) {
        super(context);
        initView(context);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        mTopRectF = new RectF();
        mBottomeRectF = new RectF();
        mPaint = new Paint();
        dingTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/DIN-Regular.ttf");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = this.getWidth();
        height = this.getHeight();

        if (mMaxProgress == 0) {
            drawWithNotarget(canvas);
        } else {
            drawWithTarget(canvas);
        }
    }

    // 已设置目标时
    private void drawWithTarget(Canvas canvas) {
        // 设置画笔属性
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTypeface(Typeface.DEFAULT);

        // 设置mTopRectF的位置
        mTopRectF.left = mCircleLineStrokeWidth / 2;
        mTopRectF.top = mCircleLineStrokeWidth / 2;
        mTopRectF.right = width - mCircleLineStrokeWidth / 2;
        float heightOfTopRectF = 2 / 3f * height;
        mTopRectF.bottom = heightOfTopRectF - mCircleLineStrokeWidth / 2;

        // 绘制进度圈背景
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setColor(mRoundBackgroundColor);
        canvas.drawArc(mTopRectF, -90, 360, false, mPaint);

        // 绘制进度圈进度
        mPaint.setColor(mRoundProgressColor);
        canvas.drawArc(mTopRectF, -90, (float) ((mProgress / mMaxProgress) * 360), false, mPaint);

        // 绘制进度圈中文字
        mPaint.setColor(mTxtColor);
        mPaint.setStrokeWidth(mCenterTxtStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);

        String textOfProgress = NumberFormatUtil.reserveDecimalNotEndWithZero(mProgress);
        float textHeight1 = height / 6f;
        mPaint.setTextSize(textHeight1);
        mPaint.setTypeface(dingTypeFace);
        float textWidth1 = mPaint.measureText(textOfProgress, 0, textOfProgress.length());

        String textOfMaxProgress = "目标" + NumberFormatUtil.notReserveDecimal(mMaxProgress) + mUnit;
        float textHeight2 = height / 9f;
        mPaint.setTextSize(textHeight2);
        mPaint.setTypeface(Typeface.DEFAULT);
        float textWidth2 = mPaint.measureText(textOfMaxProgress, 0, textOfMaxProgress.length());

        mPaint.setTextSize(textHeight1);
        mPaint.setTypeface(dingTypeFace);
        canvas.drawText(textOfProgress, width / 2 - textWidth1 / 2, heightOfTopRectF / 2 - 10, mPaint);

        mPaint.setTextSize(textHeight2);
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setColor(Color.parseColor("#999999"));
        canvas.drawLine((float) 2.5 * height / 9f, height / 3, (float) 3.5 * height / 9f, height / 3, mPaint);
        canvas.drawText(textOfMaxProgress, width / 2 - textWidth2 / 2, heightOfTopRectF / 2 + textHeight2, mPaint);

        // 设置mBottomeRectF的位置
        mBottomeRectF.left = 0;
        mBottomeRectF.top = heightOfTopRectF;
        mBottomeRectF.right = width;
        mBottomeRectF.bottom = height;

        // 绘制底部文字
        mPaint.setStrokeWidth(mBottomTxtStrokeWidth);
        float textHeight3 = height / 6f;
        mPaint.setTextSize(textHeight3);
        mPaint.setColor(mTxtColor);
        float textWidth3 = mPaint.measureText(mBottomText, 0, mBottomText.length());
        canvas.drawText(mBottomText, width / 2 - textWidth3 / 2, 8 / 9f * height, mPaint);
    }

    // 未设置目标时
    private void drawWithNotarget(Canvas canvas) {
        // 设置画笔属性
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTypeface(Typeface.DEFAULT);

        // 设置mTopRectF的位置
        mTopRectF.left = mCircleLineStrokeWidth / 2;
        mTopRectF.top = mCircleLineStrokeWidth / 2;
        mTopRectF.right = width - mCircleLineStrokeWidth / 2;
        float heightOfTopRectF = 2 / 3f * height;
        mTopRectF.bottom = heightOfTopRectF - mCircleLineStrokeWidth / 2;

        // 绘制进度圈背景
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setColor(mRoundBackgroundColor);
        canvas.drawArc(mTopRectF, -90, 360, false, mPaint);

        // 绘制进度圈进度
        mPaint.setColor(mRoundProgressColor);
        if (mProgress == 0) {
            canvas.drawArc(mTopRectF, -90, 0, false, mPaint);
        } else {
            canvas.drawArc(mTopRectF, -90, 360, false, mPaint);
        }

        // 绘制进度圈中文字
        mPaint.setColor(mTxtColor);
        mPaint.setStrokeWidth(mCenterTxtStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);

        String textOfProgress = NumberFormatUtil.reserveDecimalNotEndWithZero(mProgress);
        float textHeight1 = height / 6f;
        mPaint.setTextSize(textHeight1);
        mPaint.setTypeface(dingTypeFace);
        float textWidth1 = mPaint.measureText(textOfProgress, 0, textOfProgress.length());

        String textOfUnit = mUnit;
        float textHeight2 = height / 9f;
        mPaint.setTextSize(textHeight2);
        mPaint.setTypeface(Typeface.DEFAULT);
        float textWidth2 = mPaint.measureText(textOfUnit, 0, textOfUnit.length());

        mPaint.setTextSize(textHeight1);
        mPaint.setTypeface(dingTypeFace);
        canvas.drawText(textOfProgress, width / 2 - (textWidth1 + textWidth2) / 2, heightOfTopRectF / 2 + textHeight2 / 2, mPaint);

        mPaint.setTextSize(textHeight2);
        mPaint.setTypeface(Typeface.DEFAULT);
        canvas.drawText(textOfUnit, width / 2 + (textWidth1 - textWidth2) / 2, heightOfTopRectF / 2 + textHeight2 / 2, mPaint);

        // 设置mBottomeRectF的位置
        mBottomeRectF.left = 0;
        mBottomeRectF.top = heightOfTopRectF;
        mBottomeRectF.right = width;
        mBottomeRectF.bottom = height;

        // 绘制底部文字
        mPaint.setStrokeWidth(mBottomTxtStrokeWidth);
        float textHeight3 = height / 6f;
        mPaint.setTextSize(textHeight3);
        float textWidth3 = mPaint.measureText(mBottomText, 0, mBottomText.length());
        canvas.drawText(mBottomText, width / 2 - textWidth3 / 2, 8 / 9f * height, mPaint);
    }

    // 以下是暴露给外部的方法

    public void setProgress(double progress) {
        this.mProgress = progress;
        postInvalidate();
    }

    public void setMaxProgress(double maxProgress) {
        this.mMaxProgress = maxProgress;
        postInvalidate();
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.mRoundProgressColor = roundProgressColor;
        postInvalidate();
    }

    public void setRoundBackgroundColor(int roundBackgroundColor) {
        this.mRoundBackgroundColor = roundBackgroundColor;
        postInvalidate();
    }

    public void setCircleLineStrokeWidth(int circleLineStrokeWidth) {
        this.mCircleLineStrokeWidth = circleLineStrokeWidth;
        postInvalidate();
    }

    public void setTxtStrokeWidth(int txtStrokeWidth) {
        this.mCenterTxtStrokeWidth = txtStrokeWidth;
        postInvalidate();
    }

    public void setUnit(String unit) {
        this.mUnit = unit;
        postInvalidate();
    }

    public void setBottomText(String bottomText) {
        this.mBottomText = bottomText;
        postInvalidate();
    }

    public void setBottomTxtStrokeWidth(int bottomTxtStrokeWidth) {
        this.mBottomTxtStrokeWidth = bottomTxtStrokeWidth;
        postInvalidate();
    }
}
