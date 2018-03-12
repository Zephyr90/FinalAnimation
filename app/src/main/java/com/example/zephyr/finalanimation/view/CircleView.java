package com.example.zephyr.finalanimation.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zephyr on 2018/3/12.
 */

public class CircleView extends View {
    private static final String TAG = "CircleView";

    private int DEFUALT_COLOR = Color.parseColor("#9400D3");

    private Paint mPaint;

    private int mRadius = 0;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(DEFUALT_COLOR);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        if (widthMode == MeasureSpec.AT_MOST && heightMode != MeasureSpec.AT_MOST) {
//            setMeasuredDimension(100, heightSize);
//        } else if (widthMode != MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(widthSize, 100);
//        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(100, 100);
//        }
        setMeasuredDimension(300, 300);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getPivotX(), getPivotY(), mRadius, mPaint);
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int radius) {
        mRadius = radius;
        invalidate();
    }

    public void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "radius", 0, 100, 150, 100, 0);
        animator.setDuration(800);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();
    }
}
