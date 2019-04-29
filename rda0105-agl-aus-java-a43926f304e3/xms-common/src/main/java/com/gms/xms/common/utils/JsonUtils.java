package com.gms.xms.common.utils;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {
    /**
     * Return a string from object
     *
     * @param obj - object
     * @return - a json string
     * @throws Exception - on error
     */
    public static String toJson(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    /**
     * Return object from json
     *
     * @param json - json string
     * @param type - class of object
     * @return - object
     * @throws Exception
     */
    public static <T> T fromJson(String json, Class<T> type) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, type);
    }

}
