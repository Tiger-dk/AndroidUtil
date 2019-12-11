package com.gdk.androidutil.utils;

import android.app.Activity;
import android.view.View;
import android.view.animation.RotateAnimation;

import com.gdk.androidutil.R;

/**
 * gdk 2018.07.20
 * @author gdk
 */
public class AnimationUtil {

	/**顺时针转动动画*/
	public static void showRotateClock(View view) {
			final float centerX = view.getWidth() / 2.0f;
			final float centerY = view.getHeight() / 2.0f;
			RotateAnimation rotateAnimation = new RotateAnimation(0, 90, centerX,
			centerY);
			rotateAnimation.setDuration(300);
			rotateAnimation.setFillAfter(true);
			view.startAnimation(rotateAnimation);
	}
	/**逆时针转动动画*/
	public static void showRotateClockBack(View view) {
		final float centerX = view.getWidth() / 2.0f;
		final float centerY = view.getHeight() / 2.0f;
		RotateAnimation rotateAnimation = new RotateAnimation(90, 0, centerX,
		centerY);
		rotateAnimation.setDuration(300);
		rotateAnimation.setFillAfter(true);
		view.startAnimation(rotateAnimation);
	}
	private static boolean checkAnim(){
		return Integer.valueOf(android.os.Build.VERSION.SDK) > 5?true:false;
	}
	/**右进左出跳转  进入另一个界面***/
	public static void startAnimSlideRight(Activity activity){
		if(checkAnim()) {
			activity.overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out); // 此为自定义的动画效果，下面两个为系统的动画效果
		}
	}
	/**左进右出  进入另一个界面***/
	public static void startAnimSlideLeft(Activity activity){
		if(checkAnim()) {
			activity.overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out); // 此为自定义的动画效果，下面两个为系统的动画效果
		}
	}
	/**下进上出    进入另一个界面**/
	public static void startAnimSlideDown(Activity activity){
		if(checkAnim()) {
			activity.overridePendingTransition(R.anim.slide_down_in,R.anim.slide_top_out);
		}
	}
	/**上进下出    进入另一个界面**/
	public static void startAnimSlideTop(Activity activity){
		if(checkAnim()) {
			activity.overridePendingTransition(R.anim.slide_top_in,R.anim.slide_down_out);
		}
	}
	/**淡入淡出  进入另一个界面***/
	public static void startAnimFade(Activity activity){
		if(checkAnim()) {
			activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); // 此为自定义的动画效果，下面两个为系统的动画效果
		}
	}

	/**下一页面从底部出现覆盖当前页面**/
	public static void startAnimCoverIn(Activity activity){
//		if(checkAnim())
//			activity.overridePendingTransition(R.anim.slide_down_in,R.anim.no_anim);
	}
	/**当前页面从底部滑出，下一页面出现**/
	public static void startAnimCoverOut(Activity activity){
//		if(checkAnim())
//			activity.overridePendingTransition(R.anim.no_anim,R.anim.slide_down_out);
	}


}
