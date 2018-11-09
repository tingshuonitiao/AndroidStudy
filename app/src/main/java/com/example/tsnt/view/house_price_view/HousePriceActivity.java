package com.example.tsnt.view.house_price_view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tsnt.R;
import com.google.gson.Gson;

public class HousePriceActivity extends AppCompatActivity {

    private HousePriceView housePriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_banana_price);
        housePriceView = (HousePriceView) findViewById(R.id.house_price);
    }

    private void initData() {
        Gson gson = new Gson();
        HousePriceEntity priceEntity = gson.fromJson(getResources().getString(R.string.json_house_price), HousePriceEntity.class);
        housePriceView.setData(priceEntity.getTrend());
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, HousePriceActivity.class);
        context.startActivity(intent);
    }
}
