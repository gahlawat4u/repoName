package com.gms.xms.weblib.tag;

import com.gms.xms.cache.LocalizationCache;
import com.gms.xms.common.constants.Attributes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

public class TaglibHelper {
    private Log log = LogFactory.getLog(TaglibHelper.class);
    private HttpServletRequest request = null;

    public TaglibHelper(HttpServletRequest request) {
        super();
        this.request = request;
    }

    public String getLocalizationText(String text) {
        String langCode = "";
        try {
            if (request != null) {
                langCode = (String) request.getSession().getAttribute(Attributes.XMS_LANG);
            } else {
                log.error("Fail to get langCode. Request not init yet");
            }
        } catch (Exception e) {
            log.error("Fail to get langCode", e);
            langCode = "";
        }
        return LocalizationCache.getInstance().getValueByKey(langCode, text);
    }

    public String getLocalizationText(String langCode, String text) {
        return LocalizationCache.getInstance().getValueByKey(langCode, text);
    }
}
