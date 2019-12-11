package com.gdk.androidutil.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gdk
 * @date 2019/2/27 0027
 * des:gson
 */

public class GsonUtils {
    /**
     * 转成json
     */
    public static String toJson(Object obj) {
        String res = null;
        try {
            res = new Gson().toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static <T> T parseJson(String json, Class<T> clz) {
        T t = null;
        try {
            t = new Gson().fromJson(json, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将Map转化为Json
     *
     * @param map
     * @return String
     */
    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }
    /**
     * 将Map转化为Json
     *
     * @return String
     */
    public static <T> Map<String,T> parseJsonToMap(String json) {
        Type amountCurrencyType =
                new TypeToken<Map<String, T>>(){}.getType();
       return new Gson().fromJson(json,amountCurrencyType);
    }

    public static <T> List<T> parseJsonToList(String json, Class clz){
        Type type = new ParameterizedTypeImpl(clz);
        List<T> list =  new Gson().fromJson(json, type);
        return list;
    }
    private  static class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
