package com.example.tsnt;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tsnt.MyEventBusIndex;
import com.squareup.leakcanary.LeakCanary;

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
        // 初始化EventBus
        initEventBus();
        // 初始化ARouter
        initARouter();
        //初始化LeakCanary
        initLeakCanary();
    }

    private void initEventBus() {
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();             // 打印日志
            ARouter.openDebug();           // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);     // 尽可能早，推荐在Application中初始化
    }

    private void initLeakCanary() {
        LeakCanary.install(this);
    }
}
