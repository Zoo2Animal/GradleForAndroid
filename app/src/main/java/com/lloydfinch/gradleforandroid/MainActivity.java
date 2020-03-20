package com.lloydfinch.gradleforandroid;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.lloydfinch.gradleforandroid.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lloydfinch
 */
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

        binding.btnStop.post(new Runnable() {
            @Override
            public void run() {

            }
        });

        // testViewPost();
        testViewTreeObserver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume before: ");
        printRootSize();
        Log.e(TAG, "onResume after: ");
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

    /**
     * 证明View.post()可以获取View的measure size
     */
    private void testViewPost() {
        View root = binding.getRoot();
        root.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: by root post! ");
                printRootSize();
            }
        });


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(root);
            }
        }, 3000);
    }

    /**
     * 这个用来证明OnGlobalLayoutListener里面可以获取到View的measure size
     */
    private void testViewTreeObserver() {
        View root = binding.getRoot();
        ViewTreeObserver observer = root.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.e(TAG, "onGlobalLayout before:");
                printRootSize();
                Log.e(TAG, "onGlobalLayout after:");
            }
        });
    }

    /**
     * 证明onWindowFocueChanged可以获取View的measure size
     *
     * @param
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Log.e(TAG, "onWindowFocusChanged before: ");
            printRootSize();
            Log.e(TAG, "onWindowFocusChanged before: ");
        }
    }

    private void printRootSize() {
        View root = binding.getRoot();
        int width = root.getMeasuredWidth();
        int height = root.getMeasuredHeight();
        Log.e(TAG, "printRootSize: [width = " + width + ", height = " + height + "]");
    }
}
