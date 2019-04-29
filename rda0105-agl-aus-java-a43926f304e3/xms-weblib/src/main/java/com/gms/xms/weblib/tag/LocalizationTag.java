package com.gms.xms.weblib.tag;

import com.gms.xms.cache.LocalizationCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LocalizationTag extends TagSupport {

    private static final long serialVersionUID = 1L;
    private String text;
    private String paramMap;
    private boolean escapeHtml;
    private boolean escapeJavaScript;
    private boolean escapeXml;
    private boolean escapeCsv;
    private String scope;

    public LocalizationTag() {
        this.escapeHtml = false;
        this.escapeJavaScript = false;
        this.escapeXml = false;
        this.escapeCsv = false;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setParamMap(String paramMap) {
        this.paramMap = paramMap;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setEscapeHtml(boolean escapeHtml) {
        this.escapeHtml = escapeHtml;
    }

    public void setEscapeJavaScript(boolean escapeJavaScript) {
        this.escapeJavaScript = escapeJavaScript;
    }

    public void setEscapeXml(boolean escapeXml) {
        this.escapeXml = escapeXml;
    }

    public void setEscapeCsv(boolean escapeCsv) {
        this.escapeCsv = escapeCsv;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String langCode = (String) request.getSession().getAttribute(Attributes.XMS_LANG);
        if (StringUtils.isBlank(langCode)) {
            langCode = AppConstants.APP_SETTINGS.getDefaultLanguageCode();
        }
        JspWriter out = pageContext.getOut();
        try {
            Map<String, String> paramMaps = null;

            if (StringUtils.isNotBlank(paramMap)) {
                if (StringUtils.isBlank(scope) || "REQUEST".equalsIgnoreCase(scope)) {
                    if (request.getAttribute("paramMap") != null) {
                        paramMaps = (Map<String, String>) request.getAttribute("paramMap");
                    }
                } else if ("SESSION".equalsIgnoreCase(scope)) {
                    if (request.getSession().getAttribute("paramMap") != null) {
                        paramMaps = (Map<String, String>) request.getSession().getAttribute("paramMap");
                    }
                }

            }

            Map<String, String> actualParamMaps = new HashMap<>();
            if (paramMaps != null && paramMaps.size() > 0) {

                for (Entry<String, String> entry : paramMaps.entrySet()) {
                    if (entry.getValue() == null) {
                        actualParamMaps.put(entry.getKey(), "");
                    } else {
                        String actualValue = "";
                        if (StringUtils.isBlank(scope) || "REQUEST".equalsIgnoreCase(scope)) {
                            actualValue = (String) request.getAttribute(entry.getValue());
                        } else if ("SESSION".equalsIgnoreCase(scope)) {
                            actualValue = (String) request.getSession().getAttribute(entry.getValue());
                        }
                        if (actualValue == null) {
                            actualValue = "";
                        }
                        actualParamMaps.put(entry.getKey(), actualValue);
                    }
                }
            }
            String mText = LocalizationCache.getInstance().getValueByKey(langCode, text, actualParamMaps);
            if (mText != null) {
                if (this.escapeHtml) {
                    mText = StringEscapeUtils.escapeHtml4(mText);
                }
                if (this.escapeJavaScript) {
                    mText = StringEscapeUtils.escapeEcmaScript(mText);
                }
                if (this.escapeXml) {
                    mText = StringEscapeUtils.escapeXml(mText);
                }
                if (this.escapeCsv) {
                    mText = StringEscapeUtils.escapeCsv(mText);
                }

            }
            out.print(mText);

        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}