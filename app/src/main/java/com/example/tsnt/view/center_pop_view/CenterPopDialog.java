package com.example.tsnt.view.center_pop_view;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.tsnt.R;
import com.example.tsnt.utils.DisplayUtil;

import java.util.List;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-05-03 21:02
 * @Description:
 */

public class CenterPopDialog extends DialogFragment {

    private CenterPopView centerPopView;

    List<CenterPopView.RroundViewEntity> entities;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.style_center_pop_dialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.view_center_pop, container, false);
        // 设置dialog参数
        setDialogWindow();
        return view;
    }

    /**
     * 设置dialog参数
     */
    private void setDialogWindow() {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = getDialog().getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = view.getContext();
        // 动态添加一个CenterPopView
        centerPopView = new CenterPopView(context);
        centerPopView.init(entities);
        ((FrameLayout) view).addView(centerPopView, ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(context, 180));
        centerPopView.setOnCenterClickListener(new CenterPopView.OnCenterClickListener() {
            @Override
            public void onCenterClick() {
                dismiss();
            }
        });
        centerPopView.startPop();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        // 资源回收
        centerPopView.recycle();
    }

    public void setData(List<CenterPopView.RroundViewEntity> list) {
        entities = list;
    }
}
