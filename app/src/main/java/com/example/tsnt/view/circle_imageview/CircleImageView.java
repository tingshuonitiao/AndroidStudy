package com.example.tsnt.view.circle_imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.example.tsnt.R;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-03-04 16:43
 * @Description: 圆形ImageView
 */

public class CircleImageView extends AppCompatImageView {

    // 头像画笔
    private Paint avatarPaint;
    // 边框画笔
    private Paint borderPaint;
    // view对应的Bitmap
    private Bitmap bitmap;
    // 边框的宽度
    private int borderWidth = 10;
    // 边框的颜色
    private int borderColor = Color.parseColor("#FFE384");
    private BitmapShader bitmapShader;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        avatarPaint = new Paint();
        borderPaint = new Paint();
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setStyle(Paint.Style.STROKE);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_kobe);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置shader
        Matrix matrix = new Matrix();
        float scale = (float) getWidth() / bitmap.getWidth();
        matrix.setScale(scale, scale);
        bitmapShader.setLocalMatrix(matrix);
        avatarPaint.setShader(bitmapShader);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - borderWidth, avatarPaint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - borderWidth / 2, borderPaint);
    }
}
