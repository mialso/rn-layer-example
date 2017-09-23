package com.toplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.hudl.oss.react.fragment.ReactFragment;

public class LayeredActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    BaseFragment baseLayer;
    private static final String REACT_COMP_NAME = "topLayer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layered_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layer_container, baseLayer = new BaseFragment())
                .commit();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.layer_container, new ReactFragment.Builder(REACT_COMP_NAME).build())
                    .commit();

        }

    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!baseLayer.dispatchTouchEvent(ev)) {
            return super.dispatchTouchEvent(ev);
        }
        return true;
    }
}
