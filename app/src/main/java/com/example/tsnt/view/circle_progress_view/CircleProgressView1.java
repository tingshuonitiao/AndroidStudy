package com.example.tsnt.view.circle_progress_view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.tsnt.utils.DisplayUtil;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-08-22 14:52
 * @Description:
 */

public class CircleProgressView1 extends View {

    public static final String[] PROGRESS_TEXT_ARRAY = {"0", "较差", "75", "普通", "280", "良好", "600", "优秀", "850", "极佳", "1000"};
    public static final float[] PROGRESS_SCORE_ARRAY = {0, 37.5f, 75, 177.5f, 280, 440, 600, 725, 850, 925, 1000};

    public static final int PROGRESS_STROKE_DP = 12;
    public static final int PROGRESS_DIAMETER_DP = 234;
    public static final int PROGRESS_TEXT_SIZE_DP = 10;
    public static final int TEXT_BASELINE_TO_CENTER_DP = 93;      // 文字基线距离中心的距离
    public static final float MAX_SCORE = 1000;

    private int progressStartAngle;
    private int progressSweepMaxAngle;
    private int progressStroke;
    private int progressDiameter;
    private int progresBackgroundColor;
    private int progressTextSize;
    private int textBaseLineToCenter;
    private int wholeWidth;

    private float targetPercent = 0;
    private float currPercent = 0;

    private Context context;
    private ValueAnimator progressAnimator;

    private Paint progressFillPaint;
    private Paint progressBgPaint;
    private Paint progressTextPaint;

    public CircleProgressView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context con) {
        context = con;
        // 初始化参数
        initParams();
        // 初始化画笔
        initPaint();
    }

    /**
     * 初始化参数
     */
    private void initParams() {
        progressStartAngle = 135;
        progressSweepMaxAngle = 270;
        progressStroke = DisplayUtil.dp2px(context, PROGRESS_STROKE_DP);
        progressDiameter = DisplayUtil.dp2px(context, PROGRESS_DIAMETER_DP);
        progresBackgroundColor = Color.parseColor("#F89486");
        progressTextSize = DisplayUtil.dp2px(context, PROGRESS_TEXT_SIZE_DP);
        textBaseLineToCenter = DisplayUtil.dp2px(context, TEXT_BASELINE_TO_CENTER_DP);
        wholeWidth = DisplayUtil.dp2px(context, PROGRESS_STROKE_DP + PROGRESS_DIAMETER_DP);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 初始化进度条填充画笔
        initProgressFillPaint();
        // 初始化进度条背景画笔
        initProgressBgPaint();
        // 初始化进度条文字画笔
        initProgressTextPaint();
    }

    /**
     * 初始化进度条填充画笔
     */
    private void initProgressFillPaint() {
        progressFillPaint = new Paint();
        progressFillPaint.setColor(Color.WHITE);
        progressFillPaint.setStyle(Paint.Style.STROKE);
        progressFillPaint.setAntiAlias(true);
        progressFillPaint.setStrokeCap(Paint.Cap.ROUND);
        progressFillPaint.setStrokeWidth(progressStroke);
    }

    /**
     * 初始化进度条背景画笔
     */
    private void initProgressBgPaint() {
        progressBgPaint = new Paint();
        progressBgPaint.setColor(progresBackgroundColor);
        progressBgPaint.setStyle(Paint.Style.STROKE);
        progressBgPaint.setAntiAlias(true);
        progressBgPaint.setStrokeCap(Paint.Cap.ROUND);
        progressBgPaint.setStrokeWidth(progressStroke);
    }

    /**
     * 初始化进度条文字画笔
     */
    private void initProgressTextPaint() {
        progressTextPaint = new Paint();
        progressTextPaint.setColor(Color.WHITE);
        progressTextPaint.setStyle(Paint.Style.FILL);
        progressTextPaint.setAntiAlias(true);
        progressTextPaint.setTextSize(progressTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(wholeWidth, wholeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        RectF progressRectF = new RectF(progressStroke / 2
                , progressStroke / 2
                , progressStroke / 2 + progressDiameter
                , progressStroke / 2 + progressDiameter);
        // 画进度条背景圈
        canvas.drawArc(progressRectF
                , progressStartAngle, progressSweepMaxAngle
                , false
                , progressBgPaint);
        int fillSweepAngle = (int) (currPercent * progressSweepMaxAngle);
        // 画进度条进度圈
        canvas.drawArc(progressRectF
                , progressStartAngle, fillSweepAngle
                , false
                , progressFillPaint);
        // 画进度条的围绕文字
        for (int i = 0; i < PROGRESS_TEXT_ARRAY.length; i++) {
            drawProgressText(canvas, PROGRESS_SCORE_ARRAY[i], PROGRESS_TEXT_ARRAY[i]);
        }
    }

    /**
     * 画环绕进度条的文字
     *
     * @param canvas
     * @param score  对应的分值
     * @param text
     */
    private void drawProgressText(Canvas canvas, float score, String text) {
        // 根据分值算位置
        float rotateAngle = (int) (progressSweepMaxAngle * score / MAX_SCORE) - progressSweepMaxAngle / 2;
        // 因为要让对应的分值线位于文字的中间, 所以要计算额外偏移的角度
        float textWitdh = progressTextPaint.measureText(text);
        double deltaAngle = getDeltaAngle(textBaseLineToCenter, textBaseLineToCenter, textWitdh / 2);
        rotateAngle -= deltaAngle;
        // 相对于View的中心旋转
        canvas.save();
        canvas.rotate(rotateAngle, wholeWidth / 2, wholeWidth / 2);
        canvas.drawText(text
                , wholeWidth / 2 - textWitdh / 2
                , wholeWidth / 2 - textBaseLineToCenter
                , progressTextPaint);
        canvas.restore();
    }

    /**
     * 获取额外偏移的角度
     *
     * @param aBorder
     * @param bBorder
     * @param cBorder
     */
    private double getDeltaAngle(double aBorder, double bBorder, double cBorder) {
        double cosValue = (aBorder * aBorder + bBorder * bBorder - cBorder * cBorder) / (2 * aBorder * bBorder);
        return Math.acos(cosValue);
    }

    /**
     * 设置分值
     *
     * @param score
     */
    public void setScore(int score) {
        score = Math.max(0, score);
        score = (int) Math.min(MAX_SCORE, score);
        targetPercent = score / MAX_SCORE;
        startProgressAnimator();
    }

    /**
     * 加载进度条动画
     */
    private void startProgressAnimator() {
        progressAnimator = new ValueAnimator();
        progressAnimator = ValueAnimator.ofFloat(0, targetPercent);
        progressAnimator.setDuration(1000);
        progressAnimator.setInterpolator(new DecelerateInterpolator());
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currPercent = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        progressAnimator.start();
    }

    /**
     * 释放资源
     */
    public void releaseResource() {
        if (progressAnimator != null) {
            progressAnimator.cancel();
        }
    }
}
