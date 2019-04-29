package com.gms.xms.workflow.task.receivepayments;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CustomerAddressDao;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetCustomerAddressByCodeTask
 * <p>
 * Author DatTV Date Apr 25, 2015 3:13:51 PM
 */
public class GetCustomerAddressByCodeTask implements Task {
    private static final Log log = LogFactory.getLog(GetCustomerAddressByCodeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        try {
            String customerCode = context.getString(Attributes.CUSTOMER_CODE);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to check if the customer with specified code
            // exists or not
            CustomerAddressVo customerAddressVo = customerAddressDao.selectByCode(customerCode);

            // Puts result into the context
            context.put(Attributes.CUSTOMER_ADDRESS_RESULT, GsonUtils.toGson(customerAddressVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
