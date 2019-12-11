package com.gdk.androidutil.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author gdk
 * @date 2017/6/20 15:07
 * Modified by Harry on 2017-11-15
 * 根据指令进行对应的页面跳转
 * * If an object of this type is attached to the text of a TextView
 * with a movement METHOD of LinkMovementMethod, the affected spans of
 * text can be selected.  If clicked, the {@link #onClick} METHOD will
 * be called.
 */

public final class MyURLSpan extends ClickableSpan {
    private Context context;
    /**
     * 链接集合.
     */
    private Map<String, String> map = Collections.emptyMap();
    /**
     * 跳转链接集合.
     */
    private Map<String, String> linkMap = Collections.emptyMap();

    /**
     * global ENCODE_KEY
     */
    private static final String globalUrl = "url";
    /**
     * global ENCODE_KEY
     */
    private static final String globalLogin = "login";
    /**
     * global false
     */
    private static final String globalFalse = "false";
    /**
     * global code
     */
    private static final String globalCode = "code";


    /**
     * @param url 可以点击的关键字，构造时传入的
     */
    public MyURLSpan(String url, Context context) {

        this.context = context;
        //初始化输入url
        initData(url);
    }

    /**
     * 初始化当前功能区的所有数据.
     */

    /**
     * 出来当前的跳转链接的集合.
     *
     * @param url
     */
    private void initData(String url) {
        //这是要处理的含有链接的map对象.
        linkMap = convertLink(url);
    }

    /**
     * 统一管理跳转url.
     *
     * @param link
     * @return {@link Map}
     */
    private Map<String, String> convertLink(String link) {

        map = new HashMap<>();
        /**
         * 不要返回null, 要返回空值.
         */
        if (TextUtils.isEmpty(link)) {
            return Collections.emptyMap();
        }

        //实现存入全局的识别码globalCode
        map.put(globalCode, link);

        //然后开始加工.
        //外部链接，包括http，https
        if (link.startsWith("http")) {
            //用来标记是否登录的字段.
            map.put(globalLogin, globalFalse);
            //当前需要跳转的链接. link
            map.put(globalUrl, link);
        }

        //客服电话****
        else if ("ebcode://95519".equals(link)) {
            map.put(globalLogin, globalFalse);
            map.put(globalUrl, "ebcode://95519");
        }
        return map;
    }


    @Override
    public void onClick(View view) {

        /**
         * 拿到当前的跳转的识别码
         */
        String code = linkMap.get(globalCode);
        /**
         * 1. 处理一遍当前的url链接
         * 2. 直接从连接中拿到自己需要的url地址. 和登录状态.
         */
        String mUrl = linkMap.get(globalUrl);
        /**
         * 标记的页面登录的状态
         */
        String loginString = linkMap.get(globalLogin);
        /**
         * 如果为空, 直接终端跳转.
         */
        if (TextUtils.isEmpty(mUrl) || TextUtils.isEmpty(code) || TextUtils.isEmpty(loginString)) {
            return;
        }

        /*
         *跳转普通访问http界面
         */
        if (!TextUtils.isEmpty(code) && mUrl.startsWith("http")) {
            switchToWWWPage(mUrl);
        }

        /*
         * 打电话
         */
        else if (!TextUtils.isEmpty(code) && "ebcode://95519".equals(code)) {
        }
    }

    /**
     * 跳转到登录界面.
     */
    private void switchToLoginActivity() {
//        LoginUtil.gotoLoginPage((Activity) context);
        //String eUrl = "login/login.html";
        //switchToTargetActivity(getWWWRedirectionPath(eUrl));
    }

    /**
     * 直接跳转页面方法
     * 普通的url跳转方法.
     *
     * @param url
     */
    private void switchToTargetActivity(String url) {

//        Intent intent = new Intent(context, HtmlActivity.class);
//        String targetUrl = getWWWRedirectionPath(url);
//        intent.putExtra(Constants.KEY_URL, targetUrl);
//        intent.putExtra("ball", "ball");
//        context.startActivity(intent);
    }

    private void switchToWWWPage(String page) {
//        Intent intent = new Intent(context, HtmlActivity.class);
//        intent.putExtra(Constants.KEY_URL, page);
//        intent.putExtra("ball", "ball");
//        context.startActivity(intent);
    }

    /**
     * 带有method参数的跳转
     *
     * @param cls
     */
    private void startActivityWithParam(Class<?> cls, String key, String value) {
        Intent intent = new Intent(context, cls);
        //Log.e("ebz", "mUrl获取第一次地址是==" + mUrl);
        intent.putExtra(key, value);
        intent.putExtra("ball", "ball");
        context.startActivity(intent);
    }

    /**
     * 显示自定义toast, 在屏幕中间.
     *
     * @param msg  message
     * @param flag whether show the icon of "wrong message"
     */
    private void showToast(String msg, boolean flag) {
        //设置时长
        ToastUtil.setDurationSecond(1200);
        //设置显示
        ToastUtil.showCenterToast((Activity) context, msg, flag);
    }

}