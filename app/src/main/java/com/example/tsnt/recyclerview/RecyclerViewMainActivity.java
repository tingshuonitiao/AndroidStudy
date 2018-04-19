package com.example.tsnt.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tsnt.R;

public class RecyclerViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler_view_main);
        findViewById(R.id.gototest1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewTest1Activity.launch(RecyclerViewMainActivity.this);
            }
        });
        findViewById(R.id.gototest2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewTest2Activity.launch(RecyclerViewMainActivity.this);
            }
        });
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, RecyclerViewMainActivity.class);
        context.startActivity(intent);
    }
}
