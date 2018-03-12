package com.example.zephyr.finalanimation.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.zephyr.finalanimation.interpolator.DecelerateAccelerateInterpolator;

/**
 * Created by zephyr on 2018/3/9.
 */

public class BounceBall extends View {
    private static final String TAG = "BounceBall";

    private int COLOR_DEFAULT = Color.RED;

    private int COLOR_BLUE = Color.BLUE;

    private int mColor = COLOR_DEFAULT;

    private static int RADIUS = 50;

    private Paint mPaint;

    private Rect mRect = new Rect(0, 0, 100, 100);

    private ValueAnimator mValueAnimator;

    private boolean isCircle = true;

    public BounceBall(Context context) {
        super(context);
        init(context);
    }

    public BounceBall(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BounceBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setColor(COLOR_DEFAULT);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode != MeasureSpec.AT_MOST) {
            setMeasuredDimension(100, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST && widthMode != MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, 100);
        } else if (heightMode == MeasureSpec.AT_MOST && widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(100, 100);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mValueAnimator == null) startAnimation();
        if (isCircle) {
            canvas.drawCircle(mRect.exactCenterX(), mRect.exactCenterY(), RADIUS, mPaint);
        } else {
            canvas.drawRect(mRect, mPaint);
        }
    }

    public void setPaintColor(int color) {
        mPaint.setColor(color);
    }

    public boolean isCircle() {
        return isCircle;
    }

    public void setCircle(boolean circle) {
        isCircle = circle;
    }

    private void startAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(((int) getY()), 0, ((int) getY()));
        mValueAnimator = valueAnimator;
        valueAnimator.setDuration(800);
        valueAnimator.setInterpolator(new DecelerateAccelerateInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue();
            layout(getLeft()
                    , animatedValue
                    , getRight()
                    , getHeight() + animatedValue);
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                Log.d(TAG, "onAnimationRepeat: ");
                if (isCircle()) {
                    mColor = COLOR_DEFAULT;
                    setCircle(false);
                } else {
                    mColor = COLOR_BLUE;
                    setCircle(true);
                }
                setPaintColor(mColor);
                invalidate();
            }
        });
        valueAnimator.start();
    }

}
