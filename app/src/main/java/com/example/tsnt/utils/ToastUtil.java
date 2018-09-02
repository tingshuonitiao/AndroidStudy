package com.example.tsnt.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.tsnt.TsntApplication;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-09-02 12:09
 * @Description:
 */

public class ToastUtil {

    public static void showToast(CharSequence toast) {
        if (TextUtils.isEmpty(toast)) return;
        Toast.makeText(TsntApplication.getTsntContext(), toast, Toast.LENGTH_SHORT)
                .show();
    }

    public static void showLongToast(CharSequence toast) {
        if (TextUtils.isEmpty(toast)) return;
        Toast.makeText(TsntApplication.getTsntContext(), toast, Toast.LENGTH_LONG)
                .show();
    }

    public static void showCustomToast(CharSequence toast, int duration) {
        if (TextUtils.isEmpty(toast)) return;
        if (duration <= 0) return;
        Toast.makeText(TsntApplication.getTsntContext(), toast, duration)
                .show();
    }
}
