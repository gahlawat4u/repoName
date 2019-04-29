package test.com.gms.xms.persistence;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import org.junit.Test;

public class AdminEmailTest {

    @Test
    public void test() throws DaoException {

        String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
        String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
        String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
        String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
        int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
        String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);

        try {
            AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, "tkvclub01hp@gmail.com", "", null, "Subject", "content", null);
        } catch (Exception e) {

        }

    }
}