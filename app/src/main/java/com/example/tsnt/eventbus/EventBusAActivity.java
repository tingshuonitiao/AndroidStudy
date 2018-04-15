package com.example.tsnt.eventbus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tsnt.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusAActivity extends AppCompatActivity {

    private TextView gotoPageBTv;
    private TextView messageTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_eventbus_a);
        gotoPageBTv = (TextView) findViewById(R.id.gotoB);
        gotoPageBTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusBActivity.launch(EventBusAActivity.this);
            }
        });
        messageTv = (TextView) findViewById(R.id.message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveHelloEvent(HelloEvent helloEvent) {
        messageTv.setText("the message from Page B:\n"
                + helloEvent.getMessage());
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, EventBusAActivity.class);
        context.startActivity(intent);
    }
}
