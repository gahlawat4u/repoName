package com.gms.xms.cache;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.txndb.vo.SystemSettingVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Posted from SystemSettingCache
 * <p>
 * Author TanDT Date Apr 16, 2015
 */
public class SystemSettingCache implements BaseCache {

    protected static Log log = LogFactory.getLog(SystemSettingCache.class);

    private Map<String, SystemSettingVo> cacheMap = new HashMap<String, SystemSettingVo>();

    private static SystemSettingCache instance = null;

    static {
        instance = new SystemSettingCache();
    }

    private SystemSettingCache() {
        reload();
    }

    public static SystemSettingCache getInstance() {
        return instance;
    }

    public String getValueByKey(String key) {
        String result = null;
        if (StringUtils.isBlank(key)) {
            return null;
        }


        SystemSettingVo vo = cacheMap.get(key.toLowerCase());
        if (vo != null) {
            result = vo.getSettingValue();
        } else {
            result = null;
        }

        if (result == null) {
            SystemSettingDao systemSettingDao = new SystemSettingDao();
            SystemSettingVo settingVo = null;
            try {
                settingVo = systemSettingDao.getSystemSettingByName(key);
            } catch (DaoException e) {
                settingVo = null;
                log.error(e);
            }
            if (settingVo == null) {
                result = null;
            } else {
                result = settingVo.getSettingValue();
            }
        }
        return result;
    }

    public SystemSettingVo getObjectByKey(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return cacheMap.get(key.toLowerCase());
    }

    @Override
    public void reload() {
        Map<String, SystemSettingVo> tmpCacheMap = new HashMap<String, SystemSettingVo>();
        SystemSettingDao systemSettingDao = new SystemSettingDao();
        List<SystemSettingVo> vos = null;
        try {
            vos = systemSettingDao.getAll();
        } catch (DaoException e) {
            log.error("Fail to load LocalizationCache", e);
        }
        if (vos != null) {
            for (SystemSettingVo vo : vos) {
                if (vo != null && StringUtils.isNotBlank(vo.getSettingName())) {
                    tmpCacheMap.put(vo.getSettingName().toLowerCase(), vo);
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
