package com.example.tsnt.homepage;

import android.view.View;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-02-19 14:20
 * @Description:
 */

public class ModuleEntity {

    private String name;

    private View.OnClickListener onClickListener;

    public ModuleEntity(String name, View.OnClickListener onClickListener) {
        this.name = name;
        this.onClickListener = onClickListener;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
