package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;

import java.util.Map;

/**
 * Posted from CustomerBillingAddressDao
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class CustomerBillingAddressDao extends BaseDao<CustomerBillingAddressVo> {
    public CustomerBillingAddressDao() {
        super();
    }

    public CustomerBillingAddressDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public CustomerBillingAddressVo selectBillingCustomerByCode(Long customerCode) throws DaoException {
        return select(customerCode, "CustomerBillingAddress_SelectBillingCustomerByCode");
    }

    public void insertCustomerBillingAddress(Map<String, String> context, CustomerBillingAddressVo billingAddressVo) throws DaoException {
        if (billingAddressVo.getUsertype() == null) {
            billingAddressVo.setUsertype(1);
        }
        this.insert(context, billingAddressVo, "CustomerBillingAddress.insertCustomerBillingAddress");
    }

    public void updateCustomerBillingAddress(Map<String, String> context, CustomerBillingAddressVo billingAddressVo) throws DaoException {
        this.update(context, billingAddressVo, "CustomerBillingAddress.updateCustomerBillingAddress");
    }

    public CustomerBillingAddressVo getByCustomerCode(String customerCode) throws DaoException {
        return this.select(customerCode, "CustomerBillingAddress.getByCustomerCode");
    }
}
