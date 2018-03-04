package com.example.tsnt.view.circle_imageview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tsnt.R;

public class CircleImageViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image_view_test);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, CircleImageViewTestActivity.class);
        context.startActivity(intent);
    }
}
