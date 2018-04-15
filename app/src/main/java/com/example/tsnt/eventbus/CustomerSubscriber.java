package com.example.tsnt.eventbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-15 19:22
 * @Description:
 */

public class CustomerSubscriber {
    private String message;

    public CustomerSubscriber() {
        EventBus.getDefault().register(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void release() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void receiveHelloEvent(HelloEvent helloEvent) {
        message = helloEvent.getMessage();
    }
}
