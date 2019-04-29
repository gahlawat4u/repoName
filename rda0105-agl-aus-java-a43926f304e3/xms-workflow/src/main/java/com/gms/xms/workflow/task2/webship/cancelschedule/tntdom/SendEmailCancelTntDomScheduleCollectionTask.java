package com.gms.xms.workflow.task2.webship.cancelschedule.tntdom;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;


public class SendEmailCancelTntDomScheduleCollectionTask implements Task2 {
    private static final Log log = LogFactory.getLog(SendEmailCancelTntDomScheduleCollectionTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        Map<String, Object> dataSend  = context.get(Attributes.CANCEL_COLLECTION_DATA_MAPPING);
        String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/tnt_dom_cancel_schedule_email_" + System.currentTimeMillis()+ ".txt";
        AppUtils.template2File(outputFilePath, false, "templates/email/tnt_dom_booking/tnt_dom_cancel_schedule_collection.ftl", dataSend);
        String content = AppUtils.readUTF8File2String(outputFilePath);
        String subject = "Cancel Shipment for " + context.get(Attributes.AIRBILL_NUMBER);
        String franchiseEmail = context.get(Attributes.FRANCHISE_EMAIL);

        String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
        String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
        String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
        String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
        String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
        int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
        AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, franchiseEmail, null, null, subject, content, null);
        return true;
    }
}
