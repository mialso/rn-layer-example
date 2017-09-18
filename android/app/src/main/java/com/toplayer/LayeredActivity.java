package com.toplayer;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.hudl.oss.react.fragment.ReactFragment;

public class LayeredActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    private static final String REACT_COMP_NAME = "topLayer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layered_activity);

        if (savedInstanceState == null) {
            ReactFragment reactFragment = new ReactFragment.Builder(REACT_COMP_NAME).build();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.layer_container, reactFragment)
                    .commit();

        }

    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

}
