package test.com.gms.xms.common;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PrepareEmailContentTest {

    @Test
    public void prepareEmailContentTest() throws DaoException {
        EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
        EmailTemplateVo emailTemplateVo = emailTemplateDao.getEmailTemplateByName(AppConstants.SEND_FRANCHISE_PAYABLE_EMAIL);
        String subject = emailTemplateVo != null ? emailTemplateVo.getSubject() : "Franchise Payable Report # [Period]";
        String period = "12/12/2015 - 12/32/2015";
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("\\[Period\\]", period);
        subject = AppUtils.replaceStringByMap(replaceMap, subject);
        replaceMap.clear();

        String content = emailTemplateVo != null ? emailTemplateVo.getTemplateContent() : "This email was sent from XMS 2.0 system. Attacted to it is a PDF copy of Franchise Payable Report for activity during [Period] for [Franchise Name]";
        replaceMap.put("\\[Franchise Name\\]", "Toantq");
        replaceMap.put("\\[Period\\]", period);
        replaceMap.put("\r\n", "<br>");
        content = AppUtils.replaceStringByMap(replaceMap, content);
        System.out.println(subject);
        System.out.println(content);
    }
}