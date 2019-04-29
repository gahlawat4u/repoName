package com.gms.xms.weblib.controller.admin.emailtemplate;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.model.EmailTemplateModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from Mar 30, 2016 9:05:50 AM
 * <p>
 * Author dattrinh
 */
public class EmailTemplateController extends AdminJsonBaseController {

    private static final long serialVersionUID = -7945740360436049333L;

    private List<EmailTemplateModel> emailTemplateList;
    private EmailTemplateModel emailTemplate;
    private String templateId;

    public String show() {
        try {
            // Load email template list.
            this.loadEmailTemplates();
            // Load first email template.
            this.setTemplateId(this.getFirstEmailTemplateId());
            if (this.getTemplateId() != null) {
                this.loadEmailTemplateById(this.getTemplateId());
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String load() {
        try {
            // Load email template list.
            this.loadEmailTemplates();
            // Valid template id.
            if (StringUtils.isBlank(this.getTemplateId())) {
                throw new CustomException("No email template id to load.");
            } else {
                try {
                    Integer templateId = Integer.valueOf(this.getTemplateId());
                    if (templateId <= 0) {
                        throw new CustomException("Invalid template id.");
                    }
                } catch (Exception e) {
                    throw new CustomException("Invalid template id.");
                }
            }
            // Load the email template.
            this.loadEmailTemplateById(this.getTemplateId());
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String save() {
        try {
            // Load email templates for drop down list.
            this.loadEmailTemplates();
            // Check null.
            if (this.getEmailTemplate() == null) {
                throw new CustomException("No email template to update.");
            }
            // Validate email template.
            if (!isValidEmailTemplate(this.getEmailTemplate())) {
                this.setErrorCode(ErrorCode.FIELD_ERROR);
                return "success";
            }
            // Convert to vo to update.
            EmailTemplateVo emailTemplateVo = ModelUtils.createVoFromModel(this.getEmailTemplate(), EmailTemplateVo.class);
            EmailTemplateDao dao = new EmailTemplateDao();
            dao.updateEmailTemplate(this.getAddInfoMap(), emailTemplateVo);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected boolean isValidEmailTemplate(EmailTemplateModel emailTemplate) {
        if (StringUtils.isBlank(emailTemplate.getTemplateId())) {
            this.addFieldError("emailTemplate.templateId", "The email template id is required.");
        } else {
            try {
                Integer templateId = Integer.valueOf(emailTemplate.getTemplateId());
                if (templateId <= 0) {
                    this.addFieldError("emailTemplate.templateId", "Invalid template id.");
                }
            } catch (Exception e) {
                this.addFieldError("emailTemplate.templateId", "Invalid template id.");
            }
        }
        if (StringUtils.isBlank(emailTemplate.getSubject())) {
            this.addFieldError("emailTemplate.subject", "The subject is required.");
        }
        if (StringUtils.isBlank(emailTemplate.getTemplateContent())) {
            this.addFieldError("emailTemplate.templateContent", "The content is required.");
        }
        return !this.hasFieldErrors();
    }

    protected void loadEmailTemplates() throws Exception {
        EmailTemplateDao dao = new EmailTemplateDao();
        List<EmailTemplateVo> emailTemplateVos = dao.getNormalEmailTemplateList();
        List<EmailTemplateModel> emailTemplateModels = ModelUtils.createListModelFromVo(emailTemplateVos, EmailTemplateModel.class);
        this.setEmailTemplateList(emailTemplateModels);
    }

    protected String getFirstEmailTemplateId() {
        if (this.getEmailTemplateList() != null && this.getEmailTemplateList().size() > 0) {
            return this.getEmailTemplateList().get(0).getTemplateId();
        }
        return null;
    }

    protected void loadEmailTemplateById(String templateId) throws Exception {
        Integer emailTemplateId = Integer.valueOf(templateId);
        EmailTemplateDao dao = new EmailTemplateDao();
        EmailTemplateVo emailTemplateVo = dao.getEmailTemplateById(emailTemplateId);
        EmailTemplateModel emailTemplateModel = ModelUtils.createModelFromVo(emailTemplateVo, EmailTemplateModel.class);
        String variable = emailTemplateModel.getVariable();
        variable = variable.replace(",", "<br/>");
        emailTemplateModel.setVariable(variable);
        this.setEmailTemplate(emailTemplateModel);
    }

    public List<EmailTemplateModel> getEmailTemplateList() {
        return emailTemplateList;
    }

    public void setEmailTemplateList(List<EmailTemplateModel> emailTemplateList) {
        this.emailTemplateList = emailTemplateList;
    }

    public EmailTemplateModel getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(EmailTemplateModel emailTemplate) {
        this.emailTemplate = emailTemplate;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
