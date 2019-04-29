package com.gms.xms.model.utils;

import com.gms.xms.common.utils.JsonUtils;
import com.gms.xms.model.BaseModel;
import com.gms.xms.txndb.vo.BaseVo;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Posted from AppUtils.java
 * <p>
 * Author Toantq Date Mar 15, 2015 Time: 08:17:05 AM
 */
public class ModelUtils {
    protected static Log log = LogFactory.getLog(ModelUtils.class);

    /**
     * Return a Model object from value object
     *
     * @param m    - value object
     * @param type - type of model object
     * @return - model object
     * @throws Exception
     */
    public static <M extends BaseVo, T extends BaseModel> M createVoFromModel(T t, Class<M> type) throws Exception {
        return JsonUtils.fromJson(JsonUtils.toJson(t), type);
    }

    /**
     * Return a Model object from value object
     *
     * @param m    - value object
     * @param type - type of model object
     * @return - model object
     * @throws Exception
     */
    public static <M extends BaseVo, T extends BaseModel> T createModelFromVo(M m, Class<T> type) throws Exception {
        if (m == null) {
            return null;
        }
        return JsonUtils.fromJson(JsonUtils.toJson(m), type);
    }

    /**
     * Return list of model object from list of value object
     *
     * @param ms   - list of value object
     * @param type - type of model object
     * @return - list of model object
     * @throws Exception
     */
    public static <M extends BaseVo, T extends BaseModel> List<T> createListModelFromVo(List<M> ms, Class<T> type) throws Exception {
        if (ms == null)
            return null;
        List<T> list = new ArrayList<T>();
        for (M m : ms) {
            list.add(JsonUtils.fromJson(JsonUtils.toJson(m), type));
        }
        return list;
    }

    public static <M extends BaseVo, T extends BaseModel> List<T> createListModelFromVoEscapeHtmlAndIgnoreNonPrintableChars(List<M> ms, Class<T> type) throws Exception {
        if (ms == null)
            return null;
        List<T> list = new ArrayList<T>();

        for (M m : ms) {
            try {
                for (Field field : m.getClass().getDeclaredFields()) {
                    if (field.getType().isAssignableFrom(String.class)) {
                        field.setAccessible(true);
                        String value = (String) field.get(m);
                        if (value != null) {
                            field.set(m, StringEscapeUtils.escapeHtml(value.replaceAll("\\p{C}", "")));
                        }
                    }
                }

                list.add(JsonUtils.fromJson(JsonUtils.toJson(m), type));
            } catch (Exception e) {
                log.error("Error convert", e);
                //continue process other invoice
            }
        }
        return list;
    }

    public static <M extends BaseModel, T extends BaseVo> List<T> createListVoFromModel(List<M> listModel, Class<T> type) throws Exception {
        if (listModel == null)
            return null;
        List<T> list = new ArrayList<T>();
        for (M m : listModel) {
            list.add(JsonUtils.fromJson(JsonUtils.toJson(m), type));
        }
        return list;
    }

}
