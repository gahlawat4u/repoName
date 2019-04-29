package com.gms.xms.workflow.task.webship;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import com.gms.xms.workflow.client.integration.request.HistoryRequest;
import com.gms.xms.workflow.client.integration.response.HistoryResponse;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from SendAirbillHistoryTask
 * <p>
 * Author TanDT Date May 4, 2015
 */
public class SendAirbillHistoryTask implements Task {
    private static final Log log = LogFactory.getLog(SendAirbillHistoryTask.class);

    public boolean execute(ContextBase context) throws Exception {
        EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        HistoryRequest request = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESQUEST), HistoryRequest.class);
        HistoryResponse response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        try {
            String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
            String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
            String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
            String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
            int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
            String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
            EmailTemplateVo emailTemplateVo = emailTemplateDao.getEmailTemplateByName(request.getSendAirlbillHistoryFillterModel().getTemplateEmail());
            String subject = emailTemplateVo.getSubject();
            String content = emailTemplateVo.getTemplateContent();
            String[] filepath = request.getSendAirlbillHistoryFillterModel().getFilepath();
            try {
                AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, request.getSendAirlbillHistoryFillterModel().getEmails(), "", "", subject, content, filepath);
                response.setStatusSendAirbill(1);
            } catch (Exception e) {
                response.setStatusSendAirbill(0);
            }
            context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
