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
import android.view.animation.DecelerateInterpolator;
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
 * @Author: tingshuonitiao
 * @Date: 2018-05-03 20:52
 * @Description:
 */

public class CenterPopView extends FrameLayout {

    // 周围view扩散动画的时间
    public static final int ROUND_SPREAD_ANIMATION_DURATION = 300;
    // 第一次周围view旋转动画的时间
    public static final int FIRST_ROUND_ROTATION_ANIMATION_DURATION = 400;
    // 中心View旋转动画的时间
    public static final int CENTER_ROTATION_ANIMATION_DURATION = ROUND_SPREAD_ANIMATION_DURATION + FIRST_ROUND_ROTATION_ANIMATION_DURATION;
    // 周围view抖动动画的时间
    public static final int ROUND_SHAKE_ANIMATION_DURATION = 200;
    // 第二次周围view旋转动画的时间, 因为抖动的同时旋转, 所以时间一样
    public static final int SECOND_ROUND_ROTATION_ANIMATION_DURATION = ROUND_SHAKE_ANIMATION_DURATION;
    // 周围view收缩动画的时间
    public static final int ROUND_SHRINK_ANIMATION_DURATION = 200;
    // 第二次中心View旋转动画的时间
    public static final int SECOND_CENTER_ROTATION_ANIMATION_DURATION = ROUND_SHAKE_ANIMATION_DURATION + ROUND_SHRINK_ANIMATION_DURATION;

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
    private int shakeDistance;
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
    // 周围view的扩散动画
    private ValueAnimator spreadAnimator;
    // 周围view的抖动动画
    private ValueAnimator roundShakeAnimator;
    // 第一次周围view的旋转动画
    private Animation firstRoundRotateAnimation;
    // 第二次周围view的旋转动画
    private Animation secondRoundRotateAnimation;
    // 周围view的收缩动画
    private ValueAnimator roundShrinkAnimator;
    // 第二次中心view的旋转动画
    private Animation secondCenterRotateAnimation;

    // 中心view的点击事件
    private OnCenterClickListener onCenterClickListener;

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
        shakeDistance = DisplayUtil.dp2px(context, 120);
        textViewHeight = DisplayUtil.dp2px(context, 16);
        viewList = new ArrayList<>();
        entities = new ArrayList<>();
        initRoundData();
    }

    /**
     * 初始化周围view的数据
     */
    private void initRoundData() {
        entities.add(new RroundViewEntity("AAAA", R.mipmap.icon_aaaa, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "AAAA is clicked", Toast.LENGTH_SHORT).show();
            }
        }));
        entities.add(new RroundViewEntity("BBBB", R.mipmap.icon_bbbb, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "BBBB is clicked", Toast.LENGTH_SHORT).show();
                ;
            }
        }));
        entities.add(new RroundViewEntity("CCCC", R.mipmap.icon_cccc, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "CCCC is clicked", Toast.LENGTH_SHORT).show();
                ;
            }
        }));
        entities.add(new RroundViewEntity("DDDD", R.mipmap.icon_dddd, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "DDDD is clicked", Toast.LENGTH_SHORT).show();
                ;
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
        // 初始化动画
        initAnimation();
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
        centerView.setImageResource(R.mipmap.icon_add);
        addView(centerView);
        centerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startShrink();
            }
        });
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

    /**
     * 初始化动画
     */
    private void initAnimation() {
        // 初始化中心view的旋转动画
        initCenterRotateAnimation();
        // 初始化周围view的扩散动画
        initRoundSpreadAnimation();
        // 初始化周围view的抖动动画
        initRoundShakeAnimation();
        // 初始化第一次周围view的旋转动画
        initFirstRoundRotateAnimation();
        // 初始化第二次周围view的旋转动画
        initSecondRoundRotateAnimation();
        // 初始化周围view的收缩动画
        initRoundShrinkAnimation();
        // 初始化中心view第二次旋转动画
        initSecondCenterRotateAnimation();
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
        centerRotateAnimation = new RotateAnimation(0, -315,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        centerRotateAnimation.setDuration(CENTER_ROTATION_ANIMATION_DURATION);
        centerRotateAnimation.setRepeatCount(0);
        centerRotateAnimation.setFillAfter(true);
    }

    /**
     * 初始化周围view的扩散动画
     */
    private void initRoundSpreadAnimation() {
        spreadAnimator = ValueAnimator.ofFloat(0, 1.f);
        spreadAnimator.setInterpolator(new AccelerateInterpolator());
        spreadAnimator.setDuration(ROUND_SPREAD_ANIMATION_DURATION);
        spreadAnimator.setRepeatCount(0);
        spreadAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 更新位置
                Float value = (Float) animation.getAnimatedValue();
                currDiameter = (endDiameter - startDiameter) * value + startDiameter;
                currDistance = (endDistance - startDistance) * value + startDistance;
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
                    viewList.get(i).startAnimation(firstRoundRotateAnimation);
                }
                roundShakeAnimator.start();
            }
        });
    }

    /**
     * 初始化周围view的抖动动画
     */
    private void initRoundShakeAnimation() {
        roundShakeAnimator = new ValueAnimator().ofFloat(0, 1.f);
        roundShakeAnimator.setInterpolator(new DecelerateInterpolator());
        roundShakeAnimator.setDuration(ROUND_SHAKE_ANIMATION_DURATION / 4);
        roundShakeAnimator.setRepeatCount(3);
        roundShakeAnimator.setRepeatMode(ValueAnimator.REVERSE);
        roundShakeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 更新位置
                Float value = (Float) animation.getAnimatedValue();
                currDistance = (shakeDistance - endDistance) * value + endDistance;
                requestLayout();
            }
        });
    }

    /**
     * 初始化第一次周围view的旋转动画
     */
    private void initFirstRoundRotateAnimation() {
        float pivotYValue = endDiameter / 2.f / (endDiameter + textViewHeight);
        firstRoundRotateAnimation = new RotateAnimation(180, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, pivotYValue);
        firstRoundRotateAnimation.setDuration(FIRST_ROUND_ROTATION_ANIMATION_DURATION);
        firstRoundRotateAnimation.setRepeatCount(0);
        firstRoundRotateAnimation.setFillAfter(true);
        firstRoundRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
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
     * 初始化第二次周围view的旋转动画
     */
    private void initSecondRoundRotateAnimation() {
        float pivotYValue = endDiameter / 2.f / (endDiameter + textViewHeight);
        secondRoundRotateAnimation = new RotateAnimation(-270, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, pivotYValue);
        secondRoundRotateAnimation.setDuration(SECOND_ROUND_ROTATION_ANIMATION_DURATION);
        secondRoundRotateAnimation.setRepeatCount(0);
        secondRoundRotateAnimation.setFillAfter(true);
        secondRoundRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                for (int i = 0; i < viewList.size(); i++) {
                    View roundView = viewList.get(i);
                    TextView nameTv = (TextView) roundView.findViewById(R.id.name_tv);
                    // 隐藏文字
                    nameTv.setVisibility(INVISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                roundShrinkAnimator.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 初始化周围view的收缩动画
     */
    private void initRoundShrinkAnimation() {
        roundShrinkAnimator = ValueAnimator.ofFloat(1.f, 0);
        roundShrinkAnimator.setInterpolator(new AccelerateInterpolator());
        roundShrinkAnimator.setDuration(ROUND_SHRINK_ANIMATION_DURATION);
        roundShrinkAnimator.setRepeatCount(0);
        roundShrinkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 更新位置
                Float value = (Float) animation.getAnimatedValue();
                currDiameter = (endDiameter - startDiameter) * value + startDiameter;
                currDistance = (endDistance - startDistance) * value + startDistance;
                for (int i = 0; i < viewList.size(); i++) {
                    View roundView = viewList.get(i);
                    roundView.getLayoutParams().width = getLayoutWidth(currDiameter);
                    roundView.getLayoutParams().height = getLayoutHeight(currDiameter);
                    requestLayout();
                }
            }
        });
        roundShrinkAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                onCenterClickListener.onCenterClick();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 初始化中心view第二次旋转动画
     */
    private void initSecondCenterRotateAnimation() {
        secondCenterRotateAnimation = new RotateAnimation(-315, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        secondCenterRotateAnimation.setDuration(SECOND_CENTER_ROTATION_ANIMATION_DURATION);
        secondCenterRotateAnimation.setRepeatCount(0);
        secondCenterRotateAnimation.setFillAfter(true);
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
     * 开始膨胀
     */
    public void startPop() {
        // 开启动画
        spreadAnimator.start();
        centerView.startAnimation(centerRotateAnimation);
    }

    /**
     * 开始收缩
     */
    public void startShrink() {
        // 开启动画
        for (int i = 0; i < viewList.size(); i++) {
            viewList.get(i).startAnimation(secondRoundRotateAnimation);
        }
        centerView.startAnimation(secondCenterRotateAnimation);
    }

    /**
     * 设置中心View对应的点击事件
     *
     * @param clickListener
     */
    public void setOnCenterClickListener(OnCenterClickListener clickListener) {
        onCenterClickListener = clickListener;
    }

    public interface OnCenterClickListener {
        void onCenterClick();
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
