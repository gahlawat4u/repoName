package com.gms.xms.weblib.security;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PrepareParamFilter extends GenericFilterBean implements InitializingBean {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        Map<String, String> addInfoExtMap = new HashMap<String, String>();
        HttpServletRequest request = (HttpServletRequest) req;
        // Get user info.
        String userId = null, username = null, userCode = null, userType = null;
        String isAdmin = null;
        if (request.getSession().getAttribute("SESS_XMS_ADMIN_NAME") != null) {
            userId = (String) request.getSession().getAttribute("SESS_XMS_ADMIN_ID");
            username = (String) request.getSession().getAttribute("SESS_XMS_ADMIN_NAME");
            userCode = (String) request.getSession().getAttribute("SESS_XMS_ADMIN_CODE");
            userType = null;
            isAdmin = AppConstants.YES_FLAG;
        } else {
            WebshipLoginVo loginVo = (WebshipLoginVo) request.getSession().getAttribute(Attributes.SESS_XMS_WEBSHIP_LOGIN_INFO);
            if (loginVo != null) {
                userId = String.valueOf(loginVo.getWebshipId());
                username = loginVo.getName();
                userCode = String.valueOf(loginVo.getCustomerCode());
                userType = userCode.endsWith("00001") ? "1" : "2";
            }
            isAdmin = AppConstants.NO_FLAG;
        }
        userId = (userId == null) ? "0" : userId;
        username = (username == null) ? "" : username;
        userCode = (userCode == null) ? "0" : userCode;
        userType = (userType == null) ? "0" : userType;
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        ipAddress = (ipAddress == null) ? "" : ipAddress;
        addInfoExtMap.put(Attributes.ADD_INFO_EXT_USERNAME, username);
        addInfoExtMap.put(Attributes.ADD_INFO_EXT_USER_CODE, userCode);
        addInfoExtMap.put(Attributes.ADD_INFO_EXT_USER_ID, userId);
        addInfoExtMap.put(Attributes.ADD_INFO_EXT_USER_TYPE, userType);
        addInfoExtMap.put(Attributes.ADD_INFO_EXT_IS_ADMIN, isAdmin);
        addInfoExtMap.put(Attributes.ADD_INFO_EXT_IP, ipAddress);
        request.getSession().setAttribute(Attributes.ADD_INFO_EXT, addInfoExtMap);
        // Prepare default parameter to request.
        filterChain.doFilter(req, res);
    }

    @Override
    public void afterPropertiesSet() {
        // TODO Auto-generated method stub
    }
}
