package com.example.zephyr.finalanimation.interpolator;

import android.animation.TimeInterpolator;

/**
 * Created by zephyr on 2018/3/12.
 */

public class DecelerateAccelerateInterpolator implements TimeInterpolator{
    @Override
    public float getInterpolation(float input) {
        float x=2.0f*input-1.0f;
        return 0.5f*(x*x*x + 1.0f);    }
}
