package com.example.zephyr.finalanimation.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by zephyr on 2017/12/28.
 * 代码实现view动画
 */

public class AnimationHelper {

    public static void scale(View view) {
//        Animation.ABSOLUTE,缩放中心距离view左上角具体长度的类型
//        Animation.RELATIVE_TO_SELF,缩放中心距离view左上角用view自身的比例表示
//        Animation.RELATIVE_TO_PARENT，缩放中心距离view左上角用占父视图的比例表示
        ScaleAnimation animation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        view.startAnimation(animation);
    }

    public static void translate(View view) {
        TranslateAnimation animation = new TranslateAnimation(Animation.ABSOLUTE, 40.0f,
                Animation.ABSOLUTE, 40.0f, Animation.ABSOLUTE, 40.0f, Animation.ABSOLUTE, 40.0f);
        animation.setDuration(1000);
        view.startAnimation(animation);
    }

    public static void alpha(View view) {
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(1000);
        view.startAnimation(animation);
    }

    public static void rotate(View view) {
        RotateAnimation animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        view.startAnimation(animation);
    }


}
