package com.clicbase.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gdk.androidutil.R;

/**
 * 信息提示框，自定义的Toast
 * @author zhangwt
 *
 */
public class InfoTip {



	public static void show(Activity activity, String msg){
		show(activity,msg,Toast.LENGTH_LONG);
	}

	public static void show(Activity activity, String msg,int time){
		Toast toast = new Toast(activity);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(time);//这句不管用
		View layout = LayoutInflater.from(activity).inflate(R.layout.pub_info_tip, (ViewGroup) activity.findViewById(R.id.toast_layout_root));
		TextView txtMsg = (TextView) layout.findViewById(R.id.txt_msg);
		txtMsg.setText(msg);
		toast.setView(layout);
		toast.show();
	}

	public static void show(Activity activity, String msg,boolean success){
		Toast toast = new Toast(activity);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		View layout = LayoutInflater.from(activity).inflate(R.layout.pub_info_tip2, (ViewGroup) activity.findViewById(R.id.toast_layout_root));
		TextView txtMsg = (TextView) layout.findViewById(R.id.txt_msg);
		txtMsg.setText(msg);
		ImageView ivIcon = (ImageView) layout.findViewById(R.id.iv_icon);
		if (success){
			ivIcon.setBackgroundResource(R.mipmap.success);
		}else{
			ivIcon.setBackgroundResource(R.mipmap.error);
		}
		toast.setView(layout);
		toast.show();
	}

}
