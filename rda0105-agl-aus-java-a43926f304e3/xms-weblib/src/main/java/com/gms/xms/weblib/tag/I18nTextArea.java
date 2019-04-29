package com.gms.xms.weblib.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.UIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@StrutsTag(name = "i18n_textarea", tldTagClass = "com.gms.xms.weblib.tag.LocalizationTextareaTag", description = "Render HTML textarea tag.", allowDynamicAttributes = true)
public class I18nTextArea extends UIBean {
    public static final String TEMPLATE = "i18n_textarea";
    protected String cols;
    protected String readonly;
    protected String rows;
    protected String wrap;

    public I18nTextArea(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return "i18n_textarea";
    }

    public void evaluateExtraParams() {
        super.evaluateExtraParams();

        if (this.readonly != null) {
            addParameter("readonly", findValue(this.readonly, Boolean.class));
        }

        if (this.cols != null) {
            addParameter("cols", findString(this.cols));
        }

        if (this.rows != null) {
            addParameter("rows", findString(this.rows));
        }

        if (this.wrap != null)
            addParameter("wrap", findString(this.wrap));
    }

    @StrutsTagAttribute(description = "HTML cols attribute", type = "Integer")
    public void setCols(String cols) {
        this.cols = cols;
    }

    @StrutsTagAttribute(description = "Whether the textarea is readonly", type = "Boolean", defaultValue = "false")
    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    @StrutsTagAttribute(description = "HTML rows attribute", type = "Integer")
    public void setRows(String rows) {
        this.rows = rows;
    }

    @StrutsTagAttribute(description = "HTML wrap attribute")
    public void setWrap(String wrap) {
        this.wrap = wrap;
    }
}