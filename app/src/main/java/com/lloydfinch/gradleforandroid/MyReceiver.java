package com.lloydfinch.gradleforandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.

        Log.d(TAG, "onReceive: ");

        Intent intent1 = new Intent(context, SecondActivity.class);
        context.startActivity(intent1);
    }
}
