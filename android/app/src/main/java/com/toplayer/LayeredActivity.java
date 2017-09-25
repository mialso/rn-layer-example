package com.toplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.hudl.oss.react.fragment.ReactFragment;

public class LayeredActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    private static final String REACT_COMP_NAME = "topLayer";
    private BaseFragment baseLayer;
    private int[] location = new int[]{0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layered_activity);

        findViewById(R.id.layer_container).addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> v.getLocationOnScreen(location));

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.layer_container, baseLayer = new BaseFragment(), BaseFragment.TAG)
                    .commit();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.layer_container, new ReactFragment.Builder(REACT_COMP_NAME).build())
                    .commit();


        } else {
            baseLayer = (BaseFragment) getSupportFragmentManager().findFragmentByTag(BaseFragment.TAG);
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (baseLayer != null) {
            baseLayer.dispatchTouchEvent(MotionEvent.obtain(ev.getDownTime(), ev.getEventTime(), ev.getAction(), ev.getX() - location[0], ev.getY() - location[1], ev.getMetaState()));
        }
        return super.dispatchTouchEvent(ev);
    }
}
