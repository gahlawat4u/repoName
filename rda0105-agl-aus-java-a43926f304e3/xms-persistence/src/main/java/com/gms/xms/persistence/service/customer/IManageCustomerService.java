package com.gms.xms.persistence.service.customer;

import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.account.customers.manage.AddCustomerVo;
import com.gms.xms.txndb.vo.account.customers.manage.SaveManageCustomerVo;

import java.util.Map;

/**
 * Posted from Apr 13, 2016 10:26:26 AM
 * <p>
 * Author dattrinh
 */
public interface IManageCustomerService {
    public void updateCustomer(Map<String, String> context, SaveManageCustomerVo customer) throws Exception;

    public void addCustomer(Map<String, String> context, AddCustomerVo customer, Long profileId) throws Exception;

    public void updateCustomerActivationDate(Map<String, String> context, String customerCode) throws Exception;

    public void updateCustomerActivationDate(Map<String, String> context, String customerCode, SqlSessionClient sessionClient) throws Exception;
}
