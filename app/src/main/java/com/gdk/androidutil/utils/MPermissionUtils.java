package com.gdk.androidutil.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.BuildConfig;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;


import com.gdk.androidutil.R;
import com.gdk.androidutil.commen.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求权限的封装
 *
 * @author 郭登科 2019-06-25
 */
public class MPermissionUtils {

    private static int mRequestCode = -1;
    private static Object mObject;
    private static String[] mPermissionNames;
    private static Map<String, String> mMustPermissions;//必须权限
    private static Map<String, String> deniedPermissions;//当前没有的权限

    @Deprecated
    public static void requestPermissionsResult(Activity activity, int requestCode
            , String[] permission, String[] permissionNames, OnPermissionListener callback) {
        Map<String, String> mustPermissions = new HashMap<>(16);
        for (int i = 0; i < permission.length; i++) {
            mustPermissions.put(permission[i], permissionNames[i]);
        }
        requestPermissions(activity, requestCode, mustPermissions, null, callback);
    }

    @Deprecated
    public static void requestPermissionsResult(Fragment fragment, int requestCode
            , String[] permission, String[] permissionNames, OnPermissionListener callback) {
        Map<String, String> mustPermissions = new HashMap<>(16);
        for (int i = 0; i < permission.length; i++) {
            mustPermissions.put(permission[i], permissionNames[i]);
        }
        requestPermissions(fragment, requestCode, mustPermissions, null, callback);
    }

    @Deprecated
    public static void requestPermissionsResult(android.support.v4.app.Fragment fragment, int requestCode
            , String[] permission, String[] permissionNames, OnPermissionListener callback) {
        Map<String, String> mustPermissions = new HashMap<>(16);
        for (int i = 0; i < permission.length; i++) {
            mustPermissions.put(permission[i], permissionNames[i]);
        }
        requestPermissions(fragment, requestCode, mustPermissions, null, callback);
    }

    /**
     * 请求权限
     *
     * @param activity        上下文
     * @param requestCode     请求码
     * @param mustPermissions 必须权限
     * @param callback        请求回调
     */
    public static void requestPermissionsResult(Activity activity, int requestCode
            , Map<String, String> mustPermissions, OnPermissionListener callback) {
        requestPermissions(activity, requestCode, mustPermissions, null, callback);
    }

    /**
     * 请求权限
     *
     * @param fragment        上下文
     * @param requestCode     请求码
     * @param mustPermissions 必须权限
     * @param callback        请求回调
     */
    public static void requestPermissionsResult(Fragment fragment, int requestCode
            , Map<String, String> mustPermissions, OnPermissionListener callback) {
        requestPermissions(fragment, requestCode, mustPermissions, null, callback);
    }

    /**
     * 请求权限
     *
     * @param fragment        上下文
     * @param requestCode     请求码
     * @param mustPermissions 必须权限
     * @param callback        请求回调
     */
    public static void requestPermissionsResult(android.support.v4.app.Fragment fragment, int requestCode
            , Map<String, String> mustPermissions, OnPermissionListener callback) {
        requestPermissions(fragment, requestCode, mustPermissions, null, callback);
    }

    /**
     * 请求权限
     *
     * @param activity           上下文
     * @param requestCode        请求码
     * @param mustPermissions    必须权限
     * @param nonMustPermissions 非必须权限
     * @param callback           请求回调
     */
    public static void requestPermissionsResult(Activity activity, int requestCode
            , Map<String, String> mustPermissions, Map<String, String> nonMustPermissions, OnPermissionListener callback) {
        requestPermissions(activity, requestCode, mustPermissions, nonMustPermissions, callback);
    }

    /**
     * 请求权限
     *
     * @param fragment           上下文
     * @param requestCode        请求码
     * @param mustPermissions    必须权限
     * @param nonMustPermissions 非必须权限
     * @param callback           请求回调
     */
    public static void requestPermissionsResult(Fragment fragment, int requestCode
            , Map<String, String> mustPermissions, Map<String, String> nonMustPermissions, OnPermissionListener callback) {
        requestPermissions(fragment, requestCode, mustPermissions, nonMustPermissions, callback);
    }

    /**
     * 请求权限
     *
     * @param fragment           上下文
     * @param requestCode        请求码
     * @param mustPermissions    必须权限
     * @param nonMustPermissions 非必须权限
     * @param callback           请求回调
     */
    public static void requestPermissionsResult(android.support.v4.app.Fragment fragment, int requestCode
            , Map<String, String> mustPermissions, Map<String, String> nonMustPermissions, OnPermissionListener callback) {
        requestPermissions(fragment, requestCode, mustPermissions, nonMustPermissions, callback);
    }

    /**
     * 请求权限
     *
     * @param object             上下文
     * @param requestCode        请求码
     * @param mustPermissions    必须权限
     * @param nonMustPermissions 非必须权限
     * @param callback
     */
    @SuppressLint("NewApi")
    private static void requestPermissions(Object object, int requestCode
            , Map<String, String> mustPermissions, Map<String, String> nonMustPermissions, OnPermissionListener callback) {
        mMustPermissions = mustPermissions;
        Map<String, String> permissions = new HashMap<>(16);
        if (mustPermissions != null) {
            permissions.putAll(mustPermissions);
        }
        if (nonMustPermissions != null) {
            permissions.putAll(nonMustPermissions);
        }
        checkCallingObjectSuitability(object);
        mOnPermissionListener = callback;
        mObject = object;
        if (checkPermissions(getContext(object), permissions)) {
            if (mOnPermissionListener != null) {
                mOnPermissionListener.onPermissionGranted();
            }
        } else {
            deniedPermissions = getDeniedPermissions(getContext(object), permissions);
            if (deniedPermissions.size() > 0) {
                List<String> dps = new ArrayList<String>();
                for (Map.Entry<String, String> entry : permissions.entrySet()) {
                    String permission = entry.getKey();
                    dps.add(permission);
                }
                mRequestCode = requestCode;
                if (object instanceof Activity) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ((Activity) object).requestPermissions(dps
                                .toArray(new String[dps.size()]), requestCode);
                    }
                } else if (object instanceof Fragment) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ((Fragment) object).requestPermissions(dps
                                .toArray(new String[dps.size()]), requestCode);
                    }
                } else if (object instanceof android.support.v4.app.Fragment) {
                    ((android.support.v4.app.Fragment) object).requestPermissions(dps
                            .toArray(new String[dps.size()]), requestCode);
                } else {
                    mRequestCode = -1;
                }
            }
        }
    }

    /**
     * 获取上下文
     */
    private static Context getContext(Object object) {
        Context context;
        if (object instanceof Fragment) {
            context = ((Fragment) object).getActivity();
        } else if (object instanceof android.support.v4.app.Fragment) {
            context = ((android.support.v4.app.Fragment) object).getActivity();
        } else {
            context = (Activity) object;
        }
        return context;
    }

    /**
     * 请求权限结果，对应activity中onRequestPermissionsResult()方法。
     */
    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (permissions == null || permissions.length < 1 || grantResults == null || grantResults.length < 1) {
            return;
        }
        if (mRequestCode != -1 && requestCode == mRequestCode) {
            if (verifyPermissions(grantResults) || !hasMustPermissionsDenied(getContext(mObject), permissions)) {
                if (mOnPermissionListener != null) {
                    mOnPermissionListener.onPermissionGranted();
                }
            } else {
                if (mOnPermissionListener != null) {
                    mOnPermissionListener.onPermissionDenied(getDeniedMustPermissionNames(getContext(mObject), permissions));
                }
            }
        }
    }

//    /**
//     * 显示提示对话框
//     *
//     * @param context     上下文
//     * @param permissions 提示的权限，如有多个用顿号隔开，中文权限名
//     */
//    public static void showTipsDialog(Context context, String permissions) {
//        showTipsDialog(context, permissions, false);
//    }

    /**
     * 显示提示对话框
     *
     * @param activity    上下文
     * @param permissions 提示的权限，如有多个用顿号隔开，中文权限名
     */
    public static void showTipsDialog(final Activity activity, String permissions, final boolean finishFlag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(activity.getString(R.string.app_name) + "需要" + permissions + "权限，是否去设置");
        builder.setTitle("提示");
        builder.setCancelable(false);
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings(activity);
                activity.finish();
                MyApplication.getInstance().exitApp();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
                MyApplication.getInstance().exitApp();
            }
        });
        builder.show();
    }

    /**
     * 启动当前应用设置页面
     */
    private static void startAppSettings(Context context) {
        gotoMiuiPermission(context);
    }

    /**
     * 跳转到miui的权限管理页面
     */
    private static void gotoMiuiPermission(Context context) {
        Intent i = new Intent("miui.intent.action.APP_PERM_EDITOR");
        ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        i.setComponent(componentName);
        i.putExtra("extra_pkgname", context.getPackageName());
        try {
            context.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
            gotoMeizuPermission(context);
        }
    }

    /**
     * 跳转到魅族的权限管理系统
     */
    private static void gotoMeizuPermission(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            gotoHuaweiPermission(context);
        }
    }

    /**
     * 华为的权限管理页面
     */
    private static void gotoHuaweiPermission(Context context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            goAppSetting(context);
        }

    }

    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    private static void goAppSetting(Context context) {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            context.startActivity(localIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证权限是否都已经授权
     */
    private static boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否有必须权限为授权
     *
     * @param permissions
     * @return
     */
    private static boolean hasMustPermissionsDenied(Context context, String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (mMustPermissions != null && mMustPermissions.containsKey(permissions[i]) && ContextCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证权限是否都已经授权
     */
    public static boolean verifyPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取未授权的权限
     *
     * @param context
     * @param permissions
     * @return
     */
    private static Map<String, String> getDeniedPermissions(Context context, Map<String, String> permissions) {
        Map<String, String> deniedPermissions = new HashMap<>(16);
        for (Map.Entry<String, String> entry : permissions.entrySet()) {
            String permission = entry.getKey();
            String permissionName = entry.getValue();
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.put(permission, permissionName);
            }
        }
        return deniedPermissions;
    }

    /**
     * 获取未授权的权限名
     *
     * @param context
     * @param deniedPermissions
     * @return
     */
    private static String getDeniedMustPermissionNames(Context context, String[] deniedPermissions) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < deniedPermissions.length; i++) {
            String permission = deniedPermissions[i];
            if (mMustPermissions != null && mMustPermissions.containsKey(permission) && ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                sb.append(mMustPermissions.get(permission));
                sb.append(",");
            }
        }
        String result = sb.toString();
        return result.substring(0, result.lastIndexOf(","));
    }

    /**
     * 检查所传递对象的正确性
     *
     * @param object 必须为 activity or fragment
     */
    private static void checkCallingObjectSuitability(Object object) {
        if (object == null) {
            throw new NullPointerException("Activity or Fragment should not be null");
        }

        boolean isActivity = object instanceof Activity;
        boolean isSupportFragment = object instanceof android.support.v4.app.Fragment;
        boolean isAppFragment = object instanceof Fragment;

        if (!(isActivity || isSupportFragment || isAppFragment)) {
            throw new IllegalArgumentException(
                    "Caller must be an Activity or a Fragment");
        }
    }

    /**
     * 检测是否有未授权的权限
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean checkPermissions(Context context, Map<String, String> permissions) {
        if (isOverMarshmallow()) {
            for (Map.Entry<String, String> entry : permissions.entrySet()) {
                String permission = entry.getKey();
                String permissionName = entry.getValue();
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断当前手机API版本是否 >= 6.0
     */
    private static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public interface OnPermissionListener {
        void onPermissionGranted();

        void onPermissionDenied(String deniedPermissions);
    }

    private static OnPermissionListener mOnPermissionListener;

    public interface OnCancleListener {
        void onClick();
    }

}
