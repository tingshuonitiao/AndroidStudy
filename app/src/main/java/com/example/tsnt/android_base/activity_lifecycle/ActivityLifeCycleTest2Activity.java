package com.example.tsnt.android_base.activity_lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tsnt.R;

public class ActivityLifeCycleTest2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_test2);
        Log.d(Constants.TAG, "Activity2 " + "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constants.TAG, "Activity2 " + "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constants.TAG, "Activity2 " + "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Constants.TAG, "Activity2 " + "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constants.TAG, "Activity2 " + "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "Activity2 " + "onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Constants.TAG, "Activity2 " + "onRestart()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(Constants.TAG, "Activity2 " + "onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(Constants.TAG, "Activity2 " + "onRestoreInstanceState()");
    }
}
