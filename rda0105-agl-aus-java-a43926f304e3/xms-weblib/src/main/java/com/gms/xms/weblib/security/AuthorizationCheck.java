package com.gms.xms.weblib.security;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.SessionInfoDao;
import com.gms.xms.txndb.vo.SessionInfoVo;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class AuthorizationCheck implements Filter {
    private Log log = LogFactory.getLog(AuthorizationCheck.class);

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        SessionInfoDao sessionInfoDao = new SessionInfoDao();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Cookie[] cookies = req.getCookies();
        req.getSession().setAttribute("SESS_XMS_ADMIN_ID", null);
        req.getSession().setAttribute("SESS_XMS_ADMIN_EMAIL", null);
        req.getSession().setAttribute("SESS_XMS_ADMIN_CODE", null);
        req.getSession().setAttribute("SESS_XMS_ADMIN_NAME", null);
        req.getSession().setAttribute("SESS_XMS_ADMIN_LEVEL_ID", null);
        req.getSession().setAttribute("SESS_XMS_COLLECTOR", null);
        req.getSession().setAttribute("SESS_XMS_ISREQUIRECHANGE", null);
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
                            req.getSession().setAttribute("SESS_XMS_ADMIN_ID", map.get("SESS_XMS_ADMIN_ID"));
                            req.getSession().setAttribute("SESS_XMS_ADMIN_EMAIL", map.get("SESS_XMS_ADMIN_EMAIL"));
                            req.getSession().setAttribute("SESS_XMS_ADMIN_CODE", map.get("SESS_XMS_ADMIN_CODE"));
                            req.getSession().setAttribute("SESS_XMS_ADMIN_NAME", map.get("SESS_XMS_ADMIN_NAME"));
                            req.getSession().setAttribute("SESS_XMS_ADMIN_LEVEL_ID", map.get("SESS_XMS_ADMIN_LEVEL_ID"));
                            req.getSession().setAttribute("SESS_XMS_COLLECTOR", map.get("SESS_XMS_COLLECTOR"));
                            req.getSession().setAttribute("SESS_XMS_ISREQUIRECHANGE", map.get("SESS_XMS_ISREQUIRECHANGE"));
                        }
                    }
                } else {

                }
            }
        }

        if (req.getSession().getAttribute("SESS_XMS_ADMIN_ID") == null || StringUtils.isBlank((String) req.getSession().getAttribute("SESS_XMS_ADMIN_ID"))) {
            String reqType = request.getParameter(Attributes.REQUEST_TYPE);
            if (AppConstants.JSON.equalsIgnoreCase(reqType)) {
                res.sendRedirect("accessdenied_data.jsp");
            } else {
                res.sendRedirect("accessdenied.jsp");
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
