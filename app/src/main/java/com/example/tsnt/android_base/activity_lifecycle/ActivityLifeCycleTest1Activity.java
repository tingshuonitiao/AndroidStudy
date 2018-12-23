package com.example.tsnt.android_base.activity_lifecycle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tsnt.R;

public class ActivityLifeCycleTest1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_test1);
        Log.d(Constants.TAG, "Activity1 " + "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constants.TAG, "Activity1 " + "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constants.TAG, "Activity1 " + "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Constants.TAG, "Activity1 " + "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constants.TAG, "Activity1 " + "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "Activity1 " + "onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Constants.TAG, "Activity1 " + "onRestart()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(Constants.TAG, "Activity1 " + "onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(Constants.TAG, "Activity1 " + "onRestoreInstanceState()");
    }

    public void gotoNext(View view) {
        startActivity(new Intent(this, ActivityLifeCycleTest2Activity.class));
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ActivityLifeCycleTest1Activity.class));
    }
}
