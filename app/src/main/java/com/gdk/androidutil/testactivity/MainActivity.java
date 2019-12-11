package com.gdk.androidutil.testactivity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gdk.androidutil.R;

/**
 * 测试类
 *
 * @author gdk
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Button btn_device_id;
    private TextView tv_device_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermesion();
        initView();
    }


    /**
     * 获取权限
     */
    private void getPermesion() {
        //两个日历权限和一个数据读写权限
        String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_NUMBERS};
//        PermissionsUtils.showSystemSetting = false;//是否支持显示系统设置权限设置窗口跳转
        //这里的this不是上下文，是Activity对象！
    }

    //初始化控件
    private void initView() {
        btn_device_id = (Button) findViewById(R.id.btn_device_id);
        btn_device_id.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_device_id:
//                String device = DeviceUtil.getDeviceId(this);
                break;
            default:
                break;
        }
    }


}
