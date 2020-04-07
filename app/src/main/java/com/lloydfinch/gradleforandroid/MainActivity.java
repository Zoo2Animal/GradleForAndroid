package com.lloydfinch.gradleforandroid;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebViewClient;

import com.lloydfinch.gradleforandroid.databinding.ActivityMainBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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
            // start();

            // testBrodcastReceiver();

            // testFragmentOnActivityResult();

            testGray();
        });

        binding.btnStop.setOnClickListener(v -> {
            // loadBaidu();
            // stop();

            resetGray();
        });

        binding.btnStop.post(new Runnable() {
            @Override
            public void run() {

            }
        });

        // testViewPost();
        testViewTreeObserver();


        loadBaidu();
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

    private void testBrodcastReceiver() {
        Intent intent = new Intent(this, MyReceiver.class);
        ComponentName componentName = new ComponentName(this, MyReceiver.class);
        // intent.setComponent();
        intent.setAction("com.lloydfinch.fuck");
        sendBroadcast(intent);
    }

    private void testFragmentOnActivityResult() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(android.R.id.content, new FirstFragment(), "first").commit();

        // startActivityForResult(new Intent(this, SecondActivity.class), 100);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
    }


    /**
     * 将app灰度处理
     * 可以在baseActivity使用
     * 或者水使用application.registerActivityLifecycleCallbacks();在里面遍历Activity来设置
     */
    private void testGray() {
        View decorView = getWindow().getDecorView();
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
    }

    private void resetGray() {
        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    /**
     * 加载百度
     */
    private void loadBaidu() {
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadUrl("https://www.baidu.com");
    }
}
