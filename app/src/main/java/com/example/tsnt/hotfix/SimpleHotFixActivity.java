package com.example.tsnt.hotfix;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tsnt.R;
import com.example.tsnt.arouter.ARouterTest1Activity;

public class SimpleHotFixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_hot_fix);
    }

    // “修复”按钮的点击事件
    public void fix(View view) {
        FixDexUtils.loadFixedDex(this, Environment.getExternalStorageDirectory());
    }

    // “计算”按钮的点击事件
    public void clac(View view) {
        SimpleHotFixBugTest test = new SimpleHotFixBugTest();
        test.getBug(this);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, SimpleHotFixActivity.class);
        context.startActivity(intent);
    }
}
