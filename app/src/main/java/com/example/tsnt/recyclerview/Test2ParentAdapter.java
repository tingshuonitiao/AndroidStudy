package com.example.tsnt.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tsnt.R;

import java.util.List;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-17 17:57
 * @Description:
 */

public class Test2ParentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = Test2ParentAdapter.class.getSimpleName();

    public static final int TYPE_HORIZONTAL = 0;
    public static final int TYPE_VERTICAL = 1;

    public boolean horizontal1Loaded = false;
    public boolean horizontal2Loaded = false;

    private Context context;

    private List<String> verticalList;
    private List<String> horizontal1List;
    private List<String> horizontal2List;

    private final Test2ChildAdapter Child1Adapter;
    private final Test2ChildAdapter Child2Adapter;
    private final LinearLayoutManager linearLayoutManager1;
    private final LinearLayoutManager linearLayoutManager2;

    public Test2ParentAdapter(Context context, List<String> verticalList, List<String> horizontal1List, List<String> horizontal2List) {
        this.context = context;
        this.verticalList = verticalList;
        this.horizontal1List = horizontal1List;
        this.horizontal2List = horizontal2List;
        Child1Adapter = new Test2ChildAdapter(horizontal1List);
        Child2Adapter = new Test2ChildAdapter(horizontal2List);
        linearLayoutManager1 = new LinearLayoutManager(context);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2 = new LinearLayoutManager(context);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 2) {
            return TYPE_HORIZONTAL;
        } else {
            return TYPE_VERTICAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        RecyclerView.ViewHolder holder;
        if (viewType == TYPE_HORIZONTAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_type_re, null);
            holder = new TypeAViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_type_b, null);
            holder = new TypeBViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        if (position == 0) {
            TypeAViewHolder aViewHolder = (TypeAViewHolder) holder;
            if (!horizontal1Loaded) {
                aViewHolder.recyclerview.setAdapter(Child1Adapter);
                aViewHolder.recyclerview.setLayoutManager(linearLayoutManager1);
                horizontal1Loaded = true;
            } else {
                Child1Adapter.notifyDataSetChanged();
            }
        } else if (position == 2) {
            TypeAViewHolder aViewHolder = (TypeAViewHolder) holder;
            if (!horizontal2Loaded) {
                aViewHolder.recyclerview.setAdapter(Child2Adapter);
                aViewHolder.recyclerview.setLayoutManager(linearLayoutManager2);
                horizontal2Loaded = true;
            } else {
                Child2Adapter.notifyDataSetChanged();
            }
        } else if (position == 1) {
            TypeBViewHolder bViewHolder = (TypeBViewHolder) holder;
            bViewHolder.contentTv.setText(verticalList.get(position - 1));
        } else {
            TypeBViewHolder bViewHolder = (TypeBViewHolder) holder;
            bViewHolder.contentTv.setText(verticalList.get(position - 2));
        }
    }

    @Override
    public int getItemCount() {
        return verticalList != null ? verticalList.size() + 2 : 0;
    }

    static class TypeAViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView recyclerview;

        public TypeAViewHolder(View itemView) {
            super(itemView);
            recyclerview = (RecyclerView) itemView.findViewById(R.id.recyclerview);
        }
    }

    static class TypeBViewHolder extends RecyclerView.ViewHolder {

        private final TextView contentTv;

        public TypeBViewHolder(View itemView) {
            super(itemView);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv);
        }
    }
}
