package com.gms.xms.weblib.security;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.txndb.vo.SessionInfoVo;
import com.gms.xms.txndb.vo.UserLevelVo;
import com.gms.xms.txndb.vo.UserVo;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class AdminAuthenticationProcessingFilter extends GenericFilterBean implements InitializingBean {
    private Log log = LogFactory.getLog(AdminAuthenticationProcessingFilter.class);
    private ArrayList<Pattern> ignoredServletPaths = new ArrayList<Pattern>();
    private Map<String, List<String>> actionMap = new HashMap<String, List<String>>();
    private Map<String, List<String>> permissionMap = new HashMap<String, List<String>>();
    private boolean enablePermissionCheck = false;
    private String redirectPath;
    private String loginPage;
    private Map<String, List<String>> actionConfig = new HashMap<String, List<String>>();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        log.info("entering AdminAuthenticationProcessingFilter.");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (AppConstants.APP_SETTINGS.getPermissionMap() != null) {
            permissionMap = AppConstants.APP_SETTINGS.getPermissionMap();
        }

        if (AppConstants.APP_SETTINGS.getActionConfigMap() != null) {
            actionMap = AppConstants.APP_SETTINGS.getActionMap();
        }
        request.getSession().setAttribute("ACTION_MAP", actionMap);
        request.getSession().setAttribute("PERMISSION_MAP", permissionMap);
        String servletPath = request.getServletPath();
        boolean bolIgnored = false;
        boolean isContinue = true;
        for (Pattern ignoredPaths : ignoredServletPaths) {
            if (ignoredPaths.matcher(servletPath).matches()) {
                bolIgnored = true;
                break;
            }
        }
        // Check if the user logged.
        String xmsAdminId = (String) request.getSession().getAttribute("SESS_XMS_ADMIN_ID");
        boolean isLogged = StringUtils.isBlank(xmsAdminId) ? false : true;
        Cookie[] cookies = request.getCookies();
        String langId = SystemSettingCache.getInstance().getValueByKey("Language");
        String langCode = AppConstants.APP_SETTINGS.getDefaultLanguageCode();
        if (!bolIgnored && !isLogged) {
            SessionInfoDao sessionInfoDao = new SessionInfoDao();
            request.getSession().setAttribute("SESS_XMS_ADMIN_ID", null);
            request.getSession().setAttribute("SESS_XMS_ADMIN_EMAIL", null);
            request.getSession().setAttribute("SESS_XMS_ADMIN_CODE", null);
            request.getSession().setAttribute("SESS_XMS_ADMIN_NAME", null);
            request.getSession().setAttribute("SESS_XMS_ADMIN_LEVEL_ID", null);
            request.getSession().setAttribute("SESS_XMS_COLLECTOR", null);
            request.getSession().setAttribute("SESS_XMS_ISREQUIRECHANGE", null);
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    log.info("Cookies:" + cookie.getName() + " - " + cookie.getValue());
                    if ("PHPSESSID".equalsIgnoreCase(cookie.getName())) {
                        SessionInfoVo infoVo;
                        try {
                            infoVo = sessionInfoDao.getBySessionId(cookie.getValue());
                        } catch (DaoException e) {
                            infoVo = null;
                        }
                        if (infoVo != null) {
                            Type type = new TypeToken<Map<String, String>>() {
                            }.getType();
                            Map<String, String> map = GsonUtils.fromGson(infoVo.getContent(), type);
                            if (map != null && StringUtils.isNotBlank(map.get("SESS_XMS_ADMIN_ID"))) {
                                request.getSession().setAttribute("SESS_XMS_ADMIN_ID", map.get("SESS_XMS_ADMIN_ID"));
                                request.getSession().setAttribute("SESS_XMS_ADMIN_EMAIL", map.get("SESS_XMS_ADMIN_EMAIL"));
                                request.getSession().setAttribute("SESS_XMS_ADMIN_CODE", map.get("SESS_XMS_ADMIN_CODE"));
                                request.getSession().setAttribute("SESS_XMS_ADMIN_NAME", map.get("SESS_XMS_ADMIN_NAME"));
                                request.getSession().setAttribute("SESS_XMS_ADMIN_LEVEL_ID", map.get("SESS_XMS_ADMIN_LEVEL_ID"));
                                String adminLevelId = map.get("SESS_XMS_ADMIN_LEVEL_ID");
                                Double adminLevel = null;
                                Integer showCollector = 0;
                                try {
                                    UserLevelDao dao = new UserLevelDao();
                                    UserLevelVo userLevelVo = dao.selectById(Integer.valueOf(adminLevelId));
                                    adminLevel = userLevelVo.getUserLevelCode();
                                    UserDao userDao = new UserDao();
                                    String adminId = map.get("SESS_XMS_ADMIN_ID");
                                    UserVo userVo = userDao.getUserById(adminId);
                                    if (userVo.getIsCollector()) {
                                        showCollector = 1;
                                    } else {
                                        showCollector = 0;
                                    }
                                } catch (Exception e) {
                                    log.error(e);
                                }
                                request.getSession().setAttribute("SESS_XMS_ADMIN_COLLECTOR", showCollector);
                                request.getSession().setAttribute("SESS_XMS_ADMIN_LEVEL", adminLevel.toString());
                                request.getSession().setAttribute("SESS_XMS_COLLECTOR", map.get("SESS_XMS_COLLECTOR"));
                                request.getSession().setAttribute("SESS_XMS_ISREQUIRECHANGE", map.get("SESS_XMS_ISREQUIRECHANGE"));
                                isLogged = true;
                                log.info("UserInfo: " + map + "| admin level :" + request.getSession().getAttribute("SESS_XMS_ADMIN_LEVEL"));
                            }
                        }
                    }
                }
            }
            // Check if the user logged?
            if (!bolIgnored && !isLogged) {
                // If it's not then redirect user to the login page.
                response.sendRedirect(this.getLoginPage());
                isContinue = false;
            }
        }

        // Set back language from cookies
        if (cookies != null) {
            for (Cookie cookie2 : cookies) {
                if ("SESS_LANGUAGE_NAME".equalsIgnoreCase(cookie2.getName())) {
                    if (cookie2.getValue() != null) {
                        langId = cookie2.getValue();
                    }
                }
            }
            // Get language code
            LanguageDao languageDao = new LanguageDao();
            LanguageCodeDao languageCodeDao = new LanguageCodeDao();
            try {
                String languageName = languageDao.getLanguageNameByLanguageId(Integer.valueOf(langId));
                if (!StringUtils.isEmpty(languageName)) {
                    String languageCode = languageCodeDao.getLanguageCodeByLanguageName(languageName);
                    if (!StringUtils.isEmpty(languageCode)) {
                        langCode = languageCode;
                    }
                }
            } catch (Exception e) {
                log.error(e);
            }
            request.getSession().setAttribute(Attributes.XMS_LANG, langCode);
        }

        if (!bolIgnored && isContinue && enablePermissionCheck) {
            String adminLevel = (String) request.getSession().getAttribute("SESS_XMS_ADMIN_LEVEL");
            try {
                adminLevel = String.valueOf(Double.valueOf(adminLevel).intValue());
                //XTD-58 set admin level int value for jsp check.
                request.getSession().setAttribute("SESS_XMS_ADMIN_LEVEL_INT", adminLevel);
            } catch (Exception e) {
                log.error(e);
                adminLevel = null;
            }
            String adminName = (String) request.getSession().getAttribute("SESS_XMS_ADMIN_CODE");
            log.info("Check permission for " + adminLevel + "| action: " + servletPath);
            if (!AppUtils.checkPermission(adminLevel, servletPath, permissionMap, actionMap)) {
                log.error("Access denied.| User: " + adminName + "| Level: " + adminLevel + "| When access to :" + servletPath);
                String reqType = request.getParameter(Attributes.REQUEST_TYPE);
                response.sendRedirect(redirectPath + "?reqType=" + reqType + "&errorId=10");
                isContinue = false;
            }
        }
        if (isContinue) {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void afterPropertiesSet() {
        actionMap = new HashMap<String, List<String>>();
        if (actionConfig != null) {
            for (Entry<String, List<String>> entry : actionConfig.entrySet()) {
                for (String action : entry.getValue()) {
                    if (!actionMap.containsKey(action))
                        actionMap.put(action, new ArrayList<String>());
                    actionMap.get(action).add(entry.getKey());
                }
            }
        }
    }

    public void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }

    public void setIgnoredServletPaths(List<String> patterns) {
        ignoredServletPaths.clear();
        for (String pattern : patterns) {
            ignoredServletPaths.add(Pattern.compile(pattern));
        }
    }

    public Map<String, List<String>> getPermissionMap() {
        return permissionMap;
    }

    public void setPermissionMap(Map<String, List<String>> permissionMap) {
        this.permissionMap = permissionMap;
    }

    public boolean isEnablePermissionCheck() {
        return enablePermissionCheck;
    }

    public void setEnablePermissionCheck(boolean enablePermissionCheck) {
        this.enablePermissionCheck = enablePermissionCheck;
    }

    public Map<String, List<String>> getActionConfig() {
        return actionConfig;
    }

    public void setActionConfig(Map<String, List<String>> actionConfig) {
        this.actionConfig = actionConfig;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
