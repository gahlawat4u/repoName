package com.gms.xms.workflow.task.receivepayments;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CustomerAddressDao;
import com.gms.xms.txndb.vo.CustomerAddressFilter;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetCustomerAddressByNameTask
 * <p>
 * Author DatTV Date Apr 9, 2015 11:14:19 AM
 */
public class GetCustomerAddressByNameTask implements Task {
    private static final Log log = LogFactory.getLog(GetCustomerAddressByNameTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        try {
            CustomerAddressFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_ADDRESS_FILTER), CustomerAddressFilter.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to gets list of customer address by name
            List<CustomerAddressVo> customerList = customerAddressDao.getByName(filter);

            // Puts result into the context
            context.put(Attributes.CUSTOMER_ADDRESS_LIST_RESULT, GsonUtils.toGson(customerList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
