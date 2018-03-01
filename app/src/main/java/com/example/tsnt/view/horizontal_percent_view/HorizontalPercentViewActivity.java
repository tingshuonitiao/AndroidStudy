package com.example.tsnt.view.horizontal_percent_view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tsnt.R;
import com.example.tsnt.view.gluttonous_snake.GluttonousSnakeActivity;

public class HorizontalPercentViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_percent_view);
        initView();
    }

    private void initView() {
        HorizontalPercentView percentView = (HorizontalPercentView) findViewById(R.id.percent_view);
        percentView.setPercent(0.5);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, HorizontalPercentViewActivity.class);
        context.startActivity(intent);
    }
}
