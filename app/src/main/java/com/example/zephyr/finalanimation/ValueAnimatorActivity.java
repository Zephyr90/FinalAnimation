package com.example.zephyr.finalanimation;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zephyr.finalanimation.view.BounceBall;
import com.example.zephyr.finalanimation.view.CircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * ValueAnimator
 * ValueAnimator用于对值的操作，比如int，float等。
 * 方法：ofInt， ofFloat
 */
public class ValueAnimatorActivity extends BaseActivity {
    private static final String TAG = "ValueAnimatorActivity";

    @BindView(R.id.tv_target_of_int)
    TextView mTvTarget;
    @BindView(R.id.activity_value_animator)
    LinearLayoutCompat mActivityValueAnimator;
    @BindView(R.id.btn_start_of_int)
    Button mBtnStartOfInt;
    @BindView(R.id.btn_start_of_argb)
    Button mBtnStartOfArgb;
    @BindView(R.id.tv_target_of_argb)
    TextView mTvTargetOfArgb;
    @BindView(R.id.btn_start_of_float)
    Button mBtnStartOfFloat;
    @BindView(R.id.tv_target_of_float)
    TextView mTvTargetOfFloat;
    @BindView(R.id.tv_target_of_float_progress)
    ProgressBar mTvTargetOfFloatProgress;
    @BindView(R.id.btn_progress)
    Button mBtnProgress;
    @BindView(R.id.view_ball)
    BounceBall mViewBall;
    @BindView(R.id.btn_change_radius)
    Button mBtnChangeRadius;
    @BindView(R.id.view_circle)
    CircleView mViewCircle;
    @BindView(R.id.btn_change_bg)
    Button mBtnChangeBg;
    @BindView(R.id.tv_change_bg)
    TextView mTvChangeBg;

    public static void start(Activity context) {
        Intent intent = new Intent(context, ValueAnimatorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start_of_int)
    public void onOfInt() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(10, 0);
//        ValueAnimator.ofInt(10, 20, 30, 10, 15, 20); // 可以取多个值
        valueAnimator.setDuration(5000);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            mTvTarget.setText(String.valueOf(value));
        });
        valueAnimator.start();
    }

    @OnClick(R.id.btn_start_of_argb)
    public void onOfArgb() {
        ValueAnimator animator = ValueAnimator.ofArgb(Color.BLACK, Color.WHITE);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            int color = (int) animation.getAnimatedValue();
            mTvTargetOfArgb.setBackgroundColor(color);
        });
        animator.start();
    }

    @OnClick(R.id.btn_start_of_float)
    public void ofFloat() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(5000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            animatedValue = animatedValue * 100;
            mTvTargetOfFloat.setText(animatedValue + "%");
            mTvTargetOfFloatProgress.setProgress(((int) animatedValue));
        });
        animator.start();
    }

    @OnClick(R.id.btn_progress)
    public void onProgressClick() {
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(((int) mViewBall.getY()), 0, ((int) mViewBall.getY()));
//        valueAnimator.setDuration(800);
//        valueAnimator.setInterpolator(new DecelerateAccelerateInterpolator());
//        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
//        valueAnimator.addUpdateListener(animation -> {
//            int animatedValue = (int) animation.getAnimatedValue();
//            mViewBall.layout(mViewBall.getLeft()
//                    , animatedValue
//                    , mViewBall.getRight()
//                    , mViewBall.getHeight() + animatedValue);
//        });
//        valueAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                super.onAnimationRepeat(animation);
//                Log.d(TAG, "onAnimationRepeat: ");
//                if (mViewBall.isCircle()) {
//                    mViewBall.setCircle(false);
//                } else {
//                    mViewBall.setCircle(true);
//                }
//                mViewBall.invalidate();
//            }
//        });
//        valueAnimator.start();
    }

    @OnClick(R.id.btn_change_radius)
    public void onChangeRadius() {
        mViewCircle.startAnimation();
    }

    @OnClick(R.id.btn_change_bg)
    public void onChangeBg() {
        ObjectAnimator animator = ObjectAnimator.ofInt(mTvChangeBg, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setEvaluator(new ArgbEvaluator());
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.start();
    }
}
