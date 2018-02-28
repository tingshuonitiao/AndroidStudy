package com.example.tsnt.android_base.service_lifecycle;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.tsnt.R;

/**
 * Created by ting说你跳 on 2017/8/30.
 */

public class Service3Activity extends AppCompatActivity {
    public static final String TAG = "service_lifecycle_log";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service1);
        final ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Service3Activity#onServiceConnected");
                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Service3Activity#onServiceDisconnected");
                    }
                });
            }
        };

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(Service3Activity.this, TestService.class));
            }
        });
        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(Service3Activity.this, TestService.class));
            }
        });
        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bindService(new Intent(Service3Activity.this, TestService.class),
                        conn, Context.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });
        findViewById(R.id.checkactivity1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Service3Activity.this, Service1Activity.class));
            }
        });
        findViewById(R.id.checkactivity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Service3Activity.this, Service2Activity.class));
            }
        });
        findViewById(R.id.checkactivity3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
