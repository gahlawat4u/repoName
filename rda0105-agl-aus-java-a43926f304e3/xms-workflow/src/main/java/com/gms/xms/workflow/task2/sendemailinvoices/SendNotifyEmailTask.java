package com.gms.xms.workflow.task2.sendemailinvoices;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.persistence.service.admin.AdminEmailServiceImp;
import com.gms.xms.persistence.service.admin.IAdminEmailService;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.helper.LocalizationHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from SendNotifyEmailTask
 * <p>
 * Author @author HungNT Mar 29, 2016
 */
public class SendNotifyEmailTask implements Task2 {
    private static final Log log = LogFactory.getLog(SendNotifyEmailTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        List<Map<String, String>> sentList = context.get(Attributes.SENT_LIST);
        Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
        try {
            String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
            String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
            String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
            String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
            int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
            String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
            EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
            EmailTemplateVo emailTemplateVo = new EmailTemplateVo();
            emailTemplateVo = emailTemplateDao.getEmailTemplateByName(AppConstants.SENT_EMAIL_INVOICES_NOTIFY);
            String content = "To be removed from this mailing list, please contact a XMS administrator.<br/><br/><br/>				This e-mail is to confirm that e-mail invoices have been mailed to the<br/>				following recipients:<br/><br/><br/>				[Email Invoice List]";
            String subject = "E-mail Invoices Sent Confirmation";
            if (emailTemplateVo != null) {
                content = emailTemplateVo.getTemplateContent() != null ? emailTemplateVo.getTemplateContent() : content;
                subject = emailTemplateVo.getSubject() != null ? emailTemplateVo.getSubject() : subject;
            }

            String invoiceList = "";
            LocalizationHelper lang = LocalizationHelper.getInstance();
            if (sentList.size() > 0) {
                for (Map<String, String> sent : sentList) {
                    invoiceList += lang.getLocalization(addInfo, "Sent invoice") + " " + sent.get("file") + " " + lang.getLocalization(addInfo, "to") + " " + sent.get("email") + "<br/>";
                }
            }
            if (sentList.size() > 0) {
                Map<String, String> replaceMap = new HashMap<>();
                replaceMap.put("[Email Invoice List]", invoiceList);
                replaceMap.put("\r\n", "<br/>");
                content = AppUtils.replaceStringByMap(replaceMap, content);
                String defaultNotifyEmail = AppConstants.APP_SETTINGS.getDefaultNotifyEmail();
                IAdminEmailService adminEmailService = new AdminEmailServiceImp();
                List<String> emailList = adminEmailService.getAdminEmailsList("E-mail Invoice Confirm");
                if (emailList.size() == 0) {
                    emailList.add(defaultNotifyEmail);
                }
                for (String email : emailList) {
                    try {
                        AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, email, null, null, subject, content, null);
                    } catch (Exception e) {
                        log.error("Failed to send notification email to " + email);
                    }
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
