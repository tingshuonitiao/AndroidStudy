package com.example.tsnt.view.chat_dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tsnt.R;

public class ChattingDialogActivity extends AppCompatActivity {


    private ChatDialog dialog;
    private View confirmTv;
    private TextView inputTv;
    private EditText inputEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_dialog);
        initView();
    }

    private void initView() {
        inputTv = (TextView) findViewById(R.id.input_tv);
        inputTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChatDialog();
            }
        });
        // 软键盘监听
        new KeyboardStatusDetector()
                .registerActivity(this)
                .setVisibilityListener(new KeyboardStatusDetector.KeyboardVisibilityListener() {
                    @Override
                    public void onVisibilityChanged(boolean keyboardVisible) {
                        if (keyboardVisible) {
                            //Do stuff for keyboard visible
                        } else {
                            //Do stuff for keyboard hidden
                            // 键盘收起的时候, 聊天框如果还在展示就收起
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }
                    }
                });
    }

    /**
     * 展示聊天框
     */
    private void showChatDialog() {
        dialog = new ChatDialog(this, R.style.ChatDialogStyle);
        dialog.show();
        inputEdt = (EditText) dialog.findViewById(R.id.input_edt);
        confirmTv = dialog.findViewById(R.id.confirm_tv);
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击确认键的时候收起聊天框和键盘
                dialog.dismiss();
                hideSoftInput(v);
            }
        });
        // 同步聊天框内容
        syncInputContent();
        // 在dialog展示之后设置聊天框参数
        setDialogParamAfterDialogShow();
    }

    /**
     * 同步聊天框内容
     */
    private void syncInputContent() {
        String content = inputTv.getText().toString().trim();
        inputEdt.setText(content);
        inputEdt.setSelection(inputEdt.length());
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                inputTv.setText(inputEdt.getText().toString().trim());
            }
        });
    }

    /**
     * 在dialog展示之后设置聊天框参数
     */
    private void setDialogParamAfterDialogShow() {
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = display.getWidth();
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(lp);
    }

    /**
     * 收起键盘
     *
     * @param view
     */
    private void hideSoftInput(View view) {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 跳转到本页面
     *
     * @param context
     */
    public static void launch(Context context) {
        context.startActivity(new Intent(context, ChattingDialogActivity.class));
    }
}
