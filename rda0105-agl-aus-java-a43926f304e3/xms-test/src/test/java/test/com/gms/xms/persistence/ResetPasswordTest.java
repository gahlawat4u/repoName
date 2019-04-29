package test.com.gms.xms.persistence;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.utils.AppUtils;
import org.junit.Test;

/**
 * File Name: ResetPasswordTest.java <br/>
 * Author: TANDT <br/>
 * Create Date: Jan 7, 2016 <br/>
 * Project Name: xms-test <br/>
 * package Name: test.com.gms.xms.persistence <br/>
 * Class: ResetPasswordTest
 */
public class ResetPasswordTest {

    @Test
    public void sendEmail() {
        String host = SystemSettingCache.getInstance().getValueByKey("SMTP Server Name");
        int port = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("SMTP Port Number"));
        String fromEmail = SystemSettingCache.getInstance().getValueByKey("From Email");
        String username = SystemSettingCache.getInstance().getValueByKey("Email User Name");
        String password = SystemSettingCache.getInstance().getValueByKey("Email Password");
        String fromName = SystemSettingCache.getInstance().getValueByKey("From Name");
        String cc = null;
        String bcc = null;
        String toEmail = "tkvclub01hp@gmail.com";
        String subject = "this is test from send email";
        String content = "Test content";
        String[] attachFiles = {"d:/temp.txt"};
        try {
            AppUtils.sendEmail(host, port, fromName, fromEmail, username, password, toEmail, cc, bcc, subject, content, attachFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}