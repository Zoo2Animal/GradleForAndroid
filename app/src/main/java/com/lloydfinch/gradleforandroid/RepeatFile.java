package com.lloydfinch.gradleforandroid;

import android.app.Activity;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;

/**
 * Name: RepeatFile
 * Author: lloydfinch
 * Function: 这里放置学到的代码，用来背诵记忆
 * Date: 2020-04-23 23:02
 * Modify: lloydfinch 2020-04-23 23:02
 */
public class RepeatFile {

    /**
     * app灰度化
     *
     * @param activity 需要灰度处理的activity
     */
    public void setGray(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0); //关键步骤，将饱和度设置为0，也就是灰度化
        ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorFilter);
        //设置硬件加速层，并且传入灰度处理过的paint
        decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint);

        //还原
        decorView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }
}
