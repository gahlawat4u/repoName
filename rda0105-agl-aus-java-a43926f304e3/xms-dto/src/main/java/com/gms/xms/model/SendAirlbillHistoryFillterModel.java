package com.gms.xms.model;

import java.util.Arrays;

/**
 * Posted from SendAirlbillHistoryFillterModel
 * <p>
 * Author TanDT Date May 4, 2015
 */
public class SendAirlbillHistoryFillterModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String emails;
    private String templateEmail;
    private String[] filepath;

    public String[] getFilepath() {
        return filepath;
    }

    public void setFilepath(String[] filepath) {
        this.filepath = filepath;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getTemplateEmail() {
        return templateEmail;
    }

    public void setTemplateEmail(String templateEmail) {
        this.templateEmail = templateEmail;
    }

    @Override
    public String toString() {
        return "SendAirlbillHistoryFillterModel [emails=" + emails + ", templateEmail=" + templateEmail + ", filepath=" + Arrays.toString(filepath) + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emails == null) ? 0 : emails.hashCode());
        result = prime * result + Arrays.hashCode(filepath);
        result = prime * result + ((templateEmail == null) ? 0 : templateEmail.hashCode());
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
        SendAirlbillHistoryFillterModel other = (SendAirlbillHistoryFillterModel) obj;
        if (emails == null) {
            if (other.emails != null)
                return false;
        } else if (!emails.equals(other.emails))
            return false;
        if (!Arrays.equals(filepath, other.filepath))
            return false;
        if (templateEmail == null) {
            if (other.templateEmail != null)
                return false;
        } else if (!templateEmail.equals(other.templateEmail))
            return false;
        return true;
    }

}