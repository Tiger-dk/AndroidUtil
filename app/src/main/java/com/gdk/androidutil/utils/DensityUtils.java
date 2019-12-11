package com.gdk.androidutil.utils;

import android.app.Activity;
import android.content.Context;

/**
 * 显示工具类
 *
 * @author JieRain Wang
 * @version 1.0
 * @since 1.0
 */
public class DensityUtils {

    /**
     * dip转化为px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转化为dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽度（单位dip）
     *
     * @param act
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getScreenWidthDip(Activity act) {
        float scale = act.getResources().getDisplayMetrics().density;
        int widthPx = act.getWindowManager().getDefaultDisplay().getWidth();
        int widthDip = (int) (widthPx / scale + 0.5f);
        return widthDip;
    }

    /**
     * 获取屏幕宽带（单位PX）
     *
     * @param act
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getScreenWidthPX(Activity act) {
        return act.getWindowManager().getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度（单位PX）
     *
     * @param act
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getScreenHeightPX(Activity act) {
        return act.getWindowManager().getDefaultDisplay().getHeight();
    }

}