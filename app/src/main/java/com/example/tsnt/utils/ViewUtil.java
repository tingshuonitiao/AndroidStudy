package com.example.tsnt.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-03-03 09:48
 * @Description: 和View相关的工具类
 */

public class ViewUtil {

    /**
     * 生成View对应的Bitmap
     *
     * @param activity
     * @param view
     * @param hasAddedToScreen 是否已经被添加到屏幕上
     * @return
     */
    public static Bitmap generateViewInBitmap(Activity activity, View view, boolean hasAddedToScreen) {
        if (hasAddedToScreen) {
            return generateAddedViewInBitmap(view);
        } else {
            return generateUnAddedViewInBitmap(activity, view);
        }
    }

    // 生成已添加到屏幕上的View对应的Bitmap
    private static Bitmap generateAddedViewInBitmap(View view) {
        // 获取view的宽度
        int viewWidth = view.getWidth();
        // 获取view的高度
        int viewHeight = view.getHeight();
        // 创建View对应大小的Bitmap
        Bitmap bitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
        // 把view的内容添加到Bitmap
        Canvas canvas = new Canvas((bitmap));
        view.layout(0, 0, viewWidth, viewHeight);
        view.draw(canvas);
        return bitmap;
    }

    // 生成未添加到屏幕上的View对应的Bitmap
    private static Bitmap generateUnAddedViewInBitmap(Activity activity, View view) {
        // 设置view的宽度
        int viewWidth = ScreenUtil.getScreenWidth(activity);
        // 测量view的高度
        view.measure(View.MeasureSpec.makeMeasureSpec(viewWidth, View.MeasureSpec.EXACTLY), ViewGroup.LayoutParams.WRAP_CONTENT);
        int viewHeight = view.getMeasuredHeight();
        // 创建View对应大小的Bitmap
        Bitmap bitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
        // 把view的内容添加到Bitmap
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, viewWidth, viewHeight);
        view.draw(canvas);
        return bitmap;
    }
}
