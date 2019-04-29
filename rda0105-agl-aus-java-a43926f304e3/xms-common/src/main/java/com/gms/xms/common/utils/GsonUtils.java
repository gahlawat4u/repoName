package com.gms.xms.common.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Posted from GsonUtils.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:02:40 PM
 */
public class GsonUtils {
    /**
     * Return a json string from object
     *
     * @param obj - object
     * @return - a json string
     */
    public static String toGson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * Return a object from json
     *
     * @param json - json string
     * @param type - type of class
     * @return - object
     */
    public static <T> T fromGson(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * Return a Object from json
     *
     * @param json - json string
     * @param type - type of class
     * @return - object
     */
    public static <T> T fromGson(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * Return a json string from object
     *
     * @param t    - object
     * @param type - type of object
     * @return - json string
     */
    public static <T> String toGson(T t, Type type) {
        Gson gson = new Gson();
        return gson.toJson(t, type);
    }

}
