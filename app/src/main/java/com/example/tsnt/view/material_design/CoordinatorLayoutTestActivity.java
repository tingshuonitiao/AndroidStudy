package com.example.tsnt.view.material_design;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tsnt.R;

public class CoordinatorLayoutTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout_test);
        initView();
    }

    private void initView() {
        findViewById(R.id.appBarLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoordinatorLayoutTestActivity.this, AppBarLayoutTestActivity.class));
            }
        });
        findViewById(R.id.collapsingToolbarLayout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoordinatorLayoutTestActivity.this, CollapsingToolbarLayoutTestActivity1.class));
            }
        });
        findViewById(R.id.collapsingToolbarLayout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoordinatorLayoutTestActivity.this, CollapsingToolbarLayoutTestActivity2.class));
            }
        });
    }
}
