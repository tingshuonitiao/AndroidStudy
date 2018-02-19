package com.example.tsnt.view.AutoFixedLayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tsnt.R;

public class AutoFixedLayoutTestActivity extends AppCompatActivity {

    private View add;
    private View minus;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_auto_fixed_layout_test);
        content = (TextView) findViewById(R.id.content);
        initaddOne();
        initMinusOne();
    }

    private void initaddOne() {
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = content.getText().toString();
                str += str == null ? "" : "1";
                content.setText(str);
            }
        });
    }

    private void initMinusOne() {
        minus = findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = content.getText().toString();
                if (str == null || str.length() < 1) return;
                String substring = str.substring(0, str.length() - 1);
                content.setText(substring);
            }
        });
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, AutoFixedLayoutTestActivity.class);
        context.startActivity(intent);
    }
}
