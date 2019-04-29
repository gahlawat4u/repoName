package com.gms.xms.workflow.task.admin.invoicing;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from GetListCustomerCodeByLoginTask
 * <p>
 * Author TanDT Date May 21, 2015
 */
public class GetListCustomerCodeByLoginTask implements Task {
    private static final Log log = LogFactory.getLog(GetListCustomerCodeByLoginTask.class);

    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            UserVo userVo = GsonUtils.fromGson(context.get(Attributes.ADMIN_USER_INFO_LOGIN), UserVo.class);
            List<CustomerVo> listCustomerVos = this.prepareListCustomerCode(userVo);
            context.put(Attributes.LIST_CUSTOMER_CODE_BY_LOGIN, GsonUtils.toGson(listCustomerVos));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    private List<CustomerVo> prepareListCustomerCode(UserVo userVo) throws Exception {
        CustomerDao customerDao = new CustomerDao();
        List<CustomerVo> lisCustomerVos = new ArrayList<CustomerVo>();
        // Is Administrator or Supper Admin
        if (userVo.getUserId() == 1 || userVo.getUserId() == 2) {
            lisCustomerVos = customerDao.selectCustomerCodeByLoginIsAdmin();
        } else if (userVo.getUserId() < 8) { // Is Franchise || Account
            lisCustomerVos = customerDao.selectCustomerCodeByLoginIsFranchiseAccount(userVo);
        } else if (userVo.getUserId() == 8) { // Sale Manager
            lisCustomerVos = customerDao.selectCustomerCodeByLoginIsSaleManager(userVo);
        } else if (userVo.getUserId() == 11) { // Is Sale Rep
            lisCustomerVos = customerDao.selectCustomerCodeByLoginIsSaleRep(userVo);
        } else if (userVo.getUserId() == 11) { // Telemarketer
            lisCustomerVos = customerDao.selectCustomerCodeByLoginIsSaleTelemarketer(userVo);
        }

        return lisCustomerVos;
    }

}
