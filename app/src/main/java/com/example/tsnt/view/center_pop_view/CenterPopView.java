package com.example.tsnt.view.center_pop_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tsnt.R;
import com.example.tsnt.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-05-03 20:52
 * @Description:
 */

public class CenterPopView extends FrameLayout {
    // 扩散动画的时间
    public static final int SPREAD_ANIMATION_DURATION = 300;
    // 旋转动画的时间
    public static final int ROTATION_ANIMATION_DURATION = 500;

    // 中心的x坐标
    private int centerX;
    // 中心的y坐标
    private int centerY;
    // 开始的角度, 逆时针
    private int startAngle;
    // 结束的角度, 逆时针
    private int endAngle;
    // 开始时周围view图片的边长
    private int startDiameter;
    // 结束时周围view图片的边长
    private int endDiameter;
    // 开始时中心view和周围view的距离
    private int startDistance;
    // 结束时中心view和周围view的距离
    private int endDistance;
    // 当前周围view图片的边长
    private float currDiameter;
    // 当前中心view和周围view的距离
    private float currDistance;
    // 整个view的宽度
    private int width;
    // 整个view的高度
    private int height;
    // 最终textview的高度
    private int textViewHeight;

    // 周围view的集合
    private List<View> viewList;
    // 周围view的参数集合
    private List<RroundViewEntity> entities;
    // 中心的view
    private ImageView centerView;

    private Context context;

    // 中心view的旋转动画
    private Animation centerRotateAnimation;
    // 周围view的旋转动画
    private Animation roundRotateAnimation;
    // 周围view的扩散动画
    private ValueAnimator spreadAnimator;

    public CenterPopView(Context context) {
        this(context, null);
    }

    public CenterPopView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CenterPopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initParams();
        initView();
    }

    /**
     * 初始化参数
     */
    private void initParams() {
        startAngle = 40;
        endAngle = 140;
        startDiameter = DisplayUtil.dp2px(context, 20);
        endDiameter = DisplayUtil.dp2px(context, 40);
        startDistance = DisplayUtil.dp2px(context, 40);
        endDistance = DisplayUtil.dp2px(context, 117);
        textViewHeight = DisplayUtil.dp2px(context, 16);
        viewList = new ArrayList<>();
        entities = new ArrayList<>();
        initRoundData();
    }

    /**
     * 初始化周围view的数据
     */
    private void initRoundData() {
        entities.add(new RroundViewEntity("AAAA", R.mipmap.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "AAAA is clicked", Toast.LENGTH_SHORT).show();
            }
        }));
        entities.add(new RroundViewEntity("BBBB", R.mipmap.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "BBBB is clicked", Toast.LENGTH_SHORT).show();;
            }
        }));
        entities.add(new RroundViewEntity("CCCC", R.mipmap.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "CCCC is clicked", Toast.LENGTH_SHORT).show();;
            }
        }));
        entities.add(new RroundViewEntity("DDDD", R.mipmap.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "DDDD is clicked", Toast.LENGTH_SHORT).show();;
            }
        }));
    }

    /**
     * 初始化View
     */
    private void initView() {
        // 初始化中心的View
        initCenterView();
        // 初始周围的View
        initRoundView();
    }

    /**
     * 初始化中心的View
     */
    private void initCenterView() {
        centerView = new ImageView(context);
        ViewGroup.LayoutParams centerlayoutParams = new LayoutParams(
                DisplayUtil.dp2px(context, 50),
                DisplayUtil.dp2px(context, 50));
        centerView.setLayoutParams(centerlayoutParams);
        centerView.setImageResource(R.mipmap.ic_launcher);
        addView(centerView);
    }

    /**
     * 初始周围的View
     */
    private void initRoundView() {
        for (int i = 0; i < 4; i++) {
            ViewGroup.LayoutParams roundLayoutParams = new LayoutParams(getLayoutWidth(startDiameter), getLayoutHeight(startDiameter));
            RroundViewEntity rroundViewEntity = entities.get(i);
            View roundView = LayoutInflater.from(context).inflate(R.layout.item_center_pop_view, null);
            // 设置点击事件
            roundView.setOnClickListener(rroundViewEntity.onClickListener);
            roundView.setLayoutParams(roundLayoutParams);
            // 设置图片
            ImageView imageView = (ImageView) roundView.findViewById(R.id.icon_imgv);
            imageView.setImageResource(rroundViewEntity.getResId());
            // 添加到ViewGroup
            addView(roundView);
            // 添加到view的集合
            viewList.add(roundView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        centerX = width / 2;
        centerY = height - DisplayUtil.dp2px(context, 25);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        centerView.layout(
                centerX - centerView.getWidth() / 2,
                centerY - centerView.getHeight() / 2,
                centerX + centerView.getWidth() / 2,
                centerY + centerView.getHeight() / 2);
        for (int i = 0; i < viewList.size(); i++) {
            View roundView = viewList.get(i);
            int angle = startAngle + (endAngle - startAngle) / (viewList.size() - 1) * i;
            int x = (int) (centerX + Math.cos(Math.toRadians(angle)) * currDistance);
            int y = (int) (centerY - Math.sin(Math.toRadians(angle)) * currDistance);
            roundView.layout(
                    x - roundView.getWidth() / 2,
                    y - roundView.getHeight() / 2,
                    x + roundView.getWidth() / 2,
                    y + roundView.getHeight() / 2);
        }
    }

    /**
     * 初始化中心view的旋转动画
     */
    private void initCenterRotateAnimation() {
        centerRotateAnimation = new RotateAnimation(0, 315,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        centerRotateAnimation.setDuration(SPREAD_ANIMATION_DURATION + ROTATION_ANIMATION_DURATION);
        centerRotateAnimation.setRepeatCount(0);
        centerRotateAnimation.setFillAfter(true);
    }

    /**
     * 初始化周围view的旋转动画
     */
    private void initRoundRotateAnimation() {
        float pivotYValue = endDiameter / 2.f / (endDiameter + textViewHeight);
        roundRotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, pivotYValue);
        roundRotateAnimation.setDuration(ROTATION_ANIMATION_DURATION);
        roundRotateAnimation.setRepeatCount(0);
        roundRotateAnimation.setFillAfter(true);
        roundRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                for (int i = 0; i < viewList.size(); i++) {
                    View roundView = viewList.get(i);
                    TextView nameTv = (TextView) roundView.findViewById(R.id.name_tv);
                    // 显示文字
                    nameTv.setVisibility(VISIBLE);
                    nameTv.setText(entities.get(i).name);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 初始化周围view的扩散动画
     */
    private void initRoundSpreadAnimation() {
        spreadAnimator = ValueAnimator.ofFloat(0, 100);
        spreadAnimator.setInterpolator(new AccelerateInterpolator());
        spreadAnimator.setDuration(SPREAD_ANIMATION_DURATION);
        spreadAnimator.setRepeatCount(0);
        spreadAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 更新位置
                Float value = (Float) animation.getAnimatedValue();
                currDiameter = (endDiameter - startDiameter) * (value / 100) + startDiameter;
                currDistance = (endDistance - startDistance) * (value / 100) + startDistance;
                for (int i = 0; i < viewList.size(); i++) {
                    View roundView = viewList.get(i);
                    roundView.getLayoutParams().width = getLayoutWidth(currDiameter);
                    roundView.getLayoutParams().height = getLayoutHeight(currDiameter);
                    requestLayout();
                }
            }
        });
        spreadAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 扩散动画的结尾, 开启旋转动画
                for (int i = 0; i < viewList.size(); i++) {
                    viewList.get(i).startAnimation(roundRotateAnimation);
                }
            }
        });
    }

    /**
     * 根据图片的宽度, 获取总的宽度
     *
     * @param width
     * @return
     */
    private int getLayoutWidth(float width) {
        float scale = 1.f * 48 / 40;
        int allWidth = (int) (width * scale);
        return allWidth;
    }

    /**
     * 根据图片的宽度, 获取总的高度
     *
     * @param width
     * @return
     */
    private int getLayoutHeight(float width) {
        int allWidth = getLayoutWidth(width);
        float scale = 1.f * ((endDiameter + textViewHeight) / endDiameter);
        int allHeight = (int) (allWidth * scale);
        return allHeight;
    }

    // -------------------- ↓↓↓↓ 暴露给外部调用的方法 ↓↓↓↓ --------------------

    /**
     * 开启动画
     */
    public void startAnimation() {
        // 初始化中心view的旋转动画
        initCenterRotateAnimation();
        // 初始化周围view的旋转动画
        initRoundRotateAnimation();
        // 初始化周围view的扩散动画
        initRoundSpreadAnimation();
        // 开启动画
        spreadAnimator.start();
        centerView.startAnimation(centerRotateAnimation);
    }

    /**
     * 设置中心View对应的点击事件
     *
     * @param onClickListener
     */
    public void setOnCenterViewClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            centerView.setOnClickListener(onClickListener);
        }
    }

    /**
     * 周围view对应的数据封装类
     */
    static class RroundViewEntity {
        private String name;
        private int resId;
        private View.OnClickListener onClickListener;

        public RroundViewEntity(String name, int resId, OnClickListener onClickListener) {
            this.name = name;
            this.resId = resId;
            this.onClickListener = onClickListener;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public OnClickListener getOnClickListener() {
            return onClickListener;
        }

        public void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }
    }
}
