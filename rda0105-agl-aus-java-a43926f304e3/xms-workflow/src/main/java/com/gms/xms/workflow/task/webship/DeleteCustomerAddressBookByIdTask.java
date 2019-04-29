package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.webship.addressbook.CustomerAddressBookDao;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class DeleteCustomerAddressBookByIdTask implements Task {
    private static final Log log = LogFactory.getLog(DeleteCustomerAddressBookByIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerAddressBookDao addressBookDao = new CustomerAddressBookDao();
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            long addressId = Long.parseLong(context.get(Attributes.CUSTOMER_ADDRESS_BOOK_ID));
            addressBookDao.deleteCustomerAddressBookById(addInfo, addressId);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            log.error(e);
            return false;
        }
        return true;
    }

}
