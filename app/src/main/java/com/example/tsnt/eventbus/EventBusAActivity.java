package com.example.tsnt.eventbus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tsnt.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusAActivity extends AppCompatActivity {

    private TextView gotoPageBTv;
    private TextView messageTv;
    private View showToast;

    private CustomerSubscriber subscriber1;
    private CustomerSubscriber subscriber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initView();
        initData();
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
        showToast = findViewById(R.id.show_toast);
        showToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(subscriber1.getMessage())) {
                    Toast.makeText(EventBusAActivity.this, "subscriber1's message = " + subscriber1.getMessage(), Toast.LENGTH_SHORT)
                            .show();
                }
                if (!TextUtils.isEmpty(subscriber2.getMessage())) {
                    Toast.makeText(EventBusAActivity.this, "subscriber2's message = " + subscriber2.getMessage(), Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void initData() {
        subscriber1 = new CustomerSubscriber();
        subscriber2 = new CustomerSubscriber();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        subscriber1.release();
        subscriber2.release();
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
