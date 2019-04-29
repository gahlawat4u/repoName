package com.gms.xms.workflow.task.overpayment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CustomerPaymentDaoService;
import com.gms.xms.txndb.vo.InvoicePaymentVo;
import com.gms.xms.txndb.vo.OverpaymentVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from SaveInvoicePaymentsTask
 * <p>
 * Author DatTV Date May 5, 2015 3:40:38 PM
 */
public class SaveInvoicePaymentsTask implements Task {
    private static final Log log = LogFactory.getLog(SaveInvoicePaymentsTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        List<InvoicePaymentVo> invoicePaymentVos = GsonUtils.fromGson(context.get(Attributes.INVOICE_PAYMENT_LIST), new TypeToken<List<InvoicePaymentVo>>() {
        }.getType());

        OverpaymentVo overpaymentVo = GsonUtils.fromGson(context.get(Attributes.OVERPAYMENT_OBJECT), OverpaymentVo.class);

        try {
            CustomerPaymentDaoService paymentDaoService = new CustomerPaymentDaoService();
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            // Do DAO service to save invoice payments
            paymentDaoService.saveInvoicePayment(addInfo, invoicePaymentVos, overpaymentVo);
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
