package com.example.tsnt.view.flip_dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tsnt.R;

public class FlipDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_flip_dialog);
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FlipDialog.Builder(FlipDialogActivity.this)
                        .create()
                        .show();
            }
        });
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, FlipDialogActivity.class);
        context.startActivity(intent);
    }
}
