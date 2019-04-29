package com.gms.xms.model;

/**
 * Posted from EmailTemplateModel
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class EmailTemplateModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String templateId;

    private String templateName;

    private String templateContent;

    private String subject;

    private String variable;

    private String type;

    private String invoiceAttachment;

    private String statementAttachment;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInvoiceAttachment() {
        return invoiceAttachment;
    }

    public void setInvoiceAttachment(String invoiceAttachment) {
        this.invoiceAttachment = invoiceAttachment;
    }

    public String getStatementAttachment() {
        return statementAttachment;
    }

    public void setStatementAttachment(String statementAttachment) {
        this.statementAttachment = statementAttachment;
    }

    @Override
    public String toString() {
        return "EmailTemplateModel [templateId=" + templateId + ", templateName=" + templateName + ", templateContent=" + templateContent + ", subject=" + subject + ", variable=" + variable + ", type=" + type + ", invoiceAttachment=" + invoiceAttachment + ", statementAttachment=" + statementAttachment + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((invoiceAttachment == null) ? 0 : invoiceAttachment.hashCode());
        result = prime * result + ((statementAttachment == null) ? 0 : statementAttachment.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ((templateContent == null) ? 0 : templateContent.hashCode());
        result = prime * result + ((templateId == null) ? 0 : templateId.hashCode());
        result = prime * result + ((templateName == null) ? 0 : templateName.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((variable == null) ? 0 : variable.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmailTemplateModel other = (EmailTemplateModel) obj;
        if (invoiceAttachment == null) {
            if (other.invoiceAttachment != null)
                return false;
        } else if (!invoiceAttachment.equals(other.invoiceAttachment))
            return false;
        if (statementAttachment == null) {
            if (other.statementAttachment != null)
                return false;
        } else if (!statementAttachment.equals(other.statementAttachment))
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        if (templateContent == null) {
            if (other.templateContent != null)
                return false;
        } else if (!templateContent.equals(other.templateContent))
            return false;
        if (templateId == null) {
            if (other.templateId != null)
                return false;
        } else if (!templateId.equals(other.templateId))
            return false;
        if (templateName == null) {
            if (other.templateName != null)
                return false;
        } else if (!templateName.equals(other.templateName))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (variable == null) {
            if (other.variable != null)
                return false;
        } else if (!variable.equals(other.variable))
            return false;
        return true;
    }


}
