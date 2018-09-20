package com.example.tsnt.view.select_dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tsnt.R;
import com.example.tsnt.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_select_dialog);
    }

    public void showDialog(View view) {
        List<SelectDialog.ClickEvent> clickEvents = new ArrayList<>();
        clickEvents.add(new SelectDialog.ClickEvent("111", new SelectDialog.ClickEvent.ClickEventListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast("111");
            }
        }));
        clickEvents.add(new SelectDialog.ClickEvent("拍照", new SelectDialog.ClickEvent.ClickEventListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast("拍照");
            }
        }));
        clickEvents.add(new SelectDialog.ClickEvent("相册", new SelectDialog.ClickEvent.ClickEventListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast("相册");
            }
        }));
        new SelectDialog.Builder(this)
                .setClickEvents(clickEvents)
                .build()
                .show(getSupportFragmentManager(), "SelectDialog");
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, SelectDialogActivity.class);
        context.startActivity(intent);
    }
}
