package com.gdk.androidutil.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.gdk.androidutil.commen.MyApplication;

import java.io.File;

/**
 * @author gdk
 * @date 2016/11/9
 */
public class DeviceUtil {

    private final static int kSystemRootStateUnknow = -1;
    private final static int kSystemRootStateDisable = 0;
    private final static int kSystemRootStateEnable = 1;
    private static int systemRootState = kSystemRootStateUnknow;

    /**
     * @return 手机是否被root
     */
    public static boolean isRootSystem() {
        if (systemRootState == kSystemRootStateEnable) {
            return true;
        } else if (systemRootState == kSystemRootStateDisable) {

            return false;
        }
        File f = null;
        final String kSuSearchPaths[] = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        try {
            for (int i = 0; i < kSuSearchPaths.length; i++) {
                f = new File(kSuSearchPaths[i] + "su");
                if (f != null && f.exists()) {
                    systemRootState = kSystemRootStateEnable;
                    return true;
                }
            }
        } catch (Exception e) {
        }
        systemRootState = kSystemRootStateDisable;
        return false;
    }

    public static String getDeviceId(Context context) {
        try {
            TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            @SuppressLint("MissingPermission")
            String IMEI = mTm.getDeviceId();
            if (IMEI == null) {    //如果不是手机的话，就获取设备的唯一id号码，这个适用于安卓平板
                IMEI = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            return IMEI;    //设置imei号
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 得到当前版本号
     *
     * @return 当前版本号
     */
    public static String getVersionCode() {
        Context context = MyApplication.getInstance();
        // 得到PackageManager
        PackageManager pm = context.getPackageManager();
        // 初始化版本号为0
        int versionCode = 0;
        try {
            // 从PackageInfo中获取version_code
            versionCode = pm.getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("getVersion", e.getMessage());
        }
        return String.valueOf(versionCode);
    }

    /**
     * 得到当前版本号
     *
     * @return 当前版本号
     */
    public static String getVersionName() {
        Context context = MyApplication.getInstance();
        // 得到PackageManager
        PackageManager pm = context.getPackageManager();
        // 初始化版本号为0
        String versionCode = "2.0.0";
        try {
            // 从PackageInfo中获取version_code
            versionCode = pm.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("getVersion", e.getMessage());
        }
        return versionCode;
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return
     */
    public static int getBuildLevel() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本（4.4、5.0、5.1 ...）
     *
     * @return
     */
    public static String getBuildVersion() {
        return android.os.Build.VERSION.RELEASE;
    }
}
