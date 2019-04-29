package com.gms.xms.weblib.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.ListUIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@StrutsTag(name = "i18n_select", tldTagClass = "com.gms.xms.weblib.tag.LocalizationSelectTag", description = "Render a select element", allowDynamicAttributes = true)
public class I18nSelect extends ListUIBean {
    public static final String TEMPLATE = "i18n_select";
    protected String emptyOption;
    protected String headerKey;
    protected String headerValue;
    protected String multiple;
    protected String size;

    public I18nSelect(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    protected String getDefaultTemplate() {
        return "i18n_select";
    }

    public void evaluateExtraParams() {
        super.evaluateExtraParams();

        if (this.emptyOption != null) {
            addParameter("emptyOption", findValue(this.emptyOption, Boolean.class));
        }

        if (this.multiple != null) {
            addParameter("multiple", findValue(this.multiple, Boolean.class));
        }

        if (this.size != null) {
            addParameter("size", findString(this.size));
        }

        if ((this.headerKey != null) && (this.headerValue != null)) {
            addParameter("headerKey", findString(this.headerKey));
            addParameter("headerValue", findString(this.headerValue));
        }
    }

    @StrutsTagAttribute(description = "Whether or not to add an empty (--) option after the header option", type = "Boolean", defaultValue = "false")
    public void setEmptyOption(String emptyOption) {
        this.emptyOption = emptyOption;
    }

    @StrutsTagAttribute(description = " Key for first item in list. Must not be empty! '-1' and '' is correct, '' is bad.")
    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    @StrutsTagAttribute(description = "Value expression for first item in list")
    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    @StrutsTagAttribute(description = " Creates a multiple select. The tag will pre-select multiple values if the values are passed as an Array or a Collection(of appropriate types) via the value attribute. If one of the keys equals one of the values in the Collection or Array it wil be selected", type = "Boolean", defaultValue = "false")
    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    @StrutsTagAttribute(description = "Size of the element box (# of elements to show)", type = "Integer")
    public void setSize(String size) {
        this.size = size;
    }
}