package com.gdk.androidutil.utils;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.regex.Pattern;

//验证工具
public class ValidateUtil {

    public ValidateUtil() {

    }

    /**
     * 校验TextView的文本是否为空字符串
     *
     * @param v
     * @return
     */
    public static boolean isNull(TextView v) {
        String str = v.getText().toString();
        if (null == str || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 校验TextView的文本是否不为空字符串
     *
     * @param v
     * @return
     */
    public static boolean isNotNull(TextView v) {
        return !isNull(v);
    }

    /**
     * 校验EditText的文本是否为空字符串
     *
     * @param v
     * @return
     */
    public static boolean isNull(EditText v) {
        String str = v.getText().toString();
        if (null == str || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 校验EditText的文本是否不为空字符串
     *
     * @param v
     * @return
     */
    public static boolean isNotNull(EditText v) {
        return !isNull(v);
    }

    /**
     * 校验EditText的文本是否为手机号
     *
     * @param v
     * @return
     */
    public static boolean isMobile(EditText v) {
        String str = v.getText().toString();
        return isMobile(str);
    }

    /**
     * 校验TextView的文本是否为手机号
     *
     * @param v
     * @return
     */
    public static boolean isMobile(TextView v) {
        String str = v.getText().toString();
        return isMobile(str);
    }

    /**
     * 校验字符串是否为手机号
     *
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        if (null != str && !"".equals(str.trim())) {
            //return str.matches("^[1]+\\d{10}$");
            return str.matches("^1\\d{10}$");
        }
        return false;
    }


    /**
     * 校验EditText的文本是否为邮箱
     *
     * @param v
     * @return
     */
    public static boolean isEmail(EditText v) {
        String str = v.getText().toString();
        return isEmail(str);
    }

    /**
     * 校验TextView的文本是否为邮箱
     *
     * @param v
     * @return
     */
    public static boolean isEmail(TextView v) {
        String str = v.getText().toString();
        return isEmail(str);
    }

    /**
     * 校验字符串是否为邮箱
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        boolean ret = false;
        if (null != str && !"".equals(str.trim())) {
//			ret = str.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");//旧
            ret = Pattern.compile("^[0-9a-z_][-_\\.0-9a-z-]{0,63}@([0-9a-z][0-9a-z-]*\\.)+[a-z]{2,4}$").matcher(str).matches();// 新


        }
        return ret;
    }

    /**
     * 校验字符串是否为密码指定规则，当前规则为：6-16位数字、字母或常用符号，字母区分大小写，常用符号包括!@#$%^&*_-+
     *
     * @param str
     * @return
     */
    public static boolean isPassWord(String str) {
        boolean ret = false;
        if (null != str && !"".equals(str.trim())) {
            ret = str.matches("[[0-9]|[a-z,A-Z]|[\\!\\@\\#\\$\\%\\^\\&\\*\\_\\-\\+]]{6,16}");
        }
        return ret;
    }

    /**
     * 判断出生日期 和 性别 是否 和身份证 一致
     ***/
    public static boolean checkUserinfo(Activity activity, String idTypeNo_t, String idType_t, String birthday_t, RadioButton radioButtonMan) {
        if (idType_t.toString().trim().equals("身份证")
                || idType_t.toString().trim().equals("身份证")) {
            if (idTypeNo_t.length() == 18) {
                String data = idTypeNo_t.substring(6, 14);
                String year = data.substring(0, 4);
                String month = data.substring(4, 6);
                String day = data.substring(6, 8);
                String birthday = year + "-" + month + "-" + day;
                if (!birthday.equals(birthday_t)) {
                    com.clicbase.dialog.InfoTip.show(activity, "您所填写的出生日期与身份证号码不一致", false);
                    return false;
                }
                String gender = idTypeNo_t.substring(16, 17);
                if (Integer.parseInt(gender) % 2 > 0) {
                    if (!radioButtonMan.isChecked()) {
                        com.clicbase.dialog.InfoTip.show(activity, "出生日期或性别与身份证不符", false);
                        return false;
                    }
                } else {
                    if (radioButtonMan.isChecked()) {
                        com.clicbase.dialog.InfoTip.show(activity, "出生日期或性别与身份证不符", false);
                        return false;
                    }
                }
            } else if (idTypeNo_t.length() == 15) {
                String data = idTypeNo_t.substring(6, 12);
                String year = data.substring(0, 2);
                String month = data.substring(2, 4);
                String day = data.substring(4, 6);
                String birthday = "19" + year + "-" + month + "-" + day;
                if (!birthday.equals(birthday_t)) {
                    com.clicbase.dialog.InfoTip.show(activity, "您所填写的出生日期与身份证号码不一致", false);
                    return false;
                }
                String gender = idTypeNo_t.substring(14, 15);
                if (Integer.parseInt(gender) % 2 > 0) {
                    if (!radioButtonMan.isChecked()) {
                        com.clicbase.dialog.InfoTip.show(activity, "出生日期或性别与身份证不符", false);
                        return false;
                    }
                } else {
                    if (radioButtonMan.isChecked()) {
                        com.clicbase.dialog.InfoTip.show(activity, "出生日期或性别与身份证不符", false);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 判断字符串是否是数字
     ***/
    public static boolean isNumeric1(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
