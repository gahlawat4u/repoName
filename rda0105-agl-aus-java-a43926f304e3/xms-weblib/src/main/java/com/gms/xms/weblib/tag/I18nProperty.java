package com.gms.xms.weblib.tag;

import com.gms.xms.cache.LocalizationCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@StrutsTag(name = "i18n_property", tldBodyContent = "empty", tldTagClass = "com.gms.xms.weblib.tag.LocalizationPropertyTag", description = "Print out expression which evaluates against the stack")
public class I18nProperty extends Component {
    private static final Logger LOG = LoggerFactory.getLogger(I18nProperty.class);
    private String defaultValue;
    private String value;
    private boolean escapeHtml = true;
    private boolean escapeJavaScript = false;
    private boolean escapeXml = false;
    private boolean escapeCsv = false;
    private String paramMap;
    private String scope;
    private HttpServletRequest req;
    //private HttpServletResponse res;

    public I18nProperty(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        super(stack);
        this.req = req;
        //this.res = res;
    }

    @StrutsTagAttribute(description = "The default value to be used if <u>value</u> attribute is null")
    public void setDefault(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @StrutsTagAttribute(description = "Deprecated. Use 'escapeHtml'. Whether to escape HTML", type = "Boolean", defaultValue = "true")
    public void setEscape(boolean escape) {
        this.escapeHtml = escape;
    }

    @StrutsTagAttribute(description = "Whether to escape HTML", type = "Boolean", defaultValue = "true")
    public void setEscapeHtml(boolean escape) {
        this.escapeHtml = escape;
    }

    @StrutsTagAttribute(description = "Whether to escape Javascript", type = "Boolean", defaultValue = "false")
    public void setEscapeJavaScript(boolean escapeJavaScript) {
        this.escapeJavaScript = escapeJavaScript;
    }

    @StrutsTagAttribute(description = "Value to be displayed", type = "Object", defaultValue = "&lt;top of stack&gt;")
    public void setValue(String value) {
        this.value = value;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @StrutsTagAttribute(description = "Whether to escape CSV (useful to escape a value for a column)", type = "Boolean", defaultValue = "false")
    public void setEscapeCsv(boolean escapeCsv) {
        this.escapeCsv = escapeCsv;
    }

    @StrutsTagAttribute(description = "Whether to escape XML", type = "Boolean", defaultValue = "false")
    public void setEscapeXml(boolean escapeXml) {
        this.escapeXml = escapeXml;
    }

    @StrutsTagAttribute(description = "Map Name", type = "String", defaultValue = "false")
    public void setParamMap(String paramMap) {
        this.paramMap = paramMap;
    }

    @StrutsTagAttribute(description = "Scope", type = "String", defaultValue = "false")
    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean start(Writer writer) {
        boolean result = super.start(writer);

        String actualValue = null;

        if (this.value == null) {
            this.value = "top";
        } else {
            this.value = stripExpressionIfAltSyntax(this.value);
        }
        actualValue = (String) getStack().findValue(this.value, String.class, this.throwExceptionOnELFailure);
        try {
            if (actualValue != null)
                writer.write(prepare(actualValue));
            else if (this.defaultValue != null)
                writer.write(prepare(this.defaultValue));
        } catch (IOException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Could not print out value '" + this.value + "'", e, new String[0]);
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private String prepare(String value) {
        String result = value;

        String langCode = (String) req.getSession().getAttribute(Attributes.XMS_LANG);
        if (StringUtils.isBlank(langCode)) {
            langCode = AppConstants.APP_SETTINGS.getDefaultLanguageCode();
        }
        Map<String, String> paramMaps = null;
        if (StringUtils.isNotBlank(paramMap)) {
            if (StringUtils.isBlank(scope) || "REQUEST".equalsIgnoreCase(scope)) {
                if (req.getAttribute("paramMap") != null) {
                    paramMaps = (Map<String, String>) req.getAttribute("paramMap");
                }
            } else if ("SESSION".equalsIgnoreCase(scope)) {
                if (req.getSession().getAttribute("paramMap") != null) {
                    paramMaps = (Map<String, String>) req.getSession().getAttribute("paramMap");
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
                    if (StringUtils.isBlank(scope) || "req".equalsIgnoreCase(scope)) {
                        actualValue = (String) req.getAttribute(entry.getValue());
                    } else if ("SESSION".equalsIgnoreCase(scope)) {
                        actualValue = (String) req.getSession().getAttribute(entry.getValue());
                    }
                    if (actualValue == null) {
                        actualValue = "";
                    }
                    actualParamMaps.put(entry.getKey(), actualValue);
                }
            }
        }
        result = LocalizationCache.getInstance().getValueByKey(langCode, value, actualParamMaps);

        if (this.escapeHtml) {
            result = StringEscapeUtils.escapeHtml4(result);
        }
        if (this.escapeJavaScript) {
            result = StringEscapeUtils.escapeEcmaScript(result);
        }
        if (this.escapeXml) {
            result = StringEscapeUtils.escapeXml(result);
        }
        if (this.escapeCsv) {
            result = StringEscapeUtils.escapeCsv(result);
        }

        return result;
    }
}