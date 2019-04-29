package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetMailPaymentToInvoiceTask
 * <p>
 * Author TANDT
 */
public class GetMailPaymentToInvoiceTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetMailPaymentToInvoiceTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            String mailPaymentTo = "";
            mailPaymentTo = SystemSettingCache.getInstance().getValueByKey("Mail Payment To Address");
            mailPaymentTo = StringUtils.replace(mailPaymentTo, "\n", "<br/>");
            context.put(Attributes.MAIL_PAYMENT_TO, mailPaymentTo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
