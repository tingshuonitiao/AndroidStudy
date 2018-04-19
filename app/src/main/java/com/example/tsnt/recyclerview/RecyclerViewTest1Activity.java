package com.example.tsnt.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tsnt.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTest1Activity extends AppCompatActivity {

    private List<String> firstList;
    private List<String> secondList;

    private RecyclerView firstRecyclerView;
    private RecyclerView secondRecyclerView;
    private Test1Adapter firstAdapter;
    private Test1Adapter secontAdapter;
    private GridLayoutManager firstLayoutManager;
    private GridLayoutManager secontLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_recycler_view_test1);
        initFirstRecyclerView();
        initSecondRecyclerView();
    }

    private void initFirstRecyclerView() {
        firstRecyclerView = (RecyclerView) findViewById(R.id.first_recyclerview);
        String title = "Alphas";
        firstList = new ArrayList<>();
        firstAdapter = new Test1Adapter(title, firstList);
        firstRecyclerView.setAdapter(firstAdapter);
        firstLayoutManager = new GridLayoutManager(this, 2);
        firstLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        firstLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (firstAdapter.getItemViewType(position) == Test1Adapter.TYPE_TITLE) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        firstRecyclerView.setLayoutManager(firstLayoutManager);
    }

    private void initSecondRecyclerView() {
        secondRecyclerView = (RecyclerView) findViewById(R.id.second_recyclerview);
        String title = "Numbers";
        secondList = new ArrayList<>();
        secontAdapter = new Test1Adapter(title, secondList);
        secondRecyclerView.setAdapter(secontAdapter);
        secontLayoutManager = new GridLayoutManager(this, 3);
        secontLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (secontAdapter.getItemViewType(position) == Test1Adapter.TYPE_TITLE) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        secondRecyclerView.setLayoutManager(secontLayoutManager);
    }

    private void initData() {
        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 'A');
            firstList.add(ch + "");
        }
        firstAdapter.notifyDataSetChanged();
        for (int i = 0; i < 80; i++) {
            secondList.add(1 + i + "");
        }
        secontAdapter.notifyDataSetChanged();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, RecyclerViewTest1Activity.class);
        context.startActivity(intent);
    }
}
