package com.example.tsnt.eventflow;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-09-14 10:27
 * @Description:
 */

public abstract class FlowEvent {

    private EventFlow eventFlow;

    protected void setEventFlow(EventFlow flow) {
        eventFlow = flow;
    }

    public void end() {
        eventFlow.toNext();
    }

    public abstract void onExecute();
}
