package com.example.tsnt.view.limit_scroll_edittext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.tsnt.R;

/**
 * Created by tingshuonitiao on 17/12/16.
 */

public class LimitScrollEditTextActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit_scroll_edittext);
        initView();
    }

    private void initView() {
        LimitScrollEditText limitScrollEditText = (LimitScrollEditText) findViewById(R.id.limitScrollEditText);
        limitScrollEditText.setMaxLength(200);
        limitScrollEditText.setHint("最多输入200字");
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, LimitScrollEditTextActivity.class);
        context.startActivity(intent);
    }
}
