package com.gms.xms.workflow.task.admin.invoicing;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.invoicing.CreditNoteInfoModel;
import com.gms.xms.model.invoicing.ManageCreditNoteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.CreditNoteDao;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.persistence.dao.SendCreditNoteBillingInfoDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.workflow.client.CreditNoteClient;
import com.gms.xms.workflow.client.SendCreditNotesClient;
import com.gms.xms.workflow.client.integration.request.CreditNoteRequest;
import com.gms.xms.workflow.client.integration.response.CreditNoteResponse;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from SendCreditNotesTask
 * <p>
 * Author HungNT Date May 21, 2015
 */
public class SendCreditNotesTask implements Task {
    private static final Log log = LogFactory.getLog(SendCreditNotesTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
        List<CreditNoteVo> creditNoteVos = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTE_LIST), new TypeToken<List<CreditNoteVo>>() {
        }.getType());
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            // Get email settings
            String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
            String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
            String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
            String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
            int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
            String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
            EmailTemplateVo emailTemplateVo = emailTemplateDao.getEmailTemplateByName(AppConstants.SEND_CREDIT_NOTES);
            String toEmail = "";
            String parentFranchiseEmail = "";
            String subject = "";

            CreditNoteClient creditNoteClient = new CreditNoteClient(addInfo);
            CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
            SendCreditNoteBillingInfoDao billingInfoDao = new SendCreditNoteBillingInfoDao();
            CreditNoteDao creditNoteDao = new CreditNoteDao();
            for (CreditNoteVo creditNoteVo : creditNoteVos) {
                try {

                    // 1. Prepare data for send credit notes

                    // Get creditnote info
                    ManageCreditNoteVo manageCreditNoteVo = new ManageCreditNoteVo();
                    creditNoteRequest.setCreditNoteCode(creditNoteVo.getCreditCode());
                    CreditNoteResponse creditNoteResponse = creditNoteClient.getCreditNotesDetail(creditNoteRequest);
                    CreditNoteInfoVo creditNoteInfoVo = creditNoteResponse.getCreditNoteInfo();
                    Double totalAmount = creditNoteInfoVo.getTotalCredited();

                    if (totalAmount == 0) {
                        continue;
                    }
                    manageCreditNoteVo.setCreditNoteInfo(creditNoteResponse.getCreditNoteInfo());
                    manageCreditNoteVo.setInfoSystemAdmin(creditNoteResponse.getInfoSystemAdminVo());
                    manageCreditNoteVo.setListCreditNoteAdj(creditNoteResponse.getListAdjustment());
                    manageCreditNoteVo.setGstSummary(creditNoteResponse.getCreditNotesGSTSummaryVos());
                    ManageCreditNoteModel manageCreditNoteModel = ModelUtils.createModelFromVo(manageCreditNoteVo, ManageCreditNoteModel.class);
                    CreditNoteInfoModel creditNoteInfoModel = manageCreditNoteModel.getCreditNoteInfo();
                    creditNoteInfoModel.setTotalAmount(String.valueOf(creditNoteInfoModel.getTotalAmount()));
                    manageCreditNoteModel.setCreditNoteInfo(creditNoteInfoModel);
                    // Get billing info
                    SendCreditNoteBillingInfoVo sendCreditNoteBillingInfoVo = billingInfoDao.selectSendCreditNoteBillingInfo(manageCreditNoteVo.getCreditNoteInfo().getCustomerCode());

                    // 2. Generate attachments
                    String htmlFilePath = context.get(Attributes.TMP_PATH) + "/credit_notes_" + creditNoteVo.getCreditCode() + ".html";
                    String pdfFilePath = context.get(Attributes.TMP_PATH) + "/credit_notes_" + creditNoteVo.getCreditCode() + ".pdf";
                    // filter.setCreditNoteId(creditNoteVo.getCreditNoteId());
                    SendCreditNotesClient client = new SendCreditNotesClient(addInfo);
                    client.renderCreditNotesHtmlFile(manageCreditNoteModel, htmlFilePath);
                    AppUtils.createPDFFromHTMLWithFont(htmlFilePath, pdfFilePath, "arial", true);

                    // 3. Build email contents according to email template.
                    String billEmail = sendCreditNoteBillingInfoVo.getBillingEmail();
                    // subject = emailTemplateVo != null ?
                    // emailTemplateVo.getSubject() : "Credit Notes # Testing";
                    subject = "Credit Notes sent to " + billEmail + " # " + creditNoteVo.getCreditCode();
                    String content = emailTemplateVo != null ? emailTemplateVo.getTemplateContent() : "Dear [Customer Name] Please do not respond to this email, it is an automated message sent from our billing system. Attached to this e-mail is a PDF copy of a credit note relating to invoice [Invoice Number]. Please feel free to call us at [Franchise Phone] if you have any questions. As always, thank you for your business. Sincerely, AGL";

                    Map<String, String> replaceMap = new HashMap<>();

                    replaceMap.put("[Customer Name]", creditNoteInfoModel.getBillingCustomerName());
                    replaceMap.put("[Franchise Phone]", sendCreditNoteBillingInfoVo.getFranchisePhone());
                    replaceMap.put("[Invoice Number]", creditNoteVo.getInvoiceCode());
                    replaceMap.put("\r\n", "<br>");
                    content = AppUtils.replaceStringByMap(replaceMap, content);

                    toEmail = billEmail;
                    StringBuilder logInfo = new StringBuilder();
                    logInfo.append("SEND CREDIT NOTES:\n");
                    logInfo.append("SUBJECT: " + subject + "\n");
                    logInfo.append("FROM: " + fromEmail + "\n");
                    logInfo.append("TO: " + billEmail + "\n");
                    logInfo.append("CONTENT: \n" + content + "\n");
                    logInfo.append("ATTACHED FILE: " + pdfFilePath + "\n\n");
                    log.info(logInfo.toString());

                    // 4. Send email
                    try {
                        AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, toEmail, parentFranchiseEmail, null, subject, content, new String[]{pdfFilePath});
                        creditNoteVo.setStatus(2);
                        creditNoteDao.updateCreditNote(addInfo, creditNoteVo);
                    } catch (Exception e) {
                        log.error(e);
                    }
                } catch (Exception e) {
                    log.error(e);
                }
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
