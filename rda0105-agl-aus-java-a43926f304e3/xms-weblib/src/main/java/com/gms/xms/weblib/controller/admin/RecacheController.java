package com.gms.xms.weblib.controller.admin;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.weblib.controller.AdminJsonBaseController;

/**
 * Posted from Aug 23, 2016 4:30:24 PM
 * <p>
 * Author ToanTran
 */
public class RecacheController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;
    private String cacheType;
    private String message;

    public String recache() {
        try {
            if ("APP_SETTING".equalsIgnoreCase(cacheType)) {
                AppConstants.load();
                message = "APP_SETTING recache sucessfully.";
            } else if ("SYSTEM_SETTING".equalsIgnoreCase(cacheType)) {
                SystemSettingCache.getInstance().reload();
                message = "SYS_SETTING recache sucessfully.";
            } else {
                message = "Only support for cacheType in (APP_SETTING, SYSTEM_SETTING) ";
            }

        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }

        return "success";
    }

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
