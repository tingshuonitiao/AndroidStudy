package com.example.tsnt.eventflow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-09-14 10:53
 * @Description:
 */

public class Test extends FragmentActivity {

    public void test() {
        EventFlow.getInstance()
                .addEvent(getEvent())
                .addEvent(getEvent())
                .addEvent(getEvent())
                .addEvent(getEvent())
                .addEvent(getEvent())
                .start();
    }

    public FlowEvent getEvent() {
        return new FlowEvent() {
            @Override
            public void onExecute() {
                boolean showImmediate = true;
                if (showImmediate) {
                    showDialog(this);
                } else {
                    getData(this);
                }
            }
        };
    }

    public void getData(final FlowEvent event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showDialog(event);
                    }
                });
            }
        }).start();
    }

    public void showDialog(final FlowEvent event) {
        new AlertDialog.Builder(Test.this)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        event.end();
                    }
                })
                .create()
                .show();
    }
}
