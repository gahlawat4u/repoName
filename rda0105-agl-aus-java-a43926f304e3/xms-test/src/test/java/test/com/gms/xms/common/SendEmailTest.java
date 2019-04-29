package test.com.gms.xms.common;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import org.junit.Test;

public class SendEmailTest {

    @Test
    public void sendMailTest() throws DaoException {
        String host = SystemSettingCache.getInstance().getValueByKey("SMTP Server Name");
        int port = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("SMTP Port Number"));
        String fromEmail = SystemSettingCache.getInstance().getValueByKey("From Email");
        String username = SystemSettingCache.getInstance().getValueByKey("Email User Name");
        String password = SystemSettingCache.getInstance().getValueByKey("Email Password");
        String fromName = SystemSettingCache.getInstance().getValueByKey("From Name");
        String cc = null;
        String bcc = null;
        String toEmail = "toanah@gmail.com";
        String subject = "this is test from send email";
        String content = "Test content";
        String[] attachFiles = {"d:/gateway.log"};
        try {
            AppUtils.sendEmail(host, port, fromName, fromEmail, username, password, toEmail, cc, bcc, subject, content, attachFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}