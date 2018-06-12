package com.example.tsnt.view.round_angle_imageview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tsnt.R;

public class RoundAngleImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_angle_image_view);
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, RoundAngleImageViewActivity.class));
    }
}
