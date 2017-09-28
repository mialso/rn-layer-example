package com.toplayer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity  implements DefaultHardwareBackBtnHandler {
    private static final String REACT_BANNER_ONE_NAME = "bannerOne";
    private static final String REACT_BANNER_BUTTON_NAME = "bannerButton";

    private final List<ReactRootView> rnViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner_activity);

        ReactRootView v = (ReactRootView) findViewById(R.id.bunner_button);
        rnViews.add(v);
        initRNView(v, REACT_BANNER_BUTTON_NAME);
        v = (ReactRootView)findViewById(R.id.bunner_one);
        rnViews.add(v);
        initRNView(v, REACT_BANNER_ONE_NAME);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (View v : rnViews) {
            v.dispatchTouchEvent(ev);
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((ReactApplication)getApplication()).getReactNativeHost().hasInstance()) {
            ((ReactApplication)getApplication()).getReactNativeHost().getReactInstanceManager().onHostResume(this, this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (((ReactApplication)getApplication()).getReactNativeHost().hasInstance()) {
            ((ReactApplication)getApplication()).getReactNativeHost().getReactInstanceManager().onHostPause(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (ReactRootView v : rnViews) {

            if (v != null) {
                v.unmountReactApplication();
            }
        }
        if (((ReactApplication)getApplication()).getReactNativeHost().hasInstance()) {
            ((ReactApplication)getApplication()).getReactNativeHost().getReactInstanceManager().onHostDestroy(this);
        }
    }

    private void initRNView(@NonNull ReactRootView view, @NonNull String componentName) {
        view.startReactApplication(
                ((ReactApplication)getApplication()).getReactNativeHost().getReactInstanceManager(),
                componentName, null);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
}
