package com.gms.xms.workflow.task2.downloadbilling.dhl;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.admin.imports.BillingResultModel;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from Jun 7, 2016 2:56:53 PM
 * <p>
 * Author huynd
 */
public class SendEDIDownloadMailTask implements Task2 {
    private static final Log log = LogFactory.getLog(SendEDIDownloadMailTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
            // Config email
            String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
            int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
            String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
            String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
            String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
            String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
            String toEmail = getAdminEmailListByName("EDI Import Notify");

            // Prepare results
            List<BillingResultModel> results = context.get(Attributes.BILLING_RESULTS);
            for (BillingResultModel result : results) {
                String serviceName = context.get(Attributes.SERVICE_NAME);
                EmailTemplateVo emailTemplateVo = emailTemplateDao.getEmailTemplateByName("EDI Import Notify");
                String subject = emailTemplateVo.getSubject();
                Map<String, String> replaceMap = new HashMap<String, String>();
                replaceMap.put("[Carrier Name]", serviceName);
                replaceMap.put("[EDI File Name]", result.getFileName());
                replaceMap.put("[Record Count]", result.getNoLines());
                replaceMap.put("[Airbill Count]", result.getNoAirbill());
                String content = emailTemplateVo.getTemplateContent();
                subject = AppUtils.replaceStringByMap(replaceMap, subject);
                content = AppUtils.replaceStringByMap(replaceMap, content);
                String[] filepath = {};
                // Send email
                AppUtils.sendEDIDownloadMail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, toEmail, "", "", subject, content, filepath);
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

    private String getAdminEmailListByName(String name) throws DaoException {
        EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
        List<String> listEmails = emailTemplateDao.selectAdminEmaillistByName(name);
        String listEmailStr = "";
        for (String email : listEmails) {
            listEmailStr += email + ";";
        }
        listEmailStr = listEmailStr.substring(0, listEmailStr.length() - 1);
        return listEmailStr;
    }

}
