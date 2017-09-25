package com.future.association.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheyu on 2017/7/14.
 * 获取单例的Gson
 * Created by HeHu on 2017/4/7.
 */

public class GsonUtils {

    private static class LazyHolder {
        private static final Gson INSTANCE = new Gson();
    }

    public static Gson getInstance() {
        return LazyHolder.INSTANCE;
    }


    /**
     * 将jsonObject转成T类型对象
     *
     * @param jsonObject
     * @param clazz
     * @return
     */
    public static <T> T jsonToBean(JsonObject jsonObject, Class<T> clazz) {
        try {
            return getInstance().fromJson(jsonObject, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将jsonString转成T类型对象
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> T jsonToBean(String jsonString, Class<T> clazz) {
        try {
            return getInstance().fromJson(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将jsonArray转成T类型的数组
     *
     * @param jsonArray
     * @param clazz
     * @return
     */
    public static <T> List<T> jsonToList(JsonArray jsonArray, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        try {
            for (final JsonElement elem : jsonArray) {
                list.add(getInstance().fromJson(elem, clazz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将jsonString转成T类型的数组
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        try {
            JsonArray jsonArray = new JsonParser().parse(jsonString).getAsJsonArray();
            for (final JsonElement elem : jsonArray) {
                list.add(getInstance().fromJson(elem, clazz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
