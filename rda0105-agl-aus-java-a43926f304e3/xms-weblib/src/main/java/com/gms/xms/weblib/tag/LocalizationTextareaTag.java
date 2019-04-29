package com.gms.xms.weblib.tag;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocalizationTextareaTag extends AbstractUITag {

    private static final long serialVersionUID = 4613322758422364879L;
    protected String lplaceholder;
    protected String cols;
    protected String readonly;
    protected String rows;
    protected String wrap;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new I18nTextArea(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        I18nTextArea textArea = (I18nTextArea) this.component;
        textArea.setCols(this.cols);
        textArea.setReadonly(this.readonly);
        textArea.setRows(this.rows);
        textArea.setWrap(this.wrap);

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        this.template = "i18n_textarea";
        TaglibHelper taglibHelper = new TaglibHelper(request);
        this.component.addParameter("taglibHelper", taglibHelper);
        this.component.addParameter("lplaceholder", this.lplaceholder);
    }

    public void setCols(String cols) {
        this.cols = cols;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public void setWrap(String wrap) {
        this.wrap = wrap;
    }

    public void setLplaceholder(String lplaceholder) {
        this.lplaceholder = lplaceholder;
    }
}