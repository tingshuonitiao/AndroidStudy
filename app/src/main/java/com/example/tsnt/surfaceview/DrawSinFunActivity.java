package com.example.tsnt.surfaceview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.tsnt.R;

public class DrawSinFunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_sin_fun);
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, DrawSinFunActivity.class));
    }
}