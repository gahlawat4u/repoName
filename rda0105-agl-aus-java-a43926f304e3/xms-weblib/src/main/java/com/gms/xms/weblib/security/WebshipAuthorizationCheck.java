package com.gms.xms.weblib.security;

import com.gms.xms.common.constants.Attributes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WebshipAuthorizationCheck extends GenericFilterBean implements InitializingBean {
    private Log log = LogFactory.getLog(WebshipAuthorizationCheck.class);

    private ArrayList<Pattern> ignoredServletPaths = new ArrayList<Pattern>();
    private String redirectPath;

    public void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }

    public void setIgnoredServletPaths(List<String> patterns) {
        ignoredServletPaths.clear();
        for (String pattern : patterns) {
            ignoredServletPaths.add(Pattern.compile(pattern));
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String servletPath = request.getServletPath();
        log.info("Going to check :" + servletPath);
        boolean bolIgnored = false;
        boolean isContinue = true;
        for (Pattern ignoredPaths : ignoredServletPaths) {
            if (ignoredPaths.matcher(servletPath).matches()) {
                bolIgnored = true;
                break;
            }
        }
        if (!bolIgnored) {
            if (request.getSession().getAttribute(Attributes.SESS_XMS_WEBSHIP_LOGIN_INFO) == null) {
                isContinue = false;
                log.error("Access denied. When access to :" + servletPath);
                String reqType = request.getParameter(Attributes.REQUEST_TYPE);
                response.sendRedirect(redirectPath + "?reqType=" + reqType + "&errorId=10");
            }
        }
        if (isContinue) {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void afterPropertiesSet() {
        // TODO Auto-generated method stub
    }
}
