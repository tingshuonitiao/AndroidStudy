package com.example.tsnt.android_base.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by ting说你跳 on 2017/8/31.
 */

public class MyIntentService extends IntentService {
    public static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 5; i++) {
            SystemClock.sleep(50);
            Log.d(TAG, Thread.currentThread().getName() + " " + i);
        }
        Log.d(TAG, Thread.currentThread().getName() + "  " );
    }
}
