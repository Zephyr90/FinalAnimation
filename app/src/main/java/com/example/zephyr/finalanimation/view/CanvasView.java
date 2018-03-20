package com.example.zephyr.finalanimation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.zephyr.finalanimation.R;

/**
 * Created by zephyr on 2018/3/14.
 */

public class CanvasView extends View {
    private static final String TAG = "CanvasView";

    private Bitmap mBg;
    private Paint mPaint;
    private float mLastX;
    private float mLastY;
    private float mCenterX = 200;
    private float mCenterY = 200;
    private float mRadius = 200;
    private int mScaledTouchSlop;
    private float mDistanceX;
    private float mDistanceY;
    private VelocityTracker mVelocityTracker;

    public CanvasView(Context context) {
        super(context);
        init(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mBg = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);
        mPaint = new Paint();
        Shader shader = new BitmapShader(mBg, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        Log.d(TAG, "onTouchEvent: x: " + x + " , y: " + y);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                if ((x - mLastX ) != x && (y - mLastY) != y) return false;
                break;
            case MotionEvent.ACTION_MOVE:
                mDistanceX = x - mLastX;
                mDistanceY = y - mLastY;

                mVelocityTracker.addMovement(event);
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                float yVelocity = mVelocityTracker.getYVelocity();

                if (Math.abs(mDistanceX) >= mScaledTouchSlop || Math.abs(mDistanceY) >= mScaledTouchSlop) {
                    mCenterX = x + mDistanceX;
                    mCenterY = y + mDistanceY;
                } else if (Math.abs(xVelocity) >= 100 || Math.abs(yVelocity) >= 100) {
                    mCenterX = x;
                    mCenterY = y;
                }

                if (mCenterX < mRadius) {
                    mCenterX = mRadius;
                } else if (mCenterX > getWidth() - mRadius) {
                    mCenterX = getWidth() - mRadius;
                }

                if (mCenterY < mRadius) {
                    mCenterY = mRadius;
                } else if (mCenterY > getHeight() - mRadius) {
                    mCenterY = getHeight() - mRadius;
                }

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        mLastX = event.getX();
        mLastY = event.getY();
        return true;
    }
}
