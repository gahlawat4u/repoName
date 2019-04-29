package com.gms.xms.weblib.security;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.JsonUtils;
import com.gms.xms.model.JsonModel;
import org.apache.commons.lang.StringUtils;
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
import java.io.OutputStream;

public class JsonRequestFilter extends GenericFilterBean implements InitializingBean {
    private static Log log = LogFactory.getLog(ContextBase.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        log.info("entering JsonRequestFilter.");
        String reqType = req.getParameter("reqType");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        AppConstants.APP_SETTINGS.setAppTmpPath(request.getSession().getServletContext().getRealPath("/tmp"));
//	log.warn("APP TMP PATH:"+AppConstants.APP_SETTINGS.getAppTmpPath());
        if ("json".equalsIgnoreCase(reqType)) {
            BufferedHttpResponseWrapper wrapper = new BufferedHttpResponseWrapper(response);
            filterChain.doFilter(req, wrapper);
            JsonModel jsonModel = new JsonModel();
            String errorType = (String) request.getAttribute(Attributes.ERROR_TYPE);
            String errorCode = (String) request.getAttribute(Attributes.ERROR_CODE);
            String errorMessage = (String) request.getAttribute(Attributes.ERROR_MESSAGE);

            errorCode = StringUtils.isBlank(errorCode) ? ErrorCode.SUCCESS : errorCode;
            errorMessage = StringUtils.isBlank(errorMessage) ? "" : errorMessage;

            jsonModel.setErrorCode(errorCode);
            jsonModel.setErrorMsg(errorMessage);
            jsonModel.setErrorType(errorType);

            jsonModel.setContent(new String(wrapper.getOutput()));
            OutputStream out = res.getOutputStream();
            res.setContentType("application/json");

            try {
                out.write(JsonUtils.toJson(jsonModel).getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.flush();
        } else {
            filterChain.doFilter(req, res);
        }
    }

    @Override
    public void afterPropertiesSet() {
        // TODO Auto-generated method stub
    }
}
