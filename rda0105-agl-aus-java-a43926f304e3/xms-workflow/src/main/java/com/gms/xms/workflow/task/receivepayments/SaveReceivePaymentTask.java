package com.gms.xms.workflow.task.receivepayments;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CustomerPaymentDaoService;
import com.gms.xms.txndb.vo.CustomerPaymentVo;
import com.gms.xms.txndb.vo.NoteVo;
import com.gms.xms.txndb.vo.OverpaymentVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from SaveReceivePaymentTask
 * <p>
 * Author DatTV Date Apr 17, 2015 9:51:15 PM
 */
public class SaveReceivePaymentTask implements Task {
    private static final Log log = LogFactory.getLog(SaveReceivePaymentTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerPaymentVo customerPaymentVo = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_PAYMENT_OBJECT), CustomerPaymentVo.class);
        OverpaymentVo overpaymentVo = GsonUtils.fromGson(context.get(Attributes.OVERPAYMENT_OBJECT), OverpaymentVo.class);
        NoteVo noteVo = GsonUtils.fromGson(context.get(Attributes.NOTE_OBJECT), NoteVo.class);

        try {
            CustomerPaymentDaoService paymentDaoService = new CustomerPaymentDaoService();
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            // Do DAO service to save customer payment
            paymentDaoService.saveCustomerPayment(addInfo, customerPaymentVo, overpaymentVo, noteVo);
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
