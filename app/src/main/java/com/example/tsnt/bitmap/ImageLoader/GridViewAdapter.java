package com.example.tsnt.bitmap.ImageLoader;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tsnt.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ting说你跳 on 2017/4/19.
 */

public class GridViewAdapter extends BaseAdapter {
    private List<String> mList = new ArrayList<>();

    public GridViewAdapter(List<String> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.gridview_item_imageloader, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //        ImageLoader.build(parent.getContext()).bindBitmap(mList.get(position), holder.imageView);
        holder.imageView.setImageBitmap(MyImageResizer.compressImage(parent.getResources(), R.mipmap.timg, 100, 100));
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
    }
}
