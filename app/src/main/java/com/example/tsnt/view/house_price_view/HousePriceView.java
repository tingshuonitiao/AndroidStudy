package com.example.tsnt.view.house_price_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.tsnt.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-11-09 11:21
 * @Description:
 */

public class HousePriceView extends View {

    // 当前触摸的列数索引
    private int columnIndex = 0;
    // 黑色提示视图左边区域的宽度
    private float lwidth;
    // 黑色提示视图右边区域的宽度
    private float rwidth;
    // 最大价格
    private float maxValue;
    // 最小价格
    private float minValue;
    // 区域名, 展示在折线图下方
    private String[] names;
    // 区域名, 展示在触摸之后的黑色视图上
    private String[] name2;
    // 横坐标, 时间刻度
    private String[] xStep;
    // 纵坐标, 价格刻度
    private String[] yStep;
    // 价格比上最大价格的值
    private Float[][] values;
    // 价格
    private Long[][] dvalue;
    // 图表画笔
    private Paint mChartPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 文字画笔
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 虚线设置
    private PathEffect effects = new DashPathEffect(new float[]{3, 2}, 1);
    // 黑色提示视图范围
    private RectF toastRect = new RectF(0, 0, 100, 78);
    // 空态视图
    private Drawable mEmptyDrawable;

    public HousePriceView(Context context) {
        super(context);
        initView(context);
    }

    public HousePriceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HousePriceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = Math.round(width / 380.0f * 250.0f);
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // 触摸指定区域, 显示黑色提示视图
                float x = event.getX(0) * 380 / getMeasuredWidth();
                float y = event.getY(0) * 380 / getMeasuredWidth();
                if (x > 48 && x < 328 && y > 40 && y < 220) {
                    if (Math.abs(70 - x) < 40) {
                        columnIndex = 1;
                    } else if (Math.abs(190 - x) < 40) {
                        columnIndex = 2;
                    } else if (Math.abs(310 - x) < 40) {
                        columnIndex = 3;
                    } else {
                        columnIndex = 0;
                    }
                } else {
                    columnIndex = 0;
                }
                postInvalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 保存画板原来的状态
        canvas.save();
        // 根据比例缩放画板
        float scale = getMeasuredWidth() / 380f;
        canvas.scale(scale, scale);
        // 绘制左上角挂牌趋势
        mTextPaint.setColor(0xff4a4a4a);
        mTextPaint.setTextSize(16);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("挂牌价趋势", 10, 23, mTextPaint);
        Paint paint = mTextPaint;
        if (dvalue != null) {
            // 绘制右上角单位
            paint.setColor(0xff666666);
            paint.setTextSize(12);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("单位：元/" + getResources().getString(R.string.area_unit), 370, 22, paint);
            // 绘制左下角的区域对应颜色的圆点
            paint = mChartPaint;
            paint.setStyle(Paint.Style.FILL);
            if (!TextUtils.isEmpty(names[0])) {
                paint.setColor(0xffB8EC7F);
                canvas.drawCircle(20f, 213, 3.5f, paint);
            }
            if (!TextUtils.isEmpty(names[1])) {
                paint.setColor(0xffFEC5C5);
                canvas.drawCircle(20f, 233, 3.5f, paint);
            }
            if (!TextUtils.isEmpty(names[2])) {
                paint.setColor(0xffFF8C8C);
                canvas.drawCircle(172f, 213, 3.5f, paint);
            }
            if (!TextUtils.isEmpty(names[3])) {
                paint.setColor(0xffC37FFF);
                canvas.drawCircle(172f, 233, 3.5f, paint);
            }
            // 绘制左下角区域的名字
            paint = mTextPaint;
            paint.setColor(0xff666666);
            paint.setTextSize(12);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(toStringReplaceNULL(names[0], ""), 28, 218, paint);
            canvas.drawText(toStringReplaceNULL(names[1], ""), 28, 238, paint);
            canvas.drawText(toStringReplaceNULL(names[2], ""), 181, 218, paint);
            canvas.drawText(toStringReplaceNULL(names[3], ""), 181, 238, paint);
            // 绘制y轴刻度
            paint = mTextPaint;
            paint.setColor(0xff999999);
            paint.setTextSize(12);
            paint.setTextAlign(Paint.Align.RIGHT);
            if (yStep != null) {
                canvas.drawText(toStringReplaceNULL(yStep[0], ""), 44, 175, paint);
                canvas.drawText(toStringReplaceNULL(yStep[1], ""), 44, 138, paint);
                canvas.drawText(toStringReplaceNULL(yStep[2], ""), 44, 97, paint);
                canvas.drawText(toStringReplaceNULL(yStep[3], ""), 44, 55, paint);
            }
            // 绘制x轴刻度
            paint.setTextAlign(Paint.Align.CENTER);
            if (xStep != null) {
                canvas.drawText(toStringReplaceNULL(xStep[0], ""), 70, 196, paint);
                canvas.drawText(toStringReplaceNULL(xStep[1], ""), 190, 196, paint);
                canvas.drawText(toStringReplaceNULL(xStep[2], ""), 310, 196, paint);
            }
            // 绘制x轴和y轴, 以及其他四条虚线
            paint = mChartPaint;
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(1.0f);
            paint.setColor(0xffD3D3D3);
            paint.setPathEffect(effects);
            canvas.drawLine(48, 40f, 48, 178.25f, paint);
            canvas.drawLine(48, 51.25f, 328, 51.25f, paint);
            canvas.drawLine(48, 93.25f, 328, 93.25f, paint);
            canvas.drawLine(48, 135.25f, 328, 135.25f, paint);
            canvas.drawLine(48, 178.25f, 328, 178.25f, paint);
            // 绘制四条价格折线图
            canvas.save();
            canvas.translate(70, 178.25f);
            canvas.scale(1, -1f);
            paint.setPathEffect(null);
            if (values != null) {
                paint.setColor(0xffB8EC7F);
                drawPolyLine(canvas, values[0], paint);
                paint.setColor(0xffFEC5C5);
                drawPolyLine(canvas, values[1], paint);
                paint.setColor(0xffFF8C8C);
                drawPolyLine(canvas, values[2], paint);
                paint.setColor(0xffC37FFF);
                drawPolyLine(canvas, values[3], paint);
            }
            canvas.restore();
            // 绘制黑色提示视图
            if (columnIndex > 0 && dvalue != null && !checkEmptyData(columnIndex - 1)) {
                paint.setStyle(Paint.Style.STROKE);
                paint.setPathEffect(null);
                paint.setColor(0xff999999);
                paint.setStrokeWidth(1.0f);
                float x = (columnIndex - 1) * 120 + 70;
                // 绘制垂直x轴的直线
                canvas.drawLine(x, 40, x, 178.25f, paint);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(0xcc000000);
                if (x + 4 + toastRect.right > 310) {
                    // 当点击最右边月份区域的时候提示视图出现在垂直x轴直线的左边
                    canvas.translate(x - 4 - toastRect.right, 60);
                } else {
                    // 其他情况出现在右边
                    canvas.translate(x + 4, 60);
                }
                // 绘制黑色提示视图的背景
                canvas.drawRoundRect(toastRect, 4, 4, paint);
                // 绘制黑色提示视图的边框
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(1.0f);
                paint.setColor(0xff000000);
                canvas.drawRoundRect(toastRect, 4, 4, paint);
                // 绘制区域名字
                paint = mTextPaint;
                paint.setTextSize(12);
                paint.setTextAlign(Paint.Align.RIGHT);
                paint.setColor(0xff9B9B9B);
                x = 3 + lwidth;
                if (name2 != null) {
                    canvas.drawText(toStringReplaceNULL(name2[0], ""), x, 15, paint);
                    canvas.drawText(toStringReplaceNULL(name2[1], ""), x, 34, paint);
                    canvas.drawText(toStringReplaceNULL(name2[2], ""), x, 53, paint);
                    canvas.drawText(toStringReplaceNULL(name2[3], ""), x, 72, paint);
                }
                // 绘制区域价格
                x = 3 + lwidth + 3f;
                paint.setTextAlign(Paint.Align.LEFT);
                paint.setColor(0xeeffffff);
                canvas.drawText(toStringReplaceNULL(dvalue[0][columnIndex - 1], "--"), x, 15, paint);
                canvas.drawText(toStringReplaceNULL(dvalue[1][columnIndex - 1], "--"), x, 34, paint);
                canvas.drawText(toStringReplaceNULL(dvalue[2][columnIndex - 1], "--"), x, 53, paint);
                canvas.drawText(toStringReplaceNULL(dvalue[3][columnIndex - 1], "--"), x, 72, paint);
            }
        } else {
            // 没有数据的情况
            if (mEmptyDrawable != null) {
                mEmptyDrawable.draw(canvas);
            }
            paint = mTextPaint;
            paint.setTextSize(13);
            paint.setColor(0xff9B9B9B);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("暂无相关数据", 190, 190, paint);
        }
        // 恢复画板原来的状态
        canvas.restore();
    }

    /**
     * 绘制折线图
     *
     * @param canvas
     * @param values
     * @param paint
     */
    private void drawPolyLine(Canvas canvas, Float[] values, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3.0f);
        // 绘制折线
        int pi = 0;
        Float pv = values[pi];
        for (int i = 1; i < values.length; i++) {
            if (values[i] != null) {
                if (pv != null) {
                    canvas.drawLine(pi * 120, pv * 127, i * 120, values[i] * 127, paint);
                }
                pi = i;
                pv = values[i];
            }
        }
        // 绘制折点的外圈
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                canvas.drawCircle(i * 120, values[i] * 127, 5, paint);
            }
        }
        // 绘制折点的内圈
        paint.setColor(0xffffffff);
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                canvas.drawCircle(i * 120, values[i] * 127, 2, paint);
            }
        }
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(List<TrendEntity> data) {
        lwidth = 0;
        rwidth = 0;
        if (data != null && data.size() > 0) {
            // 初始化数据
            initData();
            // 根据原始数据, 获取最大价格等、最小价格等
            parseData(data);
            // 设置黑色提示视图的参数
            setTipParam();
        } else {
            // 清除数据
            clearData();
        }
        postInvalidate();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        maxValue = 0;
        minValue = Float.MAX_VALUE;
        names = new String[4];
        xStep = new String[3];
        yStep = new String[4];
        dvalue = new Long[4][3];
        values = new Float[4][3];
    }

    /**
     * 清除数据
     */
    private void clearData() {
        maxValue = 0;
        minValue = 0;
        names = null;
        name2 = null;
        xStep = null;
        yStep = null;
        values = null;
    }

    /**
     * 解析原始数据
     *
     * @param data
     */
    private void parseData(List<TrendEntity> data) {
        // 区域排序, 大的范围在前
        Collections.sort(data, new Comparator<TrendEntity>() {
            @Override
            public int compare(TrendEntity lhs, TrendEntity rhs) {
                if (lhs != null && rhs != null) {
                    if (lhs.getType() > rhs.getType()) {
                        return 1;
                    } else if (lhs.getType() < rhs.getType()) {
                        return -1;
                    }
                }
                return 0;
            }
        });
        // 获取关键数据
        Iterator<TrendEntity> it = data.iterator();
        while (it.hasNext()) {
            TrendEntity trend = it.next();
            int t = Math.min(4, Math.max(Long.valueOf(trend.getType()).intValue(), 1)) - 1;
            names[t] = trend.getName();
            if (trend.getType() == 4) {
                names[t] = "小区";
            }
            if (trend.getDetail() != null) {
                for (int j = 0, l = Math.min(trend.getDetail().size(), 3); j < l; j++) {
                    if (trend.getDetail().get(j) != null) {
                        xStep[j] = trend.getDetail().get(j).getTime_str();
                        dvalue[t][j] = trend.getDetail().get(j).getNumber();
                        dvalue[t][j] = (dvalue[t][j] < 0 ? null : dvalue[t][j]);
                        dvalue[t][j] = (dvalue[t][j] != null && dvalue[t][j] == 0 ? null : dvalue[t][j]);
                        if (dvalue[t][j] != null && dvalue[t][j] != -1) {
                            maxValue = Math.max(maxValue, dvalue[t][j]);
                            minValue = Math.min(minValue, dvalue[t][j]);
                        }
                    }
                }
            }
        }
        // 根据情况, 调整数据
        adjustData();
    }

    /**
     * 调整数据
     */
    private void adjustData() {
        if (maxValue == minValue && maxValue == 0) {
            // 最大价格和最小价格都为0
            yStep[0] = "0";
            yStep[1] = "3333";
            yStep[2] = "6666";
            yStep[3] = "1.0万";
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (dvalue[i][j] != null) {
                        values[i][j] = 0f;
                    }
                }
            }
        } else if (maxValue == minValue) {
            // 最大价格和最小价格相等时
            maxValue = minValue * 2;
            minValue = 0;
            yStep[0] = formatFloatValue(minValue);
            yStep[1] = formatFloatValue(maxValue * 0.33f);
            yStep[2] = formatFloatValue(maxValue * 0.66f);
            yStep[3] = formatFloatValue(maxValue);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (dvalue[i][j] != null) {
                        values[i][j] = 0.5f;
                    }
                }
            }
        } else {
            float length = Math.abs(maxValue - minValue) * 1.2f;
            if (maxValue - length < 0) {
                length = maxValue;
            }
            yStep[3] = formatFloatValue(maxValue);
            yStep[2] = formatFloatValue(maxValue - length * 0.33f);
            yStep[1] = formatFloatValue(maxValue - length * 0.66f);
            yStep[0] = formatFloatValue(maxValue - length);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (dvalue[i][j] != null) {
                        float value = dvalue[i][j];
                        values[i][j] = 1.0f - (maxValue - value) / length;
                    }
                }
            }
        }
        // 根据区域名获取精简的区域名
        name2 = Arrays.copyOf(names, names.length);
        for (int i = 0; i < name2.length; i++) {
            if (toStringReplaceNULL(name2[i], "").length() > 8) {
                name2[i] = name2[i].substring(0, 7) + "…";
            }
        }
    }

    /**
     * 设置黑色提示视图的参数
     */
    private void setTipParam() {
        mTextPaint.setTextSize(12);
        lwidth = calcMaxTextWidth(name2, mTextPaint);
        for (int i = 0; i < dvalue.length; i++) {
            rwidth = Math.max(rwidth, calcMaxTextWidth(dvalue[i], mTextPaint));
        }
        toastRect.right = 3 + lwidth + 3 + rwidth + 3;
    }

    /**
     * 设置空页面
     *
     * @param drawable
     */
    public void setEmptyDrawable(Drawable drawable) {
        mEmptyDrawable = drawable;
        if (mEmptyDrawable != null) {
            mEmptyDrawable.setBounds(120, 40, 260, 150);
        }
    }

    /**
     * 价格格式化
     *
     * @param value
     * @return
     */
    private String formatFloatValue(float value) {
        if (value > 10000) {
            return String.format("%.1f万", value * 0.0001);
        } else {
            return String.format("%.0f", value);
        }
    }

    private String toStringReplaceNULL(Object value, String re, String... append) {
        if (value == null || String.valueOf(value).trim().length() < 1) {
            return re;
        } else {
            StringBuffer sb = new StringBuffer(String.valueOf(value));
            if (append.length > 0) {
                for (int i = 0; i < append.length; i++) {
                    sb.append(append[i]);
                }
            }
            return sb.toString();
        }
    }

    /**
     * 计算文字最大宽度
     *
     * @param objects
     * @param paint
     * @return
     */
    private float calcMaxTextWidth(Object[] objects, Paint paint) {
        float maxWidth = 0f;
        for (int i = 0; i < objects.length; i++) {
            float width = 0f;
            String value = toStringReplaceNULL(objects[i], "--");
            float[] widths = new float[value.length()];
            paint.getTextWidths(value, widths);
            for (int j = 0; j < widths.length; j++) {
                width += widths[j];
            }
            maxWidth = Math.max(maxWidth, width);
        }
        return maxWidth;
    }

    /**
     * 检测数据是否为空
     *
     * @param index
     * @return
     */
    private boolean checkEmptyData(int index) {
        index = Math.min(2, Math.max(0, index));
        if (dvalue != null) {
            for (int i = 0; i < dvalue.length; i++) {
                if (dvalue[i][index] != null && dvalue[i][index] != -1) {
                    return false;
                }
            }
        }
        return true;
    }
}
