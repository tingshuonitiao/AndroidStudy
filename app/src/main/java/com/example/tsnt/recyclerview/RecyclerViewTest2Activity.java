package com.example.tsnt.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tsnt.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTest2Activity extends AppCompatActivity {

    private List<String> verticalList;
    private List<String> horizontal1List;
    private List<String> horizontal2List;

    private RecyclerView recyclerView;
    private Test2ParentAdapter parentAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler_view_test2);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        verticalList = new ArrayList<>();
        horizontal1List = new ArrayList<>();
        horizontal2List = new ArrayList<>();
        parentAdapter = new Test2ParentAdapter(this,verticalList, horizontal1List, horizontal2List);
        recyclerView.setAdapter(parentAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initData() {
        for (int i = 0; i < 80; i++) {
            verticalList.add(1 + i + "");
        }
        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 'A');
            horizontal1List.add(ch + "");
            horizontal2List.add(ch + "");
        }
        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 'A');
            horizontal2List.add(ch + "");
        }
        parentAdapter.notifyDataSetChanged();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, RecyclerViewTest2Activity.class);
        context.startActivity(intent);
    }
}
