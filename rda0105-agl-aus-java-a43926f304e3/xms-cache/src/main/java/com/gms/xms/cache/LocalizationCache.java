package com.gms.xms.cache;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.persistence.service.languagevalue.ILanguageValueService;
import com.gms.xms.persistence.service.languagevalue.LanguageValueServiceImp;
import com.gms.xms.txndb.vo.LanguageValueVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from LocalizationCache.java
 * <p>
 * Author Toantq Date Apr 2, 2015 Time: 2:08:46 PM
 */
public class LocalizationCache implements BaseCache {

    protected static Log log = LogFactory.getLog(LocalizationCache.class);

    private Map<String, Map<String, String>> cacheMap = new HashMap<String, Map<String, String>>();

    private ILanguageValueService service = new LanguageValueServiceImp();

    private static LocalizationCache instance = null;

    static {
        instance = new LocalizationCache();
    }

    private LocalizationCache() {
        reload();
    }

    public static LocalizationCache getInstance() {
        return instance;
    }

    public String getValueByKey(String langCode, String key) {
        if (StringUtils.isBlank(langCode) || StringUtils.isBlank(key)) {
            return key;
        }
        String mKey = Jsoup.clean(key, Whitelist.none());
        mKey = Jsoup.clean(mKey, Whitelist.none());
        langCode = langCode.toLowerCase();
        Map<String, String> langMap = cacheMap.get(langCode);
        Map<String, String> context = new HashMap<String, String>();
        if (langMap == null) {
            LanguageValueVo languageValueVo = new LanguageValueVo();
            try {
                languageValueVo = new LanguageValueVo();
                languageValueVo.setLangCode(langCode);
                languageValueVo.setOriginal(mKey);
                LanguageValueVo languageValueVoCheck = service.getLanguageValueByKey(languageValueVo);
                if (languageValueVoCheck == null) {
                    languageValueVo.setDestination(key);
                    languageValueVo.setMode("SYS");
                    service.insertLanguageValue(context, languageValueVo);
                    reload();
                }
            } catch (Exception e) {
                log.error("Find to auto insert language for:" + languageValueVo, e);
            }
            return key;
        }
        String value = langMap.get(mKey);
        if (value == null) {
            LanguageValueVo languageValueVo = new LanguageValueVo();
            try {
                languageValueVo = new LanguageValueVo();
                languageValueVo.setLangCode(langCode);
                languageValueVo.setOriginal(mKey);
                LanguageValueVo languageValueVoCheck = service.getLanguageValueByKey(languageValueVo);
                if (languageValueVoCheck == null) {
                    languageValueVo.setDestination(key);
                    languageValueVo.setMode("SYS");
                    service.insertLanguageValue(context, languageValueVo);
                    reload();
                }
            } catch (Exception e) {
                log.error("Find to auto insert language for:" + languageValueVo, e);
            }
            return key;
        } else {
            if (AppConstants.APP_SETTINGS.getEnableShowLangcode().equalsIgnoreCase("true")) {
                return "[" + langCode.toUpperCase() + "] " + value;
            } else {
                return value;
            }
        }
    }

    public String getValueByKey(String langCode, String key, Map<String, String> data) {
        return AppUtils.replaceStringByMap(data, getValueByKey(langCode, key));
    }

    @Override
    public void reload() {
        Map<String, Map<String, String>> tmpCacheMap = new HashMap<String, Map<String, String>>();
        List<LanguageValueVo> vos = null;
        try {
            vos = service.getLanguageValueList(null);
        } catch (DaoException e) {
            log.error("Fail to load LocalizationCache", e);
        }
        if (vos != null) {
            for (LanguageValueVo languageValueVo : vos) {
                String langCode = languageValueVo.getLangCode();
                if (languageValueVo != null && StringUtils.isNotBlank(languageValueVo.getLangCode()) && StringUtils.isNotBlank(languageValueVo.getOriginal())) {
                    langCode = langCode.toLowerCase();
                    if (tmpCacheMap.get(langCode) == null) {
                        tmpCacheMap.put(langCode, new HashMap<String, String>());
                    }
                    tmpCacheMap.get(langCode).put(languageValueVo.getOriginal(), languageValueVo.getDestination());
                }
            }
        }
        synchronized (cacheMap) {
            cacheMap.clear();
            cacheMap.putAll(tmpCacheMap);
        }
    }

    @Override
    public String toString() {
        return "LocalizationCache [cacheMap=" + cacheMap + "]";
    }
}
