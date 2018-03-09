package com.example.zephyr.finalanimation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zephyr on 2018/3/9.
 */

public class BounceBall extends View {

    private int COLOR_DEFAULT = Color.RED;

    private static int RADIUS = 50;

    private Paint mPaint;

    private Rect mRect = new Rect(0, 0, 100, 100);

    private int mIndex;

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
    protected void onDraw(Canvas canvas) {
//        if (isEven(getIndex())) {
//            canvas.drawRect(mRect, mPaint);
//        } else {
//            canvas.drawCircle(mRect.exactCenterX(), mRect.exactCenterY(), RADIUS, mPaint);
//        }
        canvas.drawRect(mRect, mPaint);
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    /**
     * 是否为偶数
     * @param number
     * @return
     */
    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
