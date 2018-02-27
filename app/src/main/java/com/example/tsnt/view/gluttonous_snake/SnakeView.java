package com.example.tsnt.view.gluttonous_snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.tsnt.utils.DisplayUtil;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-02-23 20:37
 * @Description: Gluttonous Snake
 */

public class SnakeView extends View {

    private static String TAG = SnakeView.class.getSimpleName();

    private Context context;

    // 是否初始化
    private boolean isInitial = false;
    // 是否可以改变方向, 每走一步之后可以改变一次方向
    private boolean canChangeDirection = false;

    // 安全区域的宽度
    private int fieldWidth;
    // 安全区域的长度
    private int fieldHeight;
    // 安全区域的行数
    private int rowNum;
    // 安全区域的列数
    private int columnNum;
    // 安全区域的上边距
    private float topPadding;
    // 安全区域的下边距
    private float bottomPadding;
    // 安全区域的左边距
    private float leftPadding;
    // 安全区域的右边距
    private float rightPadding;

    // 边界的画笔
    private Paint boundaryPaint;
    // 边界的颜色
    private static final int BOUNDARY_COLOR = Color.RED;

    // 蛇所处的路径, 其中存储的元素代表网格的索引(按从左到右，从上到下排序所得)
    private LinkedList<Integer> paths;
    // 蛇身的宽度
    private int bodyWidth;
    // 蛇的画笔
    private Paint snakePaint;
    // 蛇的颜色
    private static final int SNAKE_COLOR = Color.GREEN;
    // 蛇前进的方向
    private int direction = Direction.RIGHT;

    // 食物所处的位置, 代表网格的索引(按从左到右，从上到下排序所得)
    private int food;
    // 食物的画笔
    private Paint foodPaint;
    // 食物的颜色
    private static final int FOOD_COLOR = Color.YELLOW;

    // 蛇的速度, 这里指蛇移动一格所需的时间
    private int velocity;

    private SnakeViewHandler snakeViewHandler;

    public SnakeView(Context context) {
        this(context, null);
    }

    public SnakeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnakeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        initPaint();
        // 蛇移动的速度
        velocity = 500;
        // 设置蛇身宽度
        bodyWidth = DisplayUtil.dp2px(context, 20);
        // 初始化蛇身路径
        paths = new LinkedList<>();
        // View加载完成后的操作
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 移除监听，因为会多次回调
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // 初始化Handler
                snakeViewHandler = new SnakeViewHandler(SnakeView.this);
                snakeViewHandler.sendEmptyMessageDelayed(Order.GO, velocity);
            }
        });
    }

    // 初始化画笔
    private void initPaint() {
        // 初始化蛇的画笔
        snakePaint = new Paint();
        snakePaint.setColor(SNAKE_COLOR);
        // 初始边界的画笔
        boundaryPaint = new Paint();
        boundaryPaint.setColor(BOUNDARY_COLOR);
        // 初始食物的画笔
        foodPaint = new Paint();
        foodPaint.setColor(FOOD_COLOR);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        fieldWidth = MeasureSpec.getSize(widthMeasureSpec);
        fieldHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (!isInitial) {
            isInitial = true;
            divideField();
            // 设置初始时蛇身的路径
            int row = rowNum / 2;
            int column = columnNum / 2;
            for (int i = 0; i < 3; i++) {
                int index = row * columnNum + column - 3 + i;
                paths.addFirst(index);
            }
            // 设置初始时食物位置
            food = 2 * columnNum + 1;
        }
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(fieldWidth, MeasureSpec.EXACTLY)
                , MeasureSpec.makeMeasureSpec(fieldHeight, MeasureSpec.EXACTLY));
    }

    // 划分场地
    private void divideField() {
        // 以一个边长为蛇身宽度的正方形为一个单位，得到 rowNum * columnNum 个网格
        rowNum = fieldHeight / bodyWidth;
        columnNum = fieldWidth / bodyWidth;
        topPadding = (fieldHeight - rowNum * bodyWidth) / 2;
        bottomPadding = (fieldHeight - rowNum * bodyWidth) / 2;
        leftPadding = (fieldWidth - columnNum * bodyWidth) / 2;
        rightPadding = (fieldWidth - columnNum * bodyWidth) / 2;
        // 确保边界存在
        if (topPadding == 0) {
            rowNum -= 2;
            topPadding = bodyWidth;
            bottomPadding = bodyWidth;
        }
        if (leftPadding == 0) {
            columnNum -= 2;
            leftPadding = bodyWidth;
            rightPadding = bodyWidth;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 根据保存的路径画出蛇身
        ListIterator<Integer> iterator = paths.listIterator();
        while ((iterator.hasNext())) {
            // 得到一段蛇身在场地中位于第几个网格
            int index = iterator.next();
            drawCell(canvas, snakePaint, index);
        }

        // 画出食物位置
        drawCell(canvas, foodPaint, food);

        // ---------- 画边界区域 ----------
        // 画出上边界
        canvas.drawRect(0, 0, fieldWidth, topPadding, boundaryPaint);
        // 画出下边界
        canvas.drawRect(0, fieldHeight - bottomPadding, fieldWidth, fieldHeight, boundaryPaint);
        // 画出左边界
        canvas.drawRect(0, 0, leftPadding, fieldHeight, boundaryPaint);
        // 画出右边界
        canvas.drawRect(fieldWidth - rightPadding, 0, fieldWidth, fieldHeight, boundaryPaint);
    }

    /**
     * 根据网格的索引填充网格
     *
     * @param canvas 画布
     * @param paint  画笔
     * @param index  网格的索引
     */
    private void drawCell(Canvas canvas, Paint paint, int index) {
        // 判断该网格在场地哪一行
        int row = index / columnNum;
        // 判断该网格在场地哪一列
        int column = index - row * columnNum;

        float left = leftPadding + column * bodyWidth;
        float top = topPadding + row * bodyWidth;
        float right = left + bodyWidth;
        float bottom = top + bodyWidth;
        canvas.drawRect(left, top, right, bottom, paint);
    }

    // 蛇爬行一步
    private void goOneStep(int direction) {
        int oldHeadIndex = paths.getFirst();
        int newHeadIndex;
        switch (direction) {
            case Direction.UP:
                newHeadIndex = oldHeadIndex - columnNum;
                // 添加新的蛇头
                paths.addFirst(newHeadIndex);
                if (newHeadIndex == food) {
                    // 如果食物被吃, 创建新的食物
                    createFood();
                } else {
                    // 去除原来的蛇尾
                    paths.removeLast();
                }
                break;
            case Direction.DOWN:
                newHeadIndex = oldHeadIndex + columnNum;
                // 添加新的蛇头
                paths.addFirst(newHeadIndex);
                if (newHeadIndex == food) {
                    // 如果食物被吃, 创建新的食物
                    createFood();
                } else {
                    // 去除原来的蛇尾
                    paths.removeLast();
                }
                break;
            case Direction.LEFT:
                newHeadIndex = oldHeadIndex - 1;
                // 添加新的蛇头
                paths.addFirst(newHeadIndex);
                if (newHeadIndex == food) {
                    // 如果食物被吃, 创建新的食物
                    createFood();
                } else {
                    // 去除原来的蛇尾
                    paths.removeLast();
                }
                break;
            case Direction.RIGHT:
                newHeadIndex = oldHeadIndex + 1;
                // 添加新的蛇头
                paths.addFirst(newHeadIndex);
                if (newHeadIndex == food) {
                    // 如果食物被吃, 创建新的食物
                    createFood();
                } else {
                    // 去除原来的蛇尾
                    paths.removeLast();
                }
                break;
        }
        // 刷新界面
        postInvalidate();
        canChangeDirection = true;
        snakeViewHandler.sendEmptyMessageDelayed(Order.GO, velocity);
    }

    // 在随机的位置产生食物
    private void createFood() {
        // 没被蛇身占领的网格格数
        int numOfEmptyCell = rowNum * columnNum - paths.size();
        // 食物占第几个空网格
        int indexOfFood = (int) (Math.random() * numOfEmptyCell) + 1;
        int count = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                int realIndex = i * columnNum + j;
                if (!paths.contains(realIndex)) {
                    // 如果没被蛇身经过
                    count++;
                }
                if (count == indexOfFood) {
                    // 找到随机的索引所在位置，在currIndex处产生食物
                    food = realIndex;
                    Log.d(TAG, "index of food is " + food);
                    break;
                }
            }
        }
    }

    // 获取蛇的前进方向
    private int getDirection() {
        return direction;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 释放资源，避免内存泄露
        snakeViewHandler.detachFromSnakeView();
        snakeViewHandler = null;
    }

    // ---------- 以下为暴露给外部使用的方法 ----------

    // 向上转
    public void turnUp() {
        if (canChangeDirection && (direction == Direction.LEFT || direction == Direction.RIGHT)) {
            canChangeDirection = false;
            direction = Direction.UP;
        }
    }

    // 向下转
    public void turnDown() {
        if (canChangeDirection && (direction == Direction.LEFT || direction == Direction.RIGHT)) {
            canChangeDirection = false;
            direction = Direction.DOWN;
        }
    }

    // 向左转
    public void turnLeft() {
        if (canChangeDirection && (direction == Direction.UP || direction == Direction.DOWN)) {
            canChangeDirection = false;
            direction = Direction.LEFT;
        }
    }

    // 向右转
    public void turnRight() {
        if (canChangeDirection && (direction == Direction.UP || direction == Direction.DOWN)) {
            canChangeDirection = false;
            direction = Direction.RIGHT;
        }
    }

    // ---------- 辅助类 ----------

    // 指令
    static class Order {
        public static final int GO = 0;
        public static final int STOP = 1;
    }

    // 方向
    static class Direction {
        public static final int UP = 0;
        public static final int DOWN = 1;
        public static final int LEFT = 2;
        public static final int RIGHT = 3;
    }

    // 处理循环事件自定义的Handler
    static class SnakeViewHandler extends Handler {

        private SnakeView snakeView;

        public SnakeViewHandler(SnakeView snakeView) {
            this.snakeView = snakeView;
        }

        // 资源回收
        public void detachFromSnakeView() {
            snakeView = null;
            removeCallbacksAndMessages(null);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage");
            if (msg.what == Order.GO) {
                int direction = snakeView.getDirection();
                snakeView.goOneStep(direction);
            } else if (msg.what == Order.STOP) {

            }
        }
    }
}
