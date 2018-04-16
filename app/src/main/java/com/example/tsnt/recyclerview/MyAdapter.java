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
 * @Date: 2018-04-15 22:15
 * @Description:
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = MyAdapter.class.getSimpleName();

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_ITEM = 1;

    private String mTitle;
    private List<String> mList;

    public MyAdapter(String title, List<String> list) {
        mTitle = title;
        mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TITLE;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == TYPE_TITLE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_type_a, null);
            holder = new TypeAViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_type_b, null);
            holder = new TypeBViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        if (holder instanceof  TypeAViewHolder) {
            TypeAViewHolder aHolder = (TypeAViewHolder) holder;
            aHolder.titleTv.setText(mTitle);
        } else {
            TypeBViewHolder bHolder = (TypeBViewHolder) holder;
            bHolder.contentTv.setText(mList.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() + 1 : 0;
    }

    static class TypeAViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTv;

        public TypeAViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
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
