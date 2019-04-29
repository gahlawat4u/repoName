package com.gms.xms.workflow.helper;

import com.gms.xms.cache.LocalizationCache;
import com.gms.xms.common.constants.Attributes;

import java.util.Map;

/**
 * Posted from LocalizationHelper.java
 * <p>
 * Author dattrinh 5:46:42 PM
 */
public class LocalizationHelper {
    private static LocalizationHelper instance = new LocalizationHelper();

    private LocalizationHelper() {
    }

    public static LocalizationHelper getInstance() {
        return instance;
    }

    public String getLocalization(Map<String, String> context, String key) {
        return LocalizationCache.getInstance().getValueByKey(context.get(Attributes.XMS_LANG), key);
    }
}