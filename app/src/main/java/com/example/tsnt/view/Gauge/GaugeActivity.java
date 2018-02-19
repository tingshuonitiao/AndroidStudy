package com.example.tsnt.view.Gauge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tsnt.R;
import com.example.tsnt.view.base.ViewTestActivity;
import com.gauge.tsnt.gaugeview.GaugeView;

public class GaugeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauge);
        final TextView location = (TextView) findViewById(R.id.location);
        GaugeView gauge = (GaugeView) findViewById(R.id.gauge);
        gauge.setOnGaugeScrollChangeListener(new GaugeView.OnGaugeScrollChangeListener() {
            @Override
            public void onGaugeScrollChange(int i) {
                location.setText("location=" + i);
            }
        });
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, GaugeActivity.class);
        context.startActivity(intent);
    }
}
