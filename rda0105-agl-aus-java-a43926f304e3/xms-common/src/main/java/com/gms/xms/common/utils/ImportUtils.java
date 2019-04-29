package com.gms.xms.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from ImportUtils
 * <p>
 * Author HungNT Date May 12, 2015
 */
public class ImportUtils {
    protected static Log log = LogFactory.getLog(AppUtils.class);

    /**
     * Transfer from selected fields' value to model properties
     *
     * @param Model   containing fields
     * @param Hashmap <field - property>
     * @param result  model
     * @return
     */
    public static <T, M> T transferImportFieldsToModel(M fields, Map<String, String> fieldMap, T result) {
        try {
            for (int i = 1; i < fieldMap.size() + 1; i++) {
                BeanUtils.setProperty(result, fieldMap.get("field" + i), BeanUtils.getProperty(fields, "field" + i));
            }
        } catch (Exception e) {
            log.error(e);
            return null;
        }
        return result;
    }

    public static <T, M> T transferModelToImportFields(M models, Map<String, String> fieldMap, T fields) {
        try {

            for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String model = BeanUtils.getProperty(models, value);
                BeanUtils.setProperty(fields, key, model);
            }
        } catch (Exception e) {
            log.error(e);
            return null;
        }
        return fields;
    }
}
