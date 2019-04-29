package com.gms.xms.weblib.controller;

import com.gms.xms.cache.LocalizationCache;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from ActionBaseController.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:50:11 PM
 */
public abstract class ActionBaseController extends ActionSupport implements InitializingBean, DisposableBean, ParameterAware, ServletRequestAware, ServletResponseAware {
    /**
     *
     */
    private static final long serialVersionUID = 4275545217264574932L;

    protected Log log = LogFactory.getLog(ActionBaseController.class);
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    private String pageTitle;
    private String breadCrumb;
    private String systemAddress = SystemSettingCache.getInstance().getValueByKey("System Address");
    private String currencySymbol = SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL);

    public String getServerPath(String virtualPath) {
        return request.getSession().getServletContext().getRealPath(virtualPath);
    }

    public Object getSession(String sessionName) {
        return request.getSession().getAttribute(sessionName);
    }

    public void setSession(String sessionName, Object sessionValue) {
        request.getSession().setAttribute(sessionName, sessionValue);
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setParameters(Map<String, String[]> arg0) {
    }

    @Override
    public void destroy() throws Exception {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getBreadCrumb() {
        return breadCrumb;
    }

    public void setBreadCrumb(String breadCrumb) {
        this.breadCrumb = breadCrumb;
    }

    protected void addFieldErrors(Map<String, List<String>> fieldErrorMap) {
        if (fieldErrorMap == null) {
            return;
        }
        for (Map.Entry<String, List<String>> entry : fieldErrorMap.entrySet()) {
            String field = entry.getKey();
            List<String> msgList = entry.getValue();
            if (msgList != null) {
                for (String message : msgList) {
                    addFieldError(field, message);
                }
            }
        }
    }

    protected WebshipLoginVo getWebshipLoginInfo() {
        return (WebshipLoginVo) this.getSession(Attributes.SESS_XMS_WEBSHIP_LOGIN_INFO);
    }

    public boolean canLoginAs() {
        WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
        if (webshipLoginVo == null)
            return false;
        Long customerCode = webshipLoginVo.getParentCustomerCode() == null ? webshipLoginVo.getCustomerCode() : webshipLoginVo.getParentCustomerCode();
        return "00001".equalsIgnoreCase(StringUtils.right(String.valueOf(customerCode), 5));
    }

    public String getSystemAddress() {
        systemAddress = systemAddress.replaceAll("(\r\n|\n)", "<br />");
        return systemAddress;
    }

    public void setSystemAddress(String systemAddress) {
        this.systemAddress = systemAddress;
    }

    protected List<String> buildPageSizeList() {
        String pageSizeList = AppConstants.APP_SETTINGS.getPageSizeList();
        String[] pageSizes = pageSizeList.trim().split(",");
        return Arrays.asList(pageSizes);
    }

    public String getIpAddress() {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    protected void setXmsLang(String xmsLang) {
        request.getSession().setAttribute(Attributes.XMS_LANG, xmsLang);
    }

    protected String getXmsLang() {
        if (request.getSession() == null) {
            return AppConstants.DEFAULT_LANG;
        }
        Object lang = request.getSession().getAttribute(Attributes.XMS_LANG);
        return lang == null ? AppConstants.DEFAULT_LANG : (String) lang;
    }

    protected String getLocalizationText(String key) {
        String text = LocalizationCache.getInstance().getValueByKey(this.getXmsLang(), key);
        return text;
    }

    @SuppressWarnings("unchecked")
    protected Map<String, String> getAddInfoMap() {
        Map<String, String> addMap = new HashMap<String, String>();
        addMap.put(Attributes.XMS_LANG, getXmsLang());
        //FIX NPE when request.getSession() = null
        if (request.getSession() != null) {
            Object obj = request.getSession().getAttribute(Attributes.ADD_INFO_EXT);
            if (obj != null) {
                addMap.putAll((Map<String, String>) obj);
            }
        }
        return addMap;
    }
}
