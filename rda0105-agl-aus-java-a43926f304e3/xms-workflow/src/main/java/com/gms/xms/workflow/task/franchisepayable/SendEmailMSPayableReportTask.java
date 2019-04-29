package com.gms.xms.workflow.task.franchisepayable;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayablePeriodDao;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayablePeriodVo;
import com.gms.xms.workflow.client.FranchisePayableMSClient;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendEmailMSPayableReportTask implements Task {
    private static final Log log = LogFactory.getLog(SendEmailMSPayableReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayablePeriodDao fpbPeriodDao = new FranchisePayablePeriodDao();
        FranchiseDao franchiseDao = new FranchiseDao();
        EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());

        // Get the filter from the context.
        FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

        try {
            // Get email settings
            String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
            String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
            String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
            String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
            int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
            String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
            EmailTemplateVo emailTemplateVo = emailTemplateDao.getEmailTemplateByName(AppConstants.SEND_FRANCHISE_PAYABLE_EMAIL);
            String toEmail = "";
            String parentFranchiseEmail = "";
            String subject = "";
            for (String franchiseCode : filter.getFranchiseCodeList()) {
                try {
                    toEmail = "";
                    parentFranchiseEmail = "";
                    // Get email of franchise and it's parent
                    FranchiseInfoVo franchiseVo = franchiseDao.getFranchiseInfoByCode(franchiseCode);
                    FranchiseInfoVo parentFranchiseVo = franchiseDao.getParentFranchiseByCode(franchiseCode);

                    // Prepare data for PDF export
                    // 1. Prepare filter to get data
                    FranchisePayableFilter filterForEachFranchise = new FranchisePayableFilter();
                    filterForEachFranchise.setStartDate(filter.getStartDate());
                    filterForEachFranchise.setEndDate(filter.getEndDate());
                    filterForEachFranchise.setRptTxnId(filter.getRptTxnId());
                    filterForEachFranchise.setStartRecord(null);
                    filterForEachFranchise.setRecordSize(null);
                    List<String> franchiseCodeList = new ArrayList<String>();
                    franchiseCodeList.add(franchiseCode);
                    filterForEachFranchise.setFranchiseCodeList(franchiseCodeList);

                    // 2. Get data for report and export data to PDF
                    String htmlFilePath = context.get(Attributes.TMP_PATH) + "/Franchise_Payable_MS_" + franchiseCode + ".html";
                    String pdfFilePath = context.get(Attributes.TMP_PATH) + "/Franchise_Payable_MS_" + franchiseCode + ".pdf";

                    String fpbInvHtmlFilePath = context.get(Attributes.TMP_PATH) + "/Franchise_Payable_Inv_" + franchiseCode + ".html";
                    String fpbInvPdfFilePath = context.get(Attributes.TMP_PATH) + "/Franchise_Payable_Inv_" + franchiseCode + ".pdf";

                    FranchisePayableMSClient client = new FranchisePayableMSClient(addInfo);
                    client.renderPayableHtmlReportForPdf(filterForEachFranchise, htmlFilePath, Boolean.parseBoolean(AppConstants.APP_SETTINGS.getEnableNonCentralizedTab()));
                    AppUtils.createPDFFromHTMLWithFont(htmlFilePath, pdfFilePath, "arial", true);

                    client.renderPayableInvoiceHtmlReport(filterForEachFranchise, franchiseCode, fpbInvHtmlFilePath, Boolean.parseBoolean(AppConstants.APP_SETTINGS.getEnableNonCentralizedTab()));
                    AppUtils.createPDFFromHTMLWithFont(fpbInvHtmlFilePath, fpbInvPdfFilePath, "arial", true);

                    // 3. Build content of email flow the email template file
                    String period = DateUtils.convertDateToString(filter.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
                    period += "," + DateUtils.convertDateToString(filter.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);

                    subject = emailTemplateVo != null ? emailTemplateVo.getSubject() : "Franchise Payable Report # [Period]";
                    Map<String, String> replaceMap = new HashMap<String, String>();
                    replaceMap.put("[Period]", period);
                    subject = AppUtils.replaceStringByMap(replaceMap, subject);
                    replaceMap.clear();

                    String content = emailTemplateVo != null ? emailTemplateVo.getTemplateContent() : "This email was sent from XMS 2.0 system. Attacted to it is a PDF copy of Franchise Payable Report for activity during [Period] for [Franchise Name]";
                    replaceMap.put("[Franchise Name]", franchiseVo.getName());
                    replaceMap.put("[Period]", period);
                    replaceMap.put("\r\n", "<br>");
                    content = AppUtils.replaceStringByMap(replaceMap, content);

                    if (parentFranchiseVo != null) {
                        parentFranchiseEmail = parentFranchiseVo.getEmail();
                    }

                    if (franchiseVo != null) {
                        toEmail = franchiseVo.getEmail();
                    }
                    // logging send email
                    StringBuilder logInfo = new StringBuilder();
                    logInfo.append("SEND FRANCHISE PAYABLE REPORT:\n");
                    logInfo.append("SUBJECT: " + subject + "\n");
                    logInfo.append("FROM: " + fromEmail + "\n");
                    logInfo.append("TO: " + franchiseVo.getEmail() + "\n");
                    if (parentFranchiseVo != null) {
                        logInfo.append("CC: " + parentFranchiseEmail + "\n");
                    }
                    logInfo.append("CONTENT: \n" + content + "\n");
                    logInfo.append("ATTACHED FILE: " + pdfFilePath + "\n\n");
                    log.info(logInfo.toString());
                    // 4. Send PDF file to franchise and it's parent email
                    AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, toEmail, parentFranchiseEmail, null, subject, content, new String[]{pdfFilePath, fpbInvPdfFilePath});
                } catch (Exception e) {
                    log.error("Fail to send franchise payable report.| FROM EMAIL: " + fromEmail + "| TO: " + toEmail + "| CC: " + parentFranchiseEmail + "| SUBJECT: " + subject, e);
                }
            }

            // Get current info of franchise payable report
            FranchisePayablePeriodVo fpbPeriodVo = fpbPeriodDao.getFranchisePayablePeriodByDateRange(filter);
            fpbPeriodVo.setFranchisePayableStatus(2);

            // Do DAO service to update status of franchise payable report to
            // SEND_EMAIL status
            fpbPeriodDao.updateFranchisePayablePeriod(addInfo, fpbPeriodVo);
        } catch (Exception ex) {
            log.error(ex);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }
}
