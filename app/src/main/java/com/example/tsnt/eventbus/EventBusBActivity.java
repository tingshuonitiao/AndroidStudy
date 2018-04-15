package com.example.tsnt.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.tsnt.R;

import org.greenrobot.eventbus.EventBus;

public class EventBusBActivity extends AppCompatActivity {

    private TextView sayHelloTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_eventbus_b);
        sayHelloTv = (TextView) findViewById(R.id.say_hello);
        sayHelloTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelloEvent event = new HelloEvent();
                event.setMessage("Hi, Page A!");
                EventBus.getDefault().post(event);
                finish();
            }
        });
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, EventBusBActivity.class);
        context.startActivity(intent);
    }
}
