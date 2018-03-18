package com.example.tsnt.mvvm;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tsnt.R;
import com.example.tsnt.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        dataBinding.content.setText("Hello, databinding!");
        dataBinding.setViewModel(new TestVM());
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }
}
