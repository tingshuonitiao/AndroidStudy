package com.example.tsnt.view.rotate_textview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tsnt.R;

public class RotateTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_text_view);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, RotateTextViewActivity.class);
        context.startActivity(intent);
    }
}
