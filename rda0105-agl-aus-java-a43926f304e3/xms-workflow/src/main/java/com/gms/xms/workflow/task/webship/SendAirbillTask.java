package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetFromEmailTask
 * <p>
 * Author TanDT Date Apr 2, 2015
 */
public class SendAirbillTask implements Task {
    private static final Log log = LogFactory.getLog(SendAirbillTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        String template_name = context.getString(Attributes.EMAIL_TEMPLATE_NAME);
        try {
            EmailTemplateVo emailTemplateVo = emailTemplateDao.getEmailTemplateByName(template_name);
            context.put(Attributes.EMAIL_TEMPLATE_RESULT, GsonUtils.toGson(emailTemplateVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
