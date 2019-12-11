package com.gdk.androidutil.commen;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdk
 * @date 2016/11/15
 */
public class MyApplication extends Application {

    private static MyApplication context;
    private List<Activity> activityList;

    public static MyApplication getInstance() {
        return context;
    }

    private static Activity appEnterActivity;
    private static List<String> activities_str;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //初始化，加入所有安全的activity_str
        activities_str = new ArrayList<String>();
    }

    public static List<String> getActivitiesStr() {
        if (activities_str == null) {
            activities_str = new ArrayList<String>();
        }
        return activities_str;
    }
    public void exitApp() {
        if (activityList != null) {
            for (Activity act : activityList) {
                if (act != null) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


}
