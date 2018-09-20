package com.example.tsnt.view.select_dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tsnt.R;
import com.example.tsnt.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-09-11 15:18
 * @Description:
 */

public class SelectDialog extends DialogFragment {

    private Context context;
    private LinearLayout root;
    private List<ClickEvent> clickEvents = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.style_center_pop_dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = getDialog().getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);
        return root;
    }

    private void initView() {
        // 初始化LinearLayout
        initRoot();
        // 初始化item
        initItem();
        // 初始化取消按钮
        initCancelBtn();
    }

    /**
     * 初始化LinearLayout
     */
    private void initRoot() {
        root = new LinearLayout(context);
        root.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams rootParams =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(rootParams);
        int distance_9_dp = DisplayUtil.dp2px(context, 9);
        root.setPadding(distance_9_dp
                , distance_9_dp
                , distance_9_dp
                , distance_9_dp);
    }

    /**
     * 初始化item
     */
    private void initItem() {
        for (int i = 0; i < clickEvents.size(); i++) {
            final ClickEvent clickEvent = clickEvents.get(i);
            TextView textView = new TextView(context);
            LinearLayout.LayoutParams itemParam =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(context, 44));
            textView.setLayoutParams(itemParam);
            textView.setGravity(Gravity.CENTER);
            textView.setText(clickEvent.getName());
            textView.setTextColor(clickEvent.getTextColor());
            textView.setTextSize(clickEvent.getTextSize());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickEvent.getClickListener().onClick(v);
                    dismiss();
                }
            });
            // 设置背景
            if (i == 0) {
                textView.setBackgroundResource(R.drawable.select_picture_dialog_item_top);
            } else if (i == clickEvents.size() - 1) {
                textView.setBackgroundResource(R.drawable.select_picture_dialog_item_bottom);
            } else {
                textView.setBackgroundResource(R.drawable.select_picture_dialog_item_middle);
            }
            root.addView(textView);
            // 添加分割线
            if (i < clickEvents.size() - 1) {
                View view = new View(context);
                LinearLayout.LayoutParams dividerParam =
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(context, 0.5f));
                view.setLayoutParams(dividerParam);
                view.setBackgroundColor(Color.parseColor("#eeeeee"));
                root.addView(view);
            }
        }
    }

    /**
     * 初始化取消按钮
     */
    private void initCancelBtn() {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams itemParam =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(context, 44));
        itemParam.setMargins(0
                , DisplayUtil.dp2px(context, 9)
                , 0
                , 0);
        textView.setLayoutParams(itemParam);
        textView.setText("取消");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(22);
        textView.setTextColor(Color.parseColor("#fd472b"));
        textView.setBackgroundResource(R.drawable.select_picture_dialog_cancel);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        root.addView(textView);
    }

    private void setClickEvents(List<ClickEvent> clickEvents) {
        this.clickEvents = clickEvents;
    }

    public static class Builder {

        private SelectDialog dialog;

        public Builder(Context context) {
            dialog = new SelectDialog();
            dialog.context = context;
        }

        public Builder setClickEvents(List<ClickEvent> clickEvents) {
            dialog.setClickEvents(clickEvents);
            return this;
        }

        public SelectDialog build() {
            return dialog;
        }
    }

    public static class ClickEvent {

        private String name;
        private float textSize = 22;
        private int textColor = Color.parseColor("#157efb");
        private ClickEventListener clickListener;

        public ClickEvent(String name, ClickEventListener clickListener) {
            this.name = name;
            this.clickListener = clickListener;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getTextSize() {
            return textSize;
        }

        public void setTextSize(int textSize) {
            this.textSize = textSize;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        public ClickEventListener getClickListener() {
            return clickListener;
        }

        public void setClickListener(ClickEventListener clickListener) {
            this.clickListener = clickListener;
        }

        public interface ClickEventListener {
            void onClick(View view);
        }
    }
}
