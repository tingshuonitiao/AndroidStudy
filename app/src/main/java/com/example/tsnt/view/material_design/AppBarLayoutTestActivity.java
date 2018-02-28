package com.example.tsnt.view.material_design;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tsnt.R;

public class AppBarLayoutTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout_test);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, AppBarLayoutTestActivity.class);
        context.startActivity(intent);
    }
}
