package com.gms.xms.workflow.task2.generateetfile.toll;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.persistence.dao.webship.TollManifestDao;
import com.gms.xms.txndb.vo.toll.TollManifestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from Sep 24, 2016 10:22:20 AM
 * <p>
 * Author huynd
 */
public class SendEmailForTollTask implements Task2 {
    private static final Log log = LogFactory.getLog(SendEmailForTollTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            String connectedError = context.getString(GenerateETFileConstants.CONNECTED_ERROR);
            if ("0".equalsIgnoreCase(connectedError)) {
                Long manifestId = context.getLong(GenerateETFileConstants.MANIFEST_ID);
                TollManifestDao tollManifestDao = new TollManifestDao();
                TollManifestVo tollManifestVo = new TollManifestVo();
                tollManifestVo.setStatus(1);
                tollManifestVo.setManifestId(manifestId);
                tollManifestDao.updateTollManifestStatus(addInfo, tollManifestVo);
            }
            // Config email
            String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
            int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
            String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
            String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
            String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
            String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
            String toEmail = "nguyendanghuyhb@gmail.com";

            String fileName = context.getString(GenerateETFileConstants.FILE_NAME);
            String subject = "Toll Manifest file dispose";
            String content = connectedError + "<br/> Generate Mainfest Finsihed for " + fileName;
            AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, toEmail, "", "", subject, content, null);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
