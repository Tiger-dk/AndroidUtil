package com.gdk.androidutil.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 *
 * @author gdk
 * @date 2018/4/17 14:18
 */

public class GlideUtil {
    /**
     * 加载设置图片
     *
     * @param context：上下文
     * @param url：传入的url
     * @param view        目标view
     */
    public static void setImage(Context context, String url, ImageView view) {
        if (context != null && !TextUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .into(view);
        }
    }
}
