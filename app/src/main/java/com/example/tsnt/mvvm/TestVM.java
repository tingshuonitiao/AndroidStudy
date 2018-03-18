package com.example.tsnt.mvvm;

import android.databinding.ObservableField;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-03-17 18:05
 * @Description:
 */

public class TestVM {
    public ObservableField<String> bottomContent = new ObservableField<>();

    public TestVM() {
        bottomContent.set("Hello, I'm bottomContent");
    }
}
