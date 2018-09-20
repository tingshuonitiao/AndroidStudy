package com.example.tsnt.eventflow;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-09-14 10:18
 * @Description: 按顺序执行的事件流
 */

public class EventFlow {

    private Queue<FlowEvent> eventQueue;

    public static EventFlow getInstance() {
        return new EventFlow();
    }

    private EventFlow() {
        eventQueue = new LinkedList<>();
    }

    /**
     * 添加事件
     *
     * @param event
     * @return
     */
    public EventFlow addEvent(FlowEvent event) {
        event.setEventFlow(this);
        eventQueue.add(event);
        return this;
    }

    /**
     * 开启事件流
     */
    public void start() {
        executeEvent();
    }

    /**
     * 执行下一个事件
     */
    protected void toNext() {
        executeEvent();
    }

    /**
     * 执行事件
     */
    private void executeEvent() {
        FlowEvent event = eventQueue.poll();
        if (event != null) event.onExecute();
    }
}
