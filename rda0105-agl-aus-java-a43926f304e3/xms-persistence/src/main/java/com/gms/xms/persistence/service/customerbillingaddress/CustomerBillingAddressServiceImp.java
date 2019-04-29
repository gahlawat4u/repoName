package com.gms.xms.persistence.service.customerbillingaddress;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CustomerBillingAddressDao;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;

/**
 * Posted from CustomerBillingAddressServiceImp
 * <p>
 * Author HungNT Date Jul 12, 2015
 */
public class CustomerBillingAddressServiceImp implements ICustomerBillingAddressService {
    private CustomerBillingAddressDao dao = new CustomerBillingAddressDao();

    @Override
    public CustomerBillingAddressVo getCustomerBillingAddressByCustomerCode(Long customerCode) throws DaoException {
        CustomerBillingAddressVo addressVo = dao.selectBillingCustomerByCode(customerCode);
        return addressVo;
    }
}
