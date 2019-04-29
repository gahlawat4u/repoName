package com.gms.xms.common.context;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Posted from ContextBase.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:36:12 PM
 */

public class ContextBase extends ConcurrentHashMap<String, String> {
    private static Log log = LogFactory.getLog(ContextBase.class);
    private static final long serialVersionUID = 1643777706070770107L;

    private static String GLOBAL_DATE_FORMAT = "yyyyMMddHHmmssS";

    public ContextBase(Map<String, String> addMap) {
        super();
        this.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        if (addMap != null) {
            this.putAll(addMap);
            this.put(Attributes.STR_ADD_INFO, GsonUtils.toGson(addMap, new TypeToken<Map<String, String>>() {
            }.getType()));
        }
    }

    @SuppressWarnings("unused")
    private ContextBase() {
        super();
        this.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
    }

    /**
     * put integer to context
     *
     * @param key   - string key
     * @param value - int value
     */
    public void put(String key, int value) {
        put(key, Integer.toString(value));

    }

    /**
     * put string to context
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        if (StringUtils.isNotBlank(value)) {
            put(key, value);
        } else {
            put(key, "");
        }
    }

    /**
     * get string from key
     *
     * @param key - string
     * @return - string
     */
    public String getString(String key) {
        String value = "";
        value = get(key);
        if (value != null)
            return value;
        else
            return "";
    }

    /**
     * put date to context
     *
     * @param key   - string key
     * @param value - date value
     */
    public void putDate(String key, Date value) {
        if (value != null) {
            DateFormat df = new SimpleDateFormat(GLOBAL_DATE_FORMAT);
            put(key, df.format(value));
        } else {
            log.info("Fail to put date-" + key + "| value-" + value);
        }
    }

    /**
     * return date from context
     *
     * @param key - string key
     * @return - a date object
     */
    public Date getDate(String key) {
        String keyValue = getString(key);
        if (StringUtils.isNotEmpty(keyValue)) {
            DateFormat dt = new SimpleDateFormat(GLOBAL_DATE_FORMAT);
            try {
                return dt.parse(keyValue);
            } catch (ParseException ex) {
                log.warn(new StringBuilder("key:").append(key).append(",value: ").append(keyValue).append(" is not date").toString());
                return null;
            }
        } else {
            if (log.isDebugEnabled())
                log.debug("key/value is empty");
        }
        return null;
    }

    public String getErrorCode() {
        return this.get(Attributes.ERROR_CODE);
    }

    public String getErrorMessage() {
        return this.get(Attributes.ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        Map<String, String> map = new HashMap<>();
        map.putAll(this);
        for (String param : AppConstants.APP_SETTINGS.getIgnoredCtxParamLogList()) {
            if (StringUtils.isNotBlank(param) && map.get(param) != null) {
                map.put(param, "...");
            }
        }
        return map.toString();
    }
}
