package com.example.tsnt.bitmap.ImageLoader;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
    private MyImageLoader mMyImageLoader;
    private Context mContext;
    private final int mImageWidth;
    private boolean mCanBeUpdated;
    private Drawable mDefaultBitmapDrawable;

    public GridViewAdapter(List<String> list, Context context) {
        mList = list;
        mContext = context;
        mDefaultBitmapDrawable = context.getResources().getDrawable(R.mipmap.image_default);
        if (mMyImageLoader == null) {
            mMyImageLoader = MyImageLoader.build(context);
        }
        int screenWidth = MyUtils.getScreenMetrics(mContext).widthPixels;
        int space = (int) MyUtils.dp2px(mContext, 20f);
        mImageWidth = (screenWidth - space) / 3;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
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
            convertView = View.inflate(mContext, R.layout.gridview_item_imageloader, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String tag = (String) holder.imageView.getTag();
        final String uri = getItem(position);
        if (!uri.equals(tag)) {
            holder.imageView.setImageDrawable(mDefaultBitmapDrawable);
        }
        if (mCanBeUpdated) {
            holder.imageView.setTag(uri);
            mMyImageLoader.bindBitmap(mList.get(position), holder.imageView, mImageWidth, mImageWidth);
        }
        return convertView;
    }

    public void setCanBeUpdated(boolean canBeUpdated) {
        mCanBeUpdated = canBeUpdated;
    }

    private static class ViewHolder {
        ImageView imageView;
    }
}
