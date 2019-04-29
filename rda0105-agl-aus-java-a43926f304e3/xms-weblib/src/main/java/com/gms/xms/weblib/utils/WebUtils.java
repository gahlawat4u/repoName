/**
 *
 */
package com.gms.xms.weblib.utils;

import com.gms.xms.cache.LocalizationCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.AppConstants.ENCODE_TYPE;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.LocalizationDao;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.txndb.vo.admin.MenuVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Posted from WebUtils.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 6:00:38 PM
 */
public class WebUtils {

    /**
     * Return url context path of web application
     *
     * @param request - request object
     * @return - string url
     */
    public static String getContextPathURL(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        return basePath;
    }

    public static String getContextPathURLPHP(HttpServletRequest request) {
        String basePath = "http" + "://" + request.getServerName() + "/";
        return basePath;
    }

    /**
     * return tmp directory of web application
     *
     * @param request - request object
     * @return - path of tmp directory
     */
    public static String getWebTmpPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("") + "/tmp/";
    }
    
    public static String getWebLogoPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("") + "/profiles/";
    }

    public static String getLangCode(HttpServletRequest request) {
        String langCode = (String) request.getSession().getAttribute(Attributes.SESS_USER_LANG_CODE);
        if (StringUtils.isBlank(langCode)) {
            langCode = AppConstants.APP_SETTINGS.getDefaultLanguageCode();
        }
        return langCode;
    }

    public static String getHomeUrl(HttpServletRequest request) {
        String serverName = "";
        String enableHome = AppConstants.APP_SETTINGS.getEnablePHPHome();
        String phpPath = AppConstants.APP_SETTINGS.getPhpHomePath();
        serverName = getContextPathURL(request);
        if (!StringUtils.isBlank(enableHome) && enableHome.equalsIgnoreCase("Y")) {
            serverName = request.getScheme() + "://" + request.getServerName() + phpPath;
        }
        return serverName;
    }

    public static WebshipLoginVo getWebshipLoginInfo(HttpServletRequest request) {
        return (WebshipLoginVo) request.getSession().getAttribute(Attributes.SESS_XMS_WEBSHIP_LOGIN_INFO);
    }

    public static String getLocalization(HttpServletRequest request, String key, ENCODE_TYPE type) {
        String result = LocalizationCache.getInstance().getValueByKey(getLangCode(request), key);
        switch (type) {
            case CSV:
                result = StringEscapeUtils.escapeCsv(result);
                break;
            case XML:
                result = StringEscapeUtils.escapeXml(result);
                break;
            case HTML:
                result = StringEscapeUtils.escapeHtml4(result);
                break;
            case JAVASCRIPT:
                result = StringEscapeUtils.escapeEcmaScript(result);
                break;
            default:
                break;
        }
        return result;
    }

    public static String getTooltip(HttpServletRequest request, String key, ENCODE_TYPE type) {
        String key1 = key;
        if (StringUtils.isNotBlank(key)) {
            key1 = "TOOLTIP:" + key;
        }
        String result = LocalizationCache.getInstance().getValueByKey(getLangCode(request), key1);
        switch (type) {
            case CSV:
                result = StringEscapeUtils.escapeCsv(result);
                break;
            case XML:
                result = StringEscapeUtils.escapeXml(result);
                break;
            case HTML:
                result = StringEscapeUtils.escapeHtml4(result);
                break;
            case JAVASCRIPT:
                result = StringEscapeUtils.escapeEcmaScript(result);
                break;
            default:
                break;
        }
        return result;
    }

    public static String genAllMenu(List<MenuVo> menuVos, String contextPath, String contextPathPHP, String langCode) {
        String str = "";
        String url = "";
        String icon = "";
        for (MenuVo vo : menuVos) {
            url = vo.getUrl() == null ? "" : vo.getUrl().trim();
            if ("#".equalsIgnoreCase(url)) {
                str += "<li>";
                icon = getMenuIconClass(vo.getMenuName());
                if (vo.getParentId() == 0) {
                    str += "<a href=\"" + url + "\" class=\"st\"><i class=\"fa " + icon + " fa-fw\"></i><span> " + LocalizationCache.getInstance().getValueByKey(langCode, vo.getMenuName()) + "</span></a>";
                } else {
                    str += "<a href=\"" + url + "\">" + LocalizationCache.getInstance().getValueByKey(langCode, vo.getMenuName()) + "<span class=\"caret\"></span></a>";
                }
                if (vo.getMenuChilds() != null && vo.getMenuChilds().size() > 0) {
                    str += "<ul class=\"dropdown-menu\">";
                    str += genAllMenu(vo.getMenuChilds(), contextPath, contextPathPHP, langCode);
                    str += "</ul>";
                }
                str += "</li>";
            } else {
                str += "<li>";
                if (url.startsWith("XMS2:/")) {
                    url = contextPath + url.replace("XMS2:/", "");
                } else {
                    url = contextPathPHP + url;
                }
                str += "<a href=\"" + url + "\">" + LocalizationCache.getInstance().getValueByKey(langCode, vo.getMenuName()) + "<span class=\"sws mgl\"><i class=\"fa fa-external-link fa-fw sws pst\" onclick=\"window.open('" + url + "');return false;\"></i></span></a>";
                str += "</li>";
            }

        }

        return str;
    }

    private static String getMenuIconClass(String menuName) {
        String icon = "";
        if (menuName.equalsIgnoreCase("account")) {
            icon = "fa-key";
        } else if (menuName.equalsIgnoreCase("invoicing")) {
            icon = "fa-building-o";
        } else if (menuName.equalsIgnoreCase("receivables")) {
            icon = "fa-cubes";
        } else if (menuName.equalsIgnoreCase("payables")) {
            icon = "fa-cube";
        } else if (menuName.equalsIgnoreCase("reports")) {
            icon = "fa-file-text-o";
        } else if (menuName.equalsIgnoreCase("admin")) {
            icon = "fa-user";
        }
        return icon;
    }


    public static String getNotificationText() {
        SystemSettingDao systemSettingDao = new SystemSettingDao();
        boolean notificationEnable;
        String notificationText = "";
        try {
            notificationEnable = systemSettingDao.getSystemSettingByName("notification enable").getSettingValue().equals("Y");
            if (notificationEnable) {
                LocalizationDao localizationDao = new LocalizationDao();
                notificationText = localizationDao.selectLocalizationValueByLocalizationName("notification_text");
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return notificationText;
    }
}
