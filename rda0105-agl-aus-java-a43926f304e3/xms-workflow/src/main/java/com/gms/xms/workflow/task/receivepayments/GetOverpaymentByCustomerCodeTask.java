package com.gms.xms.workflow.task.receivepayments;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.OverpaymentDao;
import com.gms.xms.txndb.vo.OverpaymentVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetOverpaymentByCustomerCodeTask
 * <p>
 * Author DatTV Date Apr 10, 2015 4:14:29 PM
 */
public class GetOverpaymentByCustomerCodeTask implements Task {
    private static final Log log = LogFactory.getLog(GetOverpaymentByCustomerCodeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        OverpaymentDao overpaymentDao = new OverpaymentDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            long customerCode = Long.parseLong(context.getString(Attributes.CUSTOMER_CODE));

            // Do DAO service to gets list of payments
            List<OverpaymentVo> paymentList = overpaymentDao.selectByCustomerCode(customerCode);

            // Puts result into the context
            context.put(Attributes.OVERPAYMENT_LIST_RESULT, GsonUtils.toGson(paymentList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
