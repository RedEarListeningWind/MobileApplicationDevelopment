package com.crtf.citycardcustomermanager.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.crtf.citycardcustomermanager.MainActivity;
import com.crtf.citycardcustomermanager.R;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {

    private static final long INIT_TIMEOUT = 800;
    private boolean mPaused;

    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
        Runnable enterNextActivity = SplashActivity.this::enterNextActivity;
        mHandler.postDelayed(enterNextActivity, INIT_TIMEOUT);
    }

    private void init() {

        this.mHandler = new Handler();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.mPaused) {
            this.mPaused = false;
            enterNextActivity();
        }
    }

    void enterNextActivity() {

        if (this.mPaused) {
            return;
        }

        MainActivity.intentStart(this);
        finish();
    }
}