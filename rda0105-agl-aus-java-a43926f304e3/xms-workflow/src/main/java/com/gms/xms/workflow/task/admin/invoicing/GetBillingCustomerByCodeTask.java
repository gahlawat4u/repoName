package com.gms.xms.workflow.task.admin.invoicing;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CustomerBillingAddressDao;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetBillingCustomerByCodeTask
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class GetBillingCustomerByCodeTask implements Task {
    private static final Log log = LogFactory.getLog(GetBillingCustomerByCodeTask.class);

    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        CustomerBillingAddressDao customerBillingAddressDao = new CustomerBillingAddressDao();
        try {
            List<CreditNoteVo> listCreditNoteTo = GsonUtils.fromGson(context.get(Attributes.LIST_CREDIT_NOTES_TO), new TypeToken<List<CreditNoteVo>>() {
            }.getType());
            Long customerCode = 0L;
            if (listCreditNoteTo.size() != 0) {
                for (int i = 0; i < listCreditNoteTo.size(); i++) {
                    if (listCreditNoteTo.get(i).getCustomer().getInvoiceToCustomerId() != null) {
                        customerCode = listCreditNoteTo.get(i).getCustomer().getInvoiceToCustomerId();
                        if (customerCode == 0) {
                            customerCode = listCreditNoteTo.get(i).getInvoice().getCustomerCode();
                        }
                    }
                }
            }

            CustomerBillingAddressVo customerBillingAddressVo = customerBillingAddressDao.selectBillingCustomerByCode(customerCode);
            context.put(Attributes.CUSTOMER_BILLING_ADDRESS_BY_CODE, GsonUtils.toGson(customerBillingAddressVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
