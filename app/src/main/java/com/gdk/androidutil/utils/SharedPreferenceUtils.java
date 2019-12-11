package com.gdk.androidutil.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.gdk.androidutil.commen.Constants;
import com.google.gson.Gson;

/**
 * SharedPreference工具类
 * gdk 2018.07.20
 *
 * @author gdk
 */
public class SharedPreferenceUtils {
    /**
     * SharedPreferences
     */
    public SharedPreferences sp;

    private SharedPreferenceUtils(Context context, String filename, int model) {
        this.sp = context.getSharedPreferences(filename, model);
    }

    public SharedPreferenceUtils(Context context) {
        this(context, Constants.SP_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 将Object保存到sp中
     *
     * @param key
     * @param value
     */
    final public <T extends Object> void saveData(String key, T value) {
        Gson gson = new Gson();
        String valueString = gson.toJson(value);
        saveData(key, valueString);
    }

    /**
     * 将String保存到sp中
     *
     * @param key
     * @param value
     */
    final public void saveData(String key, String value) {
        sp.edit().putString(key, value).commit();
    }

    /**
     * 将String加密保存到sp中
     *
     * @param key
     * @param value
     * @param encrypt 是否加密
     */
    final public void saveData(String key, String value, boolean encrypt) {
        String e_value = value;
        if (encrypt && !TextUtils.isEmpty(value)) {
            e_value = CryptTools.encrypt(value);
        }
        if (!e_value.equals(value)) {
            saveData(key + "_e", true);
        }
        sp.edit().putString(key, e_value).commit();
    }

    /**
     * 将int保存到sp中
     *
     * @param key
     * @param value
     */
    final public void saveData(String key, int value) {
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 将boolean保存到sp中
     *
     * @param key
     * @param value
     */
    final public void saveData(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 将float保存到sp中
     *
     * @param key
     * @param value
     */
    final public void saveData(String key, float value) {
        sp.edit().putFloat(key, value).commit();
    }

    /**
     * 将long保存到sp中
     *
     * @param key
     * @param value
     */
    final public void saveData(String key, long value) {
        sp.edit().putLong(key, value).commit();
    }

    /**
     * 从sp中获取Object
     *
     * @param key
     * @return
     */
    final public <T extends Object> T getSaveObjectData(String key, Class<T> clazz) {
        Gson g = new Gson();
        String stringJson = getSaveStringData(key, "");
        return g.fromJson(stringJson, clazz);
    }

    /**
     * 从sp中获取String
     *
     * @param key
     * @param defaultValue
     * @return
     */
    final public String getSaveStringData(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * 从sp中获取String并解密
     *
     * @param key
     * @param defaultValue
     * @param decrypt      是否需要解密
     * @return
     */
    final public String getSaveStringData(String key, String defaultValue, boolean decrypt) {
        String value = sp.getString(key, defaultValue);
        boolean _e = getSaveBooleanData(key + "_e", false);//此值加过密
        String result = value;
        if (decrypt && !TextUtils.isEmpty(value) && _e) {
            result = CryptTools.decrypt(value);
        }
        return result;
    }

    /**
     * 从sp中获取int
     *
     * @param key
     * @param defaultValue
     * @return
     */
    final public int getSaveIntData(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    /**
     * 从sp中获取boolean
     *
     * @param key
     * @param defaultValue
     * @return
     */
    final public boolean getSaveBooleanData(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * 从sp中获取float
     *
     * @param key
     * @param defaultValue
     * @return
     */
    final public float getSaveFloatData(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    /**
     * 从sp中获取long
     *
     * @param key
     * @param defaultValue
     * @return
     */
    final public long getSaveLongData(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    final public void removeData(String key) {
        sp.edit().remove(key).commit();
    }
}
