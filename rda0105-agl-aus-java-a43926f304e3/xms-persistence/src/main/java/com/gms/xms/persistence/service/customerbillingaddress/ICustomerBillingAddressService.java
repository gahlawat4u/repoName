package com.gms.xms.persistence.service.customerbillingaddress;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;

/**
 * Posted from ICustomerBillingAddressService
 * <p>
 * Author HungNT Date Jul 12, 2015
 */
public interface ICustomerBillingAddressService {
    public CustomerBillingAddressVo getCustomerBillingAddressByCustomerCode(Long customerCode) throws DaoException;
}
