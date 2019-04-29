package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.webship.addressbook.CustomerAddressBookDao;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookVo;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class UpdateCustomerAddressBookByIdTask implements Task {
    private static final Log log = LogFactory.getLog(UpdateCustomerAddressBookByIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerAddressBookDao addressBookDao = new CustomerAddressBookDao();
        try {
            // Get additional information from the context to put into the service or dao.
            Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
            }.getType());
            CustomerAddressBookVo addressBookVo = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_ADDRESS_BOOK_OBJECT), CustomerAddressBookVo.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            addressBookDao.updateCustomerAddressBookById(addInfo, addressBookVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            log.error(e);
            return false;
        }
        return true;
    }
}
