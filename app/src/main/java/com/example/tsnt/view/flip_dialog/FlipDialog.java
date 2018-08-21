package com.example.tsnt.view.flip_dialog;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.tsnt.R;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-08-21 15:52
 * @Description:
 */

public class FlipDialog extends Dialog {

    private int times = 0;

    private Context context;

    private ImageView imgNo1;
    private ImageView imgNo2;
    private RelativeLayout container;

    public FlipDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);
    }

    private void initView() {
        setContentView(R.layout.dialog_flip);
        imgNo1 = (ImageView) findViewById(R.id.img_no_1);
        imgNo2 = (ImageView) findViewById(R.id.img_no_2);
        container = (RelativeLayout) findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (times == 0) {
                    flipCard();
                    times++;
                } else {
                    dismiss();
                }
            }
        });
    }

    @SuppressLint("ResourceType")
    private void flipCard() {
        AnimatorSet animatorIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.anim.flip_anim_in);
        AnimatorSet animatorOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.anim.flip_anim_out);
        animatorIn.setTarget(imgNo1);
        animatorOut.setTarget(imgNo2);
        animatorIn.start();
        animatorOut.start();
    }

    public static class Builder {

        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public FlipDialog create() {
            return new FlipDialog(context,R.style.style_flip_dialog);
        }
    }
}
