package com.example.zephyr.finalanimation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zephyr on 2018/3/12.
 */

public class Watch extends View {

    private static final int DEFAULT_COLOR = Color.BLACK;

    private static final int CIRCLE_DEGREE = 360;

    private static final int HOUR_TAG_LENGTH = 60;

    private static final int MINUTE_TAG_LENGTH = 30;

    private static final int DEFAULT_FONT_SIZE = 30;

    private Paint mPaint;

    private Paint mHourPaint;

    private Paint mMinutePaint;

    private Rect mRect;

    private int mRadius;

    private int mDegree;

    public Watch(Context context) {
        this(context, null);
    }

    public Watch(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Watch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyleAttr) {
        mPaint = new Paint();
        mHourPaint = new Paint();
        mMinutePaint = new Paint();

        mHourPaint.setColor(Color.RED);
        mHourPaint.setAntiAlias(true);
        mHourPaint.setStrokeWidth(10);
        mHourPaint.setStyle(Paint.Style.STROKE);

        mMinutePaint.setColor(Color.BLUE);
        mMinutePaint.setAntiAlias(true);
        mMinutePaint.setStrokeWidth(5);
        mMinutePaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(DEFAULT_FONT_SIZE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mRadius = 300;
        mDegree = CIRCLE_DEGREE / 12;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode != MeasureSpec.AT_MOST) {
            setMeasuredDimension(mRadius * 2, heightSize);
        } else if (widthMode != MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, mRadius * 2);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mRadius * 2, mRadius * 2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mRect == null) {
            mRect = new Rect(0, 0, mRadius * 2, mRadius * 2);
        }
        canvas.drawCircle(mRect.exactCenterX(), mRect.exactCenterY(), mRadius, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawColor(Color.GREEN);
        mPaint.setXfermode(null);
        canvas.drawPoint(mRect.exactCenterX(), mRect.exactCenterY(), mPaint);
        canvas.drawLine(mRect.exactCenterX(), mRect.exactCenterY(), mRect.exactCenterX(), 80, mPaint);
        canvas.drawLine(mRect.exactCenterX(), mRect.exactCenterY(), mRadius * 2 - 100, mRadius, mPaint);
        drawHoursTag(canvas);
        drawMinuteTag(canvas);
    }

    private void drawHoursTag(Canvas canvas) {
        for (int i = 1 ; i <= 12; i++) {
            canvas.save();
            canvas.rotate(mDegree * i, mRect.exactCenterX(), mRect.exactCenterY());
            canvas.drawLine(mRect.exactCenterX(), 0, mRect.exactCenterX(), HOUR_TAG_LENGTH, mHourPaint);
            canvas.drawText(String.valueOf(i), mRadius - mPaint.measureText(String.valueOf(i)) / 2, HOUR_TAG_LENGTH + DEFAULT_FONT_SIZE, mPaint);
            canvas.restore();
        }
    }

    private void drawMinuteTag(Canvas canvas) {
        int minute = mDegree / 5;
        int indexes = CIRCLE_DEGREE / minute;
        for (int i = 0; i < indexes; i++) {
//            if ((i + 1) % 5 == 0) return;
            canvas.save();
            canvas.rotate(minute * i, mRect.exactCenterX(), mRect.exactCenterY());
            if (i % 5 != 0) canvas.drawLine(mRect.exactCenterX(), 0, mRect.exactCenterX(), MINUTE_TAG_LENGTH, mMinutePaint);
            canvas.restore();
        }
    }
}
