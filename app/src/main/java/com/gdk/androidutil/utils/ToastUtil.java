package com.gdk.androidutil.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Harry on 2017/7/12.
 * 自定义toast,
 * 1. 更改样式
 * 2. 改变现实实现.
 */

public class ToastUtil extends Toast {

    /**
     * Toast单例
     */
    private static ToastUtil toast;

    private static ToastUtil getInstance() {
        return toast;
    }

    private static ToastUtil initInstance(Context context) {
        synchronized (ToastUtil.class) {
            if (toast == null) {
                toast = new ToastUtil(context);
            }
        }
        return toast;
    }

    /**
     * 图标状态 不显示图标
     */
    private static final int TYPE_HIDE = -1;
    /**
     * 图标状态 显示√
     */
    private static final int TYPE_TRUE = 0;
    /**
     * 图标状态 显示×
     */
    private static final int TYPE_FALSE = 1;
    /**
     * 设置显示时长的字段duration
     */
    private static int durationSecond = 1500;

    private static android.animation.ObjectAnimator ObjectAnimator;


    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    private ToastUtil(Context context) {
        super(context);
    }

    public static void setDurationSecond(int durationSecond) {
        ToastUtil.durationSecond = durationSecond;
    }

    @Override
    public void setView(View view) {
        super.setView(view);

    }

    /**
     * 隐藏当前Toast
     */
    private static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    @Override
    public void cancel() {
        try {
            super.cancel();
        } catch (Exception e) {

        }
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {

        }
    }

    /**
     * 初始化Toast
     *
     * @param context 上下文
     * @param text    显示的文本
     */
    private static void initToast(Context context, CharSequence text) {
        try {
            cancelToast();
            toast = getInstance();

            // 获取LayoutInflater对象
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//            // 由layout文件创建一个View对象
//            View layout = inflater.inflate(R.layout.toast_layout, null);
//
//            // 吐司上的图片
//            toast_img = (ImageView) layout.findViewById(R.id.toast_img);
//
//            // 吐司上的文字
//            TextView toast_text = (TextView) layout.findViewById(R.id.toast_text);
//            toast_text.setText(text);
//            toast.setView(layout);
//            toast.setGravity(Gravity.CENTER, 0, 70);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示一个纯文本吐司
     *
     * @param context 上下文
     * @param text    显示的文本
     */
    public static void showText(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT, TYPE_HIDE);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context   上下文
     * @param text      显示的文本
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static void showText(Context context, CharSequence text, boolean isSucceed) {
        showToast(context, text, Toast.LENGTH_SHORT, isSucceed ? TYPE_TRUE : TYPE_FALSE);
    }

    /**
     * 显示一个纯文本吐司
     *
     * @param context 上下文
     * @param text    显示的文本
     * @param time    持续的时间
     */
    public static void showText(Context context, CharSequence text, int time) {
        showToast(context, text, time, TYPE_HIDE);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context   上下文
     * @param text      显示的文本
     * @param time      持续的时间
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static void showText(Context context, CharSequence text, int time, boolean isSucceed) {
        showToast(context, text, time, isSucceed ? TYPE_TRUE : TYPE_FALSE);
    }

    /**
     * 显示Toast
     *
     * @param context 上下文
     * @param text    显示的文本
     * @param time    显示时长
     * @param imgType 图标状态
     */
    private static void showToast(Context context, CharSequence text, int time, int imgType) {
        //初始化单例.
        initInstance(context);
        // 初始化一个新的Toast对象
        initToast(context, text);

        // 设置显示时长
        if (time == Toast.LENGTH_LONG) {
            toast.setDuration(Toast.LENGTH_LONG);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
        }

        // 判断图标是否该显示，显示√还是×
//        if (imgType == TYPE_HIDE) {
//            toast_img.setVisibility(View.GONE);
//        } else {
//            if (imgType == TYPE_TRUE) {
//                toast_img.setBackgroundResource(R.drawable.toast_y);
//            } else {
//                toast_img.setBackgroundResource(R.drawable.toast_n);
//            }
//            toast_img.setVisibility(View.VISIBLE);
//
//            // 动画
//            ObjectAnimator.ofFloat(toast_img, "rotationY", 0, 360).setDuration(1700).start();
//        }

        // 显示Toast
        toast.show();
    }

    /**
     * 使用本地的对话框显示方法
     * 默认显示对号图标
     *
     * @param activity
     * @param msg
     */
    public static void showCenterToast(Activity activity, String msg) {
        showCenterToast(activity, msg, false);
    }

    //*********************************************************************************************

    /**
     * 使用本地的对话框显示方法
     *
     * @param activity
     * @param msg
     * @param isShow   显示错误图标的标记
     */
    public static void showCenterToast(Activity activity, String msg, boolean isShow) {
    }

    private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(3);

    /**
     * 随意设置toast时长
     *
     * @param toast
     * @param cnt
     */
    private static void showMyToast(final Toast toast, final int cnt) {
        //显示延迟. 没有延迟, 直接显示.
        executorService.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0L, TimeUnit.MILLISECONDS);
        //然后停止执行. 定长时间之后, 直接取消当前的对话框.
        executorService.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, cnt, TimeUnit.MILLISECONDS);


        /**
         * Returns {@code true} if this executor has been shut down.
         *
         * @return {@code true} if this executor has been shut down
         */
        //executorService.shutdownNow();
    }


}

