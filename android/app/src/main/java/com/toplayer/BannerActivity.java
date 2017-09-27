package com.toplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.hudl.oss.react.fragment.ReactFragment;

public class BannerActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    private static final String REACT_COMP_NAME = "bannerOne";
    private BackgroundFragment baseLayer;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layered_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.layer_container, baseLayer = new BackgroundFragment(), BackgroundFragment.TAG)
                    .commit();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.layer_container, new ReactFragment.Builder(REACT_COMP_NAME).build())
                    .commit();


        } else {
            baseLayer = (BackgroundFragment) getSupportFragmentManager().findFragmentByTag(BackgroundFragment.TAG);
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
