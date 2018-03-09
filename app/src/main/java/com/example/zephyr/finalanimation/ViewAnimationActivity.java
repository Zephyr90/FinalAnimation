package com.example.zephyr.finalanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewAnimationActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_target)
    ImageView mIvTarget;
    @BindView(R.id.rg)
    RadioGroup mRg;
    Interpolator mInterpolator;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ViewAnimationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        mRg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.interpolatro_1:
                    mInterpolator = new AccelerateDecelerateInterpolator();
                    break;
                case R.id.interpolatro_2:
                    mInterpolator = new AccelerateInterpolator();
                    break;
                case R.id.interpolatro_3:
                    mInterpolator = new AnticipateInterpolator();
                    break;
                case R.id.interpolatro_4:
                    mInterpolator = new AnticipateOvershootInterpolator();
                    break;
                case R.id.interpolatro_5:
                    mInterpolator = new BounceInterpolator();
                    break;
                case R.id.interpolatro_6:
                    mInterpolator = new CycleInterpolator(1.0f);
                    break;
                case R.id.interpolatro_7:
                    mInterpolator = new DecelerateInterpolator();
                    break;
                case R.id.interpolatro_8:
                    mInterpolator = new LinearInterpolator();
                    break;
                case R.id.interpolatro_9:
                    mInterpolator = new OvershootInterpolator();
                    break;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onScale(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        animation.setInterpolator(mInterpolator);
        mIvTarget.startAnimation(animation);
    }

    public void onRotate(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        animation.setInterpolator(mInterpolator);
        mIvTarget.startAnimation(animation);
    }

    public void onTranslate(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        animation.setInterpolator(mInterpolator);
        mIvTarget.startAnimation(animation);
    }

    public void onAlpha(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        animation.setInterpolator(mInterpolator);
        mIvTarget.startAnimation(animation);
    }

    public void onSet(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
        animation.setInterpolator(mInterpolator);
        mIvTarget.startAnimation(animation);
    }
}
