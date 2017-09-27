package com.toplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void goToReactView(View view) {
        Intent intent = new Intent(this, LayeredActivity.class);
        startActivity(intent);
    }

    public void goToBannerView(View view) {
        Intent intent = new Intent(this, BannerActivity.class);
        startActivity(intent);
    }

}
