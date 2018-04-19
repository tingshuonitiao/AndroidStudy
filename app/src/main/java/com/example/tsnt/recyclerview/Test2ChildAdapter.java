package com.example.tsnt.recyclerview;

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

public class Test2ChildAdapter extends RecyclerView.Adapter<Test2ChildAdapter.ChildViewHolder> {

    public static final String TAG = Test2ChildAdapter.class.getSimpleName();

    private List<String> list;

    public Test2ChildAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public Test2ChildAdapter.ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        Test2ChildAdapter.ChildViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_type_b, null);
        holder = new ChildViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Test2ChildAdapter.ChildViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        holder.contentTv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class ChildViewHolder extends RecyclerView.ViewHolder {

        private final TextView contentTv;

        public ChildViewHolder(View itemView) {
            super(itemView);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv);
        }
    }
}
