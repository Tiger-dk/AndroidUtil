
package com.gdk.androidutil.utils;

import com.gdk.androidutil.commen.Constants;

/**
 * 打log的工具类
 *
 * @author gdk
 */
public class Log {

    public static void v(String tag, String msg) {
        if (Constants.log) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable t) {
        if (Constants.log) {
            android.util.Log.v(tag, msg, t);
        }
    }

    public static void d(String tag, String msg) {
        if (Constants.log) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable t) {
        if (Constants.log) {
            android.util.Log.d(tag, msg, t);
        }
    }

    public static void i(String tag, String msg) {
        if (Constants.log) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable t) {
        if (Constants.log) {
            android.util.Log.i(tag, msg, t);
        }
    }

    public static void w(String tag, String msg) {
        if (Constants.log) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable t) {
        if (Constants.log) {
            android.util.Log.w(tag, msg, t);
        }
    }

    public static void e(String tag, String msg) {
        if (Constants.log) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable t) {
        if (Constants.log) {
            android.util.Log.e(tag, msg, t);
        }
    }
}
