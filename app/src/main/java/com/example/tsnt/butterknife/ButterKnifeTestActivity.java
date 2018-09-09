package com.example.tsnt.butterknife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tsnt.R;
import com.example.tsnt.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeTestActivity extends AppCompatActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;

    @OnClick(R.id.say_hello_tv)
    void sayHello() {
        ToastUtil.showToast("hello, hello, hello!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_test);
        ButterKnife.bind(this);
        titleTv.setText("ButterKnifeTestActivity");
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, ButterKnifeTestActivity.class);
        context.startActivity(intent);
    }
}
