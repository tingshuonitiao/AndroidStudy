package com.example.tsnt.view.round_angle_imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.example.tsnt.R;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-06-12 16:15
 * @Description:
 */

public class RoundAngleImageView extends AppCompatImageView {

    private int roundWidth = 5;

    public RoundAngleImageView(Context context) {
        super(context);
        init(context, null);
    }

    public RoundAngleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundAngleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.RoundAngleImageView);
            roundWidth = a.getDimensionPixelSize(
                    R.styleable.RoundAngleImageView_roundWidth, roundWidth);
        } else {
            float density = context.getResources().getDisplayMetrics().density;
            roundWidth = (int) (roundWidth * density);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (width > roundWidth && height > roundWidth) {
            Path path = new Path();
            path.moveTo(roundWidth, 0);
            path.lineTo(width - roundWidth, 0);
            path.quadTo(width, 0, width, roundWidth);
            path.lineTo(width, height - roundWidth);
            path.quadTo(width, height, width - roundWidth, height);
            path.lineTo(roundWidth, height);
            path.quadTo(0, height, 0, height - roundWidth);
            path.lineTo(0, roundWidth);
            path.quadTo(0, 0, roundWidth, 0);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
