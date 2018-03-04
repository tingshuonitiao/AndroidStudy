package com.example.tsnt.view.auto_line_feed_layout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.tsnt.R;

import java.util.ArrayList;
import java.util.List;

public class AutoLineFeedLayoutTestActivity extends AppCompatActivity {

    private AutoLineFeedLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_auto_line_feed_layout_test);
        container = (AutoLineFeedLayout) findViewById(R.id.container);
    }

    private void initData() {
        List<String> stringlist = new ArrayList<>();
        stringlist.add("今天天气真好");
        stringlist.add("android");
        stringlist.add("logo");
        stringlist.add("今晚");
        stringlist.add("下雨了外面");
        stringlist.add("emmmmmmmmmmmmmmmmmmmmmmmm");
        stringlist.add("很好");
        stringlist.add("LayoutInflater");
        stringlist.add("ArrayList");
        stringlist.add("第一次");
        stringlist.add("今宵难忘");
        stringlist.add("icon_kobe bryant");
        stringlist.add("今天天不好,刚才冒着雨上山烧香,现在全湿了.");
        stringlist.add("hehehehehehehehehehehehehehehehehe");

        for (int i = 0; i < stringlist.size(); i++) {
            View childView = LayoutInflater.from(AutoLineFeedLayoutTestActivity.this).
                    inflate(R.layout.item_auto_line_feed_layout_test, null);
            TextView content = (TextView) childView.findViewById(R.id.content);
            content.setText(stringlist.get(i));
            container.addView(childView);
        }
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, AutoLineFeedLayoutTestActivity.class);
        context.startActivity(intent);
    }
}
