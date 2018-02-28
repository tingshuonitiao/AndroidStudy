package com.example.tsnt.view.self_adapting_view_pager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ting说你跳 on 2017/3/12.
 */

public class SelfAdaptingViewPager extends ViewPager {
    private Map<Integer, Integer> map             = new HashMap<>(2);
    private int                   currentPosition = 0;
    private MarginLayoutParams params;

    public SelfAdaptingViewPager(Context context) {
        super(context);
    }

    public SelfAdaptingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //在View的绘制过程中此方法会被多次调用,每次都会测量出新的数据,我们用最新的数据替换旧的数据
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            Log.d("SelfAdaptingViewPager", "measuredHeight=" + h);
            Log.d("SelfAdaptingViewPager", "getChildCount=" + getChildCount());
            Log.d("SelfAdaptingViewPager", "getChildAt(i)=" + i);
           /* Log.d("SelfAdaptingViewPager", "getChildAt(i).getId())=" + getChildAt(i).getId());
            if (Build.VERSION.SDK_INT > 19) {
                map.put(Math.abs(i - 1), h);
            } else {*/
            map.put(i, h);
            //            }
        }
        int height = 0;
        //onMeasure第一次被调用的时候,遍历不到child(View的绘制流程只是了解最基本的，我就不解释了)
        height = map.get(currentPosition) == null ? 0 : map.get(currentPosition);
        Log.d("SelfAdaptingViewPager", "height=" + height);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //在切换tab的时候，重置ViewPager的高度
    public void resetHeight(int currentPosition) {
        this.currentPosition = currentPosition;
        params = (MarginLayoutParams) getLayoutParams();
        if (params == null) {
            params = new MarginLayoutParams(LayoutParams.MATCH_PARENT, map.get(currentPosition));
        } else {
            params.height = map.get(currentPosition);
        }
        setLayoutParams(params);
    }
}
