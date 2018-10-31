package com.example.tsnt.view.chat_dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import com.example.tsnt.R;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-10-31 15:45
 * @Description:
 */

public class ChatDialog extends AlertDialog {
    public ChatDialog(@NonNull Context context) {
        super(context);
    }

    public ChatDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ChatDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_chat);
        getWindow().setDimAmount(0);
        findViewById(R.id.input_edt).requestFocus();
        //只用下面这一行弹出对话框时需要点击输入框才能弹出软键盘
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        //加上下面这一行弹出对话框时软键盘随之弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
