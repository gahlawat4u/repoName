package com.gms.xms.cache;

import com.gms.xms.common.constants.AppConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * Posted from PackageAddConfigCache
 * <p>
 * Author HungNT Date Apr 15, 2015
 */
public class PackageAddConfigCache implements BaseCache {

    protected static Log log = LogFactory.getLog(PackageAddConfigCache.class);

    private Map<String, Map<String, String>> cacheMap = new HashMap<String, Map<String, String>>();

    private static PackageAddConfigCache instance = null;

    static {
        instance = new PackageAddConfigCache();
    }

    private PackageAddConfigCache() {
        reload();
    }

    public static PackageAddConfigCache getInstance() {
        return instance;
    }

    public String getValueByKey(int shipmentTypeId, int packageId, String type, String name) {
        return AppConstants.YES_FLAG;
    }

    @Override
    public void reload() {
    /*
	Map<String, Map<String, String>> tmpCacheMap = new HashMap<String, Map<String, String>>();
	LanguageValueDao languageValueDaoService = new LanguageValueDao();
	List<LanguageValueVo> vos = null;
	try {
	    vos = languageValueDaoService.getAll();
	} catch (DaoException e) {
	    log.error("Fail to load LocalizationCache", e);
	}
	if (vos != null) {
	    for (LanguageValueVo languageValueVo : vos) {
		if (languageValueVo != null && StringUtils.isNotBlank(languageValueVo.getLangCode()) && StringUtils.isNotBlank(languageValueVo.getOrginal())) {
		    if (tmpCacheMap.get(languageValueVo.getLangCode()) == null) {
			tmpCacheMap.put(languageValueVo.getLangCode(), new HashMap<String, String>());
		    }
		    tmpCacheMap.get(languageValueVo.getLangCode()).put(languageValueVo.getOrginal(), languageValueVo.getDestination());
		}
	    }
	}

	synchronized (cacheMap) {
	    cacheMap.clear();
	    cacheMap.putAll(tmpCacheMap);
	}
	*/
    }

    @Override
    public String toString() {
        return "LocalizationCache [cacheMap=" + cacheMap + "]";
    }

}
