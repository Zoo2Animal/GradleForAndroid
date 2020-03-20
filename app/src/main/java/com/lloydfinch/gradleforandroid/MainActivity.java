package com.lloydfinch.gradleforandroid;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.lloydfinch.gradleforandroid.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v -> {
            start();
        });

        binding.btnStop.setOnClickListener(v -> {
            stop();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop();
    }

    private Handler mHandler = new Handler();
    private int interval = 1000;

    private void start() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "running...... ");
                mHandler.postDelayed(this, interval);
            }
        }, interval);
    }

    private void stop() {
        mHandler.removeCallbacksAndMessages(null);
    }
}
