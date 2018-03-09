package com.example.zephyr.finalanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MDAnimatorActivity extends AppCompatActivity {

    @BindView(R.id.iv_show)
    ImageView mAnimatorView;
    @BindView(R.id.btn_hide)
    TextView mBtnHide;
    @BindView(R.id.btn_show)
    TextView mBtnShow;
    @BindView(R.id.btn_remark)
    Button mBtnRemark;
    private int mViewLeft;
    private int mViewRight;
    private int mViewTop;
    private int mViewBottom;
    private int mRadius;
    private int mCenterX;
    private int mCenterY;

    public static void startMDAnimatorActivity(Context context) {
        Intent intent = new Intent(context, MDAnimatorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdanimator);
        ButterKnife.bind(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mViewLeft = mAnimatorView.getLeft();
        mViewRight = mAnimatorView.getRight();
        mViewTop = mAnimatorView.getTop();
        mViewBottom = mAnimatorView.getBottom();

        mCenterX = (mViewLeft + mViewRight) / 2;
        mCenterY = (mViewTop + mViewBottom) / 2;
        mRadius = Math.max(mAnimatorView.getWidth(), mAnimatorView.getHeight());
    }

    @OnClick(R.id.btn_show)
    public void onBtnShowClicked() {
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(mAnimatorView, mCenterX, mCenterY, 0, mRadius);
        mAnimatorView.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    @OnClick(R.id.btn_hide)
    public void onBtnHideClicked() {
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(mAnimatorView, mCenterX, mCenterY, mRadius, 0);
        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimatorView.setVisibility(View.INVISIBLE);
            }
        });
        circularReveal.start();
    }

    @OnClick(R.id.btn_remark)
    public void onResetClicked() {
        Toast.makeText(this, "隐藏和显示只针对INVISIBLE有用，GONE没效果", Toast.LENGTH_LONG).show();
    }
}
