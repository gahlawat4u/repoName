package com.gms.xms.weblib.utils;

import com.gms.xms.model.BaseModel;
import org.apache.commons.lang.StringEscapeUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by thinhdd
 * Date 6/29/2017.
 */
public class StringConvertEscapeUtils {
    public static <T extends BaseModel> void convertObjectsEscape(List<T> data) throws IllegalAccessException {
        if (data.size() == 0) {
            return;
        }
        Class resourceClass = data.get(0).getClass();
        Field[] resourceFields = resourceClass.getDeclaredFields();

        for (T object : data) {
            convertObjectEscape(object, resourceFields);
        }
    }

    public static void convertObjectEscape(Object data, Field[] fields) throws IllegalAccessException {
        if (fields == null) {
            fields = data.getClass().getDeclaredFields();
        }
        for (Field field : fields) {
            Boolean isAccess = field.isAccessible();
            if (field.getType().isAssignableFrom(String.class)) {
                field.setAccessible(true);
                if(field.get(data) != null)
                {
                    field.set(data, StringEscapeUtils.escapeXml((String) field.get(data)));
                    field.setAccessible(isAccess);
                }
            }
        }
    }
}
