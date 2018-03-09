package com.example.zephyr.finalanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDrawableActivity extends BaseActivity {

    @BindView(R.id.iv_target)
    ImageView mIvTarget;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, CustomDrawableActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drawable);
        ButterKnife.bind(this);
        mIvTarget.setBackground(new CustomDrawable());
    }
}
