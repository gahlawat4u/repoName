package com.gms.xms.common.context;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Posted from ContextBase.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:36:12 PM
 */

public class ContextBase2 extends ConcurrentHashMap<String, Object> {
    private static final long serialVersionUID = 1643777706070770107L;

    public ContextBase2(Map<String, String> addMap) {
        super();
        this.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        if (addMap != null) {
            this.putAll(addMap);
            String addInfoStr = GsonUtils.toGson(addMap, new TypeToken<Map<String, String>>() {
            }.getType());
            this.put(Attributes.STR_ADD_INFO, addInfoStr);
        }
    }

    @SuppressWarnings("unused")
    private ContextBase2() {
        super();
        this.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) super.get(key);
    }

    public Double getDouble(String key) {
        return (Double) this.get(key);
    }

    public String getString(String key) {
        return (String) this.get(key);
    }

    public Integer getInt(String key) {
        return (Integer) this.get(key);
    }

    public Float getFloat(String key) {
        return (Float) this.get(key);
    }

    public Byte getByte(String key) {
        return (Byte) this.get(key);
    }

    public BigDecimal getBigDecimal(String key) {
        return (BigDecimal) this.get(key);
    }

    public Long getLong(String key) {
        return (Long) this.get(key);
    }

    public Character getChar(String key) {
        return (Character) this.get(key);
    }

    public Boolean getBoolean(String key) {
        return (Boolean) this.get(key);
    }

    public <U, V> Map<U, V> getMap(String key) {
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<U, V> result = GsonUtils.fromGson(this.getString(key), type);
        return result;
    }

    @Override
    public String toString() {
        Map<String, Object> map = new HashMap<>();
        map.putAll(this);
        for (String param : AppConstants.APP_SETTINGS.getIgnoredCtxParamLogList()) {
            if (StringUtils.isNotBlank(param) && map.get(param) != null) {
                map.put(param, "...");
            }
        }
        return map.toString();
    }
}
