package com.example.tsnt;

import android.app.Application;

import com.example.tsnt.MyEventBusIndex;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-15 18:30
 * @Description:
 */

public class TsntApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }
}
