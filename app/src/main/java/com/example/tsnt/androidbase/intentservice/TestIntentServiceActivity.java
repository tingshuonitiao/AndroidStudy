package com.example.tsnt.androidbase.intentservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tsnt.R;

public class TestIntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent_service);
        for (int i = 0; i < 5; i++) {
            startService(new Intent(TestIntentServiceActivity.this, MyIntentService.class));
        }
    }
}
