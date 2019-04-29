package com.gms.xms.workflow.helper;

import com.gms.xms.cache.LocalizationCache;
import com.gms.xms.common.constants.Attributes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from FtlLocalizationHelper
 * </p>
 *
 * @author hungnt - Nov 28, 2015
 */
public class ExportLocalizationHelper {
    private Log log = LogFactory.getLog(ExportLocalizationHelper.class);
    private Map<String, String> addInfo = null;

    public ExportLocalizationHelper(Map<String, String> addInfo) {
        super();
        this.addInfo = addInfo;
    }

    public String translate(String key) {
        String langCode = "";
        try {
            if (this.addInfo != null) {
                langCode = addInfo.get(Attributes.XMS_LANG);
            } else {
                log.error("Fail to get langCode. addInfo was not given");
            }
        } catch (Exception e) {
            log.error("Fail to get langCode", e);
            langCode = "";
        }
        return LocalizationCache.getInstance().getValueByKey(langCode, key);
    }
}
