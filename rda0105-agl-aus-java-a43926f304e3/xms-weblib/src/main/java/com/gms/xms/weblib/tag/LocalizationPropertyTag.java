package com.gms.xms.weblib.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocalizationPropertyTag extends ComponentTagSupport {
    private static final long serialVersionUID = -700801856911687320L;
    private String defaultValue;
    private String value;
    private boolean escapeHtml;
    private boolean escapeJavaScript;
    private boolean escapeXml;
    private boolean escapeCsv;
    private String paramMap;
    private String scope;

    public LocalizationPropertyTag() {
        this.escapeHtml = true;
        this.escapeJavaScript = false;
        this.escapeXml = false;
        this.escapeCsv = false;
    }

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new I18nProperty(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        I18nProperty tag = (I18nProperty) this.component;
        tag.setDefault(this.defaultValue);
        tag.setValue(this.value);
        tag.setEscape(this.escapeHtml);
        tag.setEscapeJavaScript(this.escapeJavaScript);
        tag.setEscapeXml(this.escapeXml);
        tag.setEscapeCsv(this.escapeCsv);
        tag.setParamMap(this.paramMap);
        tag.setScope(this.scope);
    }

    public void setDefault(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setEscapeHtml(boolean escapeHtml) {
        this.escapeHtml = escapeHtml;
    }

    public void setEscapeJavaScript(boolean escapeJavaScript) {
        this.escapeJavaScript = escapeJavaScript;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setEscapeCsv(boolean escapeCsv) {
        this.escapeCsv = escapeCsv;
    }

    public void setEscapeXml(boolean escapeXml) {
        this.escapeXml = escapeXml;
    }

    public void setParamMap(String paramMap) {
        this.paramMap = paramMap;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}