package com.gms.xms.weblib.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractRequiredListTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocalizationSelectTag extends AbstractRequiredListTag {

    private static final long serialVersionUID = -9037090957606507234L;
    protected String i18nitem = "yes";
    protected String emptyOption;
    protected String headerKey;
    protected String headerValue;
    protected String multiple;
    protected String size;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new I18nSelect(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        I18nSelect select = (I18nSelect) this.component;
        select.setEmptyOption(this.emptyOption);
        select.setHeaderKey(this.headerKey);
        select.setHeaderValue(this.headerValue);
        select.setMultiple(this.multiple);
        select.setSize(this.size);

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        this.template = "i18n_select";
        TaglibHelper taglibHelper = new TaglibHelper(request);
        this.component.addParameter("taglibHelper", taglibHelper);
        this.component.addParameter("i18nitem", this.i18nitem);

    }

    public void setI18nitem(String i18nitem) {
        this.i18nitem = i18nitem;
    }

    public void setEmptyOption(String emptyOption) {
        this.emptyOption = emptyOption;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public void setSize(String size) {
        this.size = size;
    }
}