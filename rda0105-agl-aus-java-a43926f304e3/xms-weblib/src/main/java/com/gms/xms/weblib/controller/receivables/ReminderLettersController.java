package com.gms.xms.weblib.controller.receivables;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.filter.admin.receivables.reminderletter.ReminderLetterFilter;
import com.gms.xms.filter.invoicing.StatementInvoiceFilter;
import com.gms.xms.model.EmailTemplateModel;
import com.gms.xms.model.admin.SystemSettingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.persistence.dao.admin.receivables.reminderletter.ReminderLetterDao;
import com.gms.xms.txndb.model.admin.receivables.reminderletter.ReminderInvoiceDetailModel;
import com.gms.xms.txndb.model.admin.receivables.reminderletter.ReminderLetterModel;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import com.gms.xms.txndb.vo.SystemSettingVo;
import com.gms.xms.txndb.vo.admin.receivables.reminderletter.ReminderInvoiceDetailVo;
import com.gms.xms.txndb.vo.admin.receivables.reminderletter.ReminderLetterVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.invoice.vieweditinvoice.IViewEditInvoiceRender;
import com.gms.xms.workflow.render.invoice.vieweditinvoice.ViewEditInvoiceRenderImp;
import com.gms.xms.workflow.render.reminderletter.IReminderLettersRender;
import com.gms.xms.workflow.render.reminderletter.ReminderLettersRenderImp;
import com.gms.xms.workflow.render.statements.IStatementsRender;
import com.gms.xms.workflow.render.statements.StatementsRenderImp;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Posted from Mar 28, 2016 10:35:34 AM
 * <p>
 * Author dattrinh
 */
public class ReminderLettersController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    // Models.
    private SystemSettingModel reminderStart;
    private SystemSettingModel reminderEnd;
    private EmailTemplateModel emailLetter;
    private EmailTemplateModel printLetter;
    private List<ReminderLetterModel> emailInvoices;
    private List<ReminderLetterModel> printInvoices;
    private String testEmail;
    private List<String> emailList;
    private List<String> printList;
    private String emailInvoice;
    private String printInvoice;

    private String htmlExportString;

    public String show() {
        try {
            this.loadSettings();
            this.loadEmailLetter();
            this.loadPrintLetter();
            // Set empty list for email and print invoices.
            this.setEmailInvoices(new ArrayList<ReminderLetterModel>());
            this.setPrintInvoices(new ArrayList<ReminderLetterModel>());
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String saveSettings() {
        try {
            if (this.getReminderStart() == null || this.getReminderEnd() == null) {
                throw new CustomException("No settings to save.");
            }
            // Do validate.
            if (!isValidSettings(this.getReminderStart(), this.getReminderEnd())) {
                this.setErrorCode(ErrorCode.FIELD_ERROR);
                return "success";
            }
            // Convert to vo.
            SystemSettingVo startVo = ModelUtils.createVoFromModel(this.getReminderStart(), SystemSettingVo.class);
            SystemSettingVo endVo = ModelUtils.createVoFromModel(this.getReminderEnd(), SystemSettingVo.class);
            // Create transaction.
            SqlSessionClient sessionClient = new SqlSessionClient();
            SystemSettingDao settingDao = new SystemSettingDao(sessionClient);
            try {
                // Insert if new and update if exists.
                if (startVo.getSettingId() == null) {
                    settingDao.insert(this.getAddInfoMap(), startVo);
                } else {
                    settingDao.update(this.getAddInfoMap(), startVo);
                }
                if (endVo.getSettingId() == null) {
                    settingDao.insert(this.getAddInfoMap(), endVo);
                } else {
                    settingDao.update(this.getAddInfoMap(), endVo);
                }
                // Commit transaction.
                sessionClient.endTransaction();
            } catch (Exception e) {
                // Roll-back transaction.
                sessionClient.rollback();
                throw e;
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String saveEmailLetter() {
        try {
            if (this.getEmailLetter() == null) {
                throw new CustomException("No Reminder Email setting to save.");
            }
            // Do validate.
            if (!isValidEmailLetter(this.getEmailLetter())) {
                this.setErrorCode(ErrorCode.FIELD_ERROR);
                return "success";
            }
            // Convert to vo.
            EmailTemplateVo emailTemplateVo = ModelUtils.createVoFromModel(this.getEmailLetter(), EmailTemplateVo.class);
            EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
            emailTemplateDao.updateEmailTemplate(this.getAddInfoMap(), emailTemplateVo);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String savePrintLetter() {
        try {
            if (this.getPrintLetter() == null) {
                throw new CustomException("No Reminder Print Letter setting to save.");
            }
            // Do validate.
            if (!isValidPrintLetter(this.getPrintLetter())) {
                this.setErrorCode(ErrorCode.FIELD_ERROR);
                return "success";
            }
            // Convert to vo.
            EmailTemplateVo emailTemplateVo = ModelUtils.createVoFromModel(this.getPrintLetter(), EmailTemplateVo.class);
            EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
            emailTemplateDao.updateEmailTemplate(this.getAddInfoMap(), emailTemplateVo);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String generate() {
        try {
            ReminderLetterDao dao = new ReminderLetterDao();
            ReminderLetterFilter filter = new ReminderLetterFilter();
            filter.setFranchiseList(this.buildFranchiseCodeList("All"));
            // Get email invoices.
            List<ReminderLetterVo> emailLetterVos = dao.getEmailInvoices(filter);
            List<ReminderLetterModel> emailLetterModels = ModelUtils.createListModelFromVo(emailLetterVos, ReminderLetterModel.class);
            this.setEmailInvoices(emailLetterModels);
            // Get print invoices.
            List<ReminderLetterVo> printLetterVos = dao.getPrintInvoices(filter);
            List<ReminderLetterModel> printLetterModels = ModelUtils.createListModelFromVo(printLetterVos, ReminderLetterModel.class);
            this.setPrintInvoices(printLetterModels);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public void sendPreview() {
        try {
            // Validate email address.
            if (StringUtils.isBlank(this.getTestEmail()) || !EmailUtils.isValidEmail(this.getTestEmail())) {
                throw new CustomException("Please enter a valid email.");
            }
            // Load email template.
            EmailTemplateDao templateDao = new EmailTemplateDao();
            final EmailTemplateVo template = templateDao.getEmailTemplateByName("Reminder Email");
            // Load invoice information and send email in background.
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                public void run() {
                    //Only send email for selected invoice, not all invoice
                    String invoiceId = getEmailInvoice();
                    try {
                        sendEmailInvoice(template, Long.valueOf(invoiceId), getTestEmail());
                        log.info("Send email invoice: invoiceid=[" + invoiceId + "]");
                    } catch (Exception e) {
                        log.error("Cannot send email invoice: invoiceid=[" + invoiceId + "]", e);
                    }
                }
            });
            executor.shutdown();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    public void sendInvoice() {
        try {
            // Load email template.
            EmailTemplateDao templateDao = new EmailTemplateDao();
            EmailTemplateVo template = templateDao.getEmailTemplateByName("Reminder Email");
            // Load invoice information and send email.
            Long invoiceId = null;
            try {
                invoiceId = Long.valueOf(this.getEmailInvoice());
            } catch (Exception e) {
                throw new CustomException("Invalid invoice id.");
            }
            this.sendEmailInvoice(template, invoiceId, null);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    public void sendAllInvoices() {
        try {
            // Load email template.
            EmailTemplateDao templateDao = new EmailTemplateDao();
            final EmailTemplateVo template = templateDao.getEmailTemplateByName("Reminder Email");
            // Load invoice information and send email in background.
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (String invoiceId : getEmailList()) {
                        try {
                            sendEmailInvoice(template, Long.valueOf(invoiceId), null);
                            log.info("Send email invoice: invoiceid=[" + invoiceId + "]");
                        } catch (Exception e) {
                            log.error("Cannot send email invoice: invoiceid=[" + invoiceId + "]", e);
                        }
                    }
                }
            });
            thread.start();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    public String printInvoice() {
        try {
            Date current = Calendar.getInstance().getTime();
            String currentDateStr = DateUtils.convertDateToString(current, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            Long invoiceId = Long.parseLong(this.getPrintInvoice());
            ReminderLetterDao dao = new ReminderLetterDao();
            ReminderInvoiceDetailVo invoiceDetailVo = dao.getInvoiceDetailById(Long.valueOf(invoiceId));
            ReminderInvoiceDetailModel invoiceDetailModel = ModelUtils.createModelFromVo(invoiceDetailVo, ReminderInvoiceDetailModel.class);
            StatementInvoiceFilter filter = this.buildStatementFilter(invoiceDetailModel.getCustomerCode());
            IReminderLettersRender render = new ReminderLettersRenderImp(this.getAddInfoMap());
            String realPath = WebUtils.getContextPathURL(request);
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/print_letter_" + AppUtils.createMessageReference() + ".html";
            this.loadPrintLetter();
            String printLetter = this.getPrintLetter().getTemplateContent();
            HashMap<String, String> variableMap = new HashMap<String, String>();
            variableMap.put("[CUSTOMER NAME]", invoiceDetailModel.getCustomerName());
            variableMap.put("[INVOICE DATE]", invoiceDetailModel.getInvoiceDate());
            variableMap.put("[INVOICE TERMS]", invoiceDetailModel.getInvoiceTerms());
            variableMap.put("[DUE DATE]", invoiceDetailModel.getInvoiceDate());
            variableMap.put("[DAY FROM INVOICE DATE]", invoiceDetailModel.getInvoiceAge());
            variableMap.put("[INVOICE AMOUNT]", invoiceDetailModel.getTotalAmount());
            variableMap.put("[TOTAL PAID]", invoiceDetailModel.getTotalPaid());
            variableMap.put("[AMOUNT DUE]", invoiceDetailModel.getTotalPaid());
            variableMap.put("[INVOICE NUMBER]", invoiceDetailModel.getInvoiceCode());
            variableMap.put("\r\n", "<br/>");
            printLetter = AppUtils.replaceStringByMap(variableMap, printLetter);
            render.generateHtmlPrintInvoice(invoiceDetailModel.getCustomerCode(), filter, currentDateStr, realPath, printLetter, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public void printAllInvoices() {
        try {
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    protected void sendEmailInvoice(EmailTemplateVo emailTemplate, Long invoiceId, String alternateEmail) throws Exception {
        ReminderLetterDao dao = new ReminderLetterDao();
        ReminderInvoiceDetailVo invoiceDetailVo = dao.getInvoiceDetailById(Long.valueOf(invoiceId));
        ReminderInvoiceDetailModel invoiceDetailModel = ModelUtils.createModelFromVo(invoiceDetailVo, ReminderInvoiceDetailModel.class);
        // Get email subject.
        String subject = emailTemplate.getSubject();
        subject = this.getLocalizationText(emailTemplate.getSubject());
        // Get email body content.
        String content = emailTemplate.getTemplateContent();
        HashMap<String, String> variableMap = new HashMap<String, String>();
        variableMap.put("[CUSTOMER NAME]", invoiceDetailModel.getCustomerName());
        variableMap.put("[INVOICE DATE]", invoiceDetailModel.getInvoiceDate());
        variableMap.put("[INVOICE TERMS]", invoiceDetailModel.getInvoiceTerms());
        variableMap.put("[DUE DATE]", invoiceDetailModel.getInvoiceDate());
        variableMap.put("[DAY FROM INVOICE DATE]", invoiceDetailModel.getInvoiceAge());
        variableMap.put("[INVOICE AMOUNT]", invoiceDetailModel.getTotalAmount());
        variableMap.put("[TOTAL PAID]", invoiceDetailModel.getTotalPaid());
        variableMap.put("[AMOUNT DUE]", invoiceDetailModel.getTotalPaid());
        variableMap.put("\r\n", "<br/>");
        for (Entry<String, String> entry : variableMap.entrySet()) {
            content = content.replace(entry.getKey(), entry.getValue());
        }
        // Get email attachment if it has.
        List<String> attachments = new ArrayList<String>();
        if (emailTemplate.getInvoiceAttachment()) {
            String invoicePDF = this.createInvoicePDF(invoiceId);
            if (StringUtils.isBlank(invoicePDF)) {
                throw new CustomException("Cannot create invoice PDF file.");
            }
            attachments.add(invoicePDF);
        }
        if (emailTemplate.getStatementAttachment()) {
            String statementPDF = this.createStatementPDF(invoiceDetailModel.getCustomerCode());
            if (StringUtils.isBlank(statementPDF)) {
                throw new CustomException("Cannot create statement PDF file.");
            }
            attachments.add(statementPDF);
        }
        String[] attachFiles = new String[attachments.size()];
        attachFiles = attachments.toArray(attachFiles);
        // Get email settings
        String toEmail = StringUtils.isBlank(alternateEmail) ? invoiceDetailModel.getEmail() : alternateEmail;
        String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
        String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
        String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
        String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
        int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
        String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
        // Send email.
        AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, toEmail, null, null, subject, content, attachFiles);
    }

    protected String createInvoicePDF(Long invoiceId) throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfoMap());
        context.put(Attributes.INVOICE_ID, invoiceId);
        context.put(Attributes.WFP_NAME, "Wfl-ViewEditInvoiceSearchInvoiceDetail");
        IViewEditInvoiceRender render = new ViewEditInvoiceRenderImp(this.getAddInfoMap());
        render.generateHtmlViewPdfFile(context, false);
        InvoiceInfoVo invoiceInfoVo = context.get(Attributes.INVOICE_VO);
        if (invoiceInfoVo == null) {
            throw new CustomException("No invoice information.");
        }
        String invoiceCode = invoiceInfoVo.getInvoiceCode();
        String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + invoiceCode + ".pdf";
        AppUtils.createPDFFromHTMLWithFont(context.getString(Attributes.OUT_PUT_FILE_PATH), pdfFilePath, "arial", true);
        return pdfFilePath;
    }

    protected String createStatementPDF(String customerCode) throws Exception {
        Date current = Calendar.getInstance().getTime();
        String currentDateStr = DateUtils.convertDateToString(current, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/statements_" + currentDateStr + "_" + AppUtils.createMessageReference() + ".html";
        String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/statements_" + customerCode + ".pdf";
        IStatementsRender render = new StatementsRenderImp(this.getAddInfoMap());
        StatementInvoiceFilter filter = this.buildStatementFilter(customerCode);
        render.generateHtmlForPdfFile(customerCode, filter, currentDateStr, outputFilePath);
        AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
        return pdfFilePath;
    }

    protected StatementInvoiceFilter buildStatementFilter(String customerCode) throws Exception {
        StatementInvoiceFilter filter = new StatementInvoiceFilter();
        filter.setCustomerCode(customerCode);
        filter.setOrderType(0);
        filter.setOrderField("invoice_code");
        return filter;
    }

    protected boolean isValidSettings(SystemSettingModel start, SystemSettingModel end) throws Exception {
        if (StringUtils.isBlank(start.getSettingValue())) {
            this.addFieldError("reminderStart.settingValue", "Please choose start day.");
        } else {
            try {
                Integer startDay = Integer.valueOf(start.getSettingValue());
                if (startDay < 0) {
                    this.addFieldError("reminderStart.settingValue", "Invalid start day. It cannot be an negative value.");
                }
            } catch (Exception e) {
                this.addFieldError("reminderStart.settingValue", "Invalid start day. It must be an integer value.");
            }
        }
        if (StringUtils.isBlank(end.getSettingValue())) {
            this.addFieldError("reminderEnd.settingValue", "Please choose end day.");
        } else {
            try {
                Integer endDay = Integer.valueOf(end.getSettingValue());
                if (endDay < 0) {
                    this.addFieldError("reminderEnd.settingValue", "Invalid end day. It cannot be an negative value.");
                }
            } catch (Exception e) {
                this.addFieldError("reminderEnd.settingValue", "Invalid end day. It must be an integer value.");
            }
        }
        return !hasFieldErrors();
    }

    protected boolean isValidEmailLetter(EmailTemplateModel emailLetter) {
        if (StringUtils.isBlank(emailLetter.getTemplateId())) {
            this.addFieldError("emailLetter.templateId", "There isn't setting for Reminder Email.");
        } else {
            try {
                Integer templateId = Integer.valueOf(emailLetter.getTemplateId());
                if (templateId <= 0) {
                    this.addFieldError("emailLetter.templateId", "There isn't setting for Reminder Email.");
                }
            } catch (Exception e) {
                this.addFieldError("emailLetter.templateId", "There isn't setting for Reminder Email.");
            }
        }
        if (StringUtils.isBlank(emailLetter.getTemplateName())) {
            this.addFieldError("emailLetter.templateName", "There isn't setting for Reminder Email.");
        }
        if (StringUtils.isBlank(emailLetter.getSubject())) {
            this.addFieldError("emailLetter.subject", "E-mail subject cannot leave blank.");
        }
        if (StringUtils.isBlank(emailLetter.getTemplateContent())) {
            this.addFieldError("emailLetter.templateContent", "E-mail body cannot leave blank.");
        }
        return !hasFieldErrors();
    }

    protected boolean isValidPrintLetter(EmailTemplateModel printLetter) {
        if (StringUtils.isBlank(printLetter.getTemplateId())) {
            this.addFieldError("printLetter.templateId", "There isn't setting for Reminder Print Letter.");
        } else {
            try {
                Integer templateId = Integer.valueOf(printLetter.getTemplateId());
                if (templateId <= 0) {
                    this.addFieldError("printLetter.templateId", "There isn't setting for Reminder Print Letter.");
                }
            } catch (Exception e) {
                this.addFieldError("printLetter.templateId", "There isn't setting for Reminder Print Letter.");
            }
        }
        if (StringUtils.isBlank(printLetter.getTemplateName())) {
            this.addFieldError("printLetter.templateName", "There isn't setting for Reminder Print Letter.");
        }
        if (StringUtils.isBlank(printLetter.getTemplateContent())) {
            this.addFieldError("printLetter.templateContent", "E-mail body cannot leave blank.");
        }
        return !hasFieldErrors();
    }

    protected void loadSettings() throws Exception {
        this.determineAdminLevel();
        SystemSettingDao settingDao = new SystemSettingDao();
        SystemSettingVo reminderStartVo = settingDao.getSystemSettingByName("ReminderStart");
        if (reminderStartVo == null) {
            reminderStartVo = new SystemSettingVo();
            reminderStartVo.setSettingName("ReminderStart");
            reminderStartVo.setSettingValue("15");
            reminderStartVo.setDescription("Start Invoice Date to send Email");
            reminderStartVo.setUserLevel(this.getAdminLevel());
            reminderStartVo.setAllowNull(0);
        }
        SystemSettingModel reminderStartModel = ModelUtils.createModelFromVo(reminderStartVo, SystemSettingModel.class);
        this.setReminderStart(reminderStartModel);
        SystemSettingVo reminderEndVo = settingDao.getSystemSettingByName("ReminderEnd");
        if (reminderEndVo == null) {
            reminderEndVo = new SystemSettingVo();
            reminderEndVo.setSettingName("ReminderEnd");
            reminderEndVo.setSettingValue("300");
            reminderEndVo.setDescription("End Invoice Date to send Email");
            reminderEndVo.setUserLevel(this.getAdminLevel());
            reminderEndVo.setAllowNull(0);
        }
        SystemSettingModel reminderEndModel = ModelUtils.createModelFromVo(reminderEndVo, SystemSettingModel.class);
        this.setReminderEnd(reminderEndModel);
    }

    protected void loadEmailLetter() throws Exception {
        this.setEmailLetter(this.getTemplateByName("Reminder Email"));
    }

    protected void loadPrintLetter() throws Exception {
        this.setPrintLetter(this.getTemplateByName("Reminder Print Letter"));
    }

    protected EmailTemplateModel getTemplateByName(String templateName) throws Exception {
        EmailTemplateDao templateDao = new EmailTemplateDao();
        EmailTemplateVo emailTemplateVo = templateDao.getEmailTemplateByName(templateName);
        EmailTemplateModel emailTemplateModel = ModelUtils.createModelFromVo(emailTemplateVo, EmailTemplateModel.class);
        String variable = emailTemplateModel.getVariable();
        variable = variable.replace(",", "<br/>");
        emailTemplateModel.setVariable(variable);
        return emailTemplateModel;
    }

    public SystemSettingModel getReminderStart() {
        return reminderStart;
    }

    public void setReminderStart(SystemSettingModel reminderStart) {
        this.reminderStart = reminderStart;
    }

    public SystemSettingModel getReminderEnd() {
        return reminderEnd;
    }

    public void setReminderEnd(SystemSettingModel reminderEnd) {
        this.reminderEnd = reminderEnd;
    }

    public EmailTemplateModel getEmailLetter() {
        return emailLetter;
    }

    public void setEmailLetter(EmailTemplateModel emailLetter) {
        this.emailLetter = emailLetter;
    }

    public EmailTemplateModel getPrintLetter() {
        return printLetter;
    }

    public void setPrintLetter(EmailTemplateModel printLetter) {
        this.printLetter = printLetter;
    }

    public List<ReminderLetterModel> getEmailInvoices() {
        return emailInvoices;
    }

    public void setEmailInvoices(List<ReminderLetterModel> emailInvoices) {
        this.emailInvoices = emailInvoices;
    }

    public List<ReminderLetterModel> getPrintInvoices() {
        return printInvoices;
    }

    public void setPrintInvoices(List<ReminderLetterModel> printInvoices) {
        this.printInvoices = printInvoices;
    }

    public String getTestEmail() {
        return testEmail;
    }

    public void setTestEmail(String testEmail) {
        this.testEmail = testEmail;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    public List<String> getPrintList() {
        return printList;
    }

    public void setPrintList(List<String> printList) {
        this.printList = printList;
    }

    public String getEmailInvoice() {
        return emailInvoice;
    }

    public void setEmailInvoice(String emailInvoice) {
        this.emailInvoice = emailInvoice;
    }

    public String getPrintInvoice() {
        return printInvoice;
    }

    public void setPrintInvoice(String printInvoice) {
        this.printInvoice = printInvoice;
    }

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
    }
}
