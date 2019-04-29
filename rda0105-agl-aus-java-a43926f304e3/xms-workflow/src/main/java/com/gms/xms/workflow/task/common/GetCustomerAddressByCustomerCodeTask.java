package com.gms.xms.workflow.task.common;

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

public class GetCustomerAddressByCustomerCodeTask implements Task {
    private static final Log log = LogFactory.getLog(GetCustomerAddressByCustomerCodeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        CustomerAddressFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_ADDRESS_FILTER), CustomerAddressFilter.class);

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            CustomerAddressVo customerAddressVo = customerAddressDao.selectByCustomerCode(filter.getCustomerCode());
            context.put(Attributes.CUSTOMER_ADDRESS_RESULT, GsonUtils.toGson(customerAddressVo));

        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e.getMessage());
            return false;
        }

        return true;
    }

}
