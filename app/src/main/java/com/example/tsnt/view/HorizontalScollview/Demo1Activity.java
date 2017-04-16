package com.example.tsnt.view.HorizontalScollview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tsnt.R;

import java.util.ArrayList;

/**
 * Created by ting说你跳 on 2017/3/28.
 */

public class Demo1Activity extends AppCompatActivity {

    private MyHorizontalScrollView1 mListContainer;
    private String TAG = "Demo1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate()");
        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        mListContainer = (MyHorizontalScrollView1) findViewById(R.id.container);
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(
                    R.layout.content_layout1, mListContainer, false);
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + i);
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout, i);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout, int i) {
        ListView listView = (ListView) layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<>();
        for (int j = 0; j < 50 + i; j++) {
            datas.add("item " + j);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Demo1Activity.this, "item " + position + " is clicked",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
