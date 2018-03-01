package com.example.tsnt.homepage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tsnt.R;

import java.util.List;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-02-19 14:11
 * @Description:
 */

public class HomePageListAdapter extends RecyclerView.Adapter<HomePageListAdapter.HomePageListViewHolder> {

    private List<ModuleEntity> moduleEntityList;

    public HomePageListAdapter(List<ModuleEntity> moduleEntityList) {
        this.moduleEntityList = moduleEntityList;
    }

    @Override
    public HomePageListAdapter.HomePageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_page_list, parent, false);
        HomePageListViewHolder viewHolder = new HomePageListViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomePageListAdapter.HomePageListViewHolder holder, int position) {
        ModuleEntity entity = moduleEntityList.get(position);
        if (entity == null) return;
        holder.moduleName.setText(entity.getName());
        holder.moduleName.setOnClickListener(entity.getOnClickListener());
    }

    @Override
    public int getItemCount() {
        return moduleEntityList == null ? 0 : moduleEntityList.size();
    }

    class HomePageListViewHolder extends RecyclerView.ViewHolder {

        private TextView moduleName;

        public HomePageListViewHolder(View itemView) {
            super(itemView);
            moduleName = (TextView) itemView.findViewById(R.id.module_name);
        }
    }
}


