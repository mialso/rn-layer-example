package com.toplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hudl.oss.react.fragment.ReactFragment;

public class LayeredActivity extends AppCompatActivity {

    private static final String REACT_COMP_NAME = "react_layer";
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

}
