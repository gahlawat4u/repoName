package com.gms.xms.persistence.service.customeraddress;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.CustomerCodeFilter;
import com.gms.xms.txndb.vo.CustomerFranchiseInfoVo;

/**
 * Posted from ICustomerAddressService
 * <p>
 * Author HungNT Date Jul 11, 2015
 */
public interface ICustomerAddressService {
    public CustomerAddressVo getCustomerAddressByCustomerCode(Long customerCode) throws Exception;

    CustomerFranchiseInfoVo selectCustomerAndFranchiseInfo(CustomerCodeFilter customerCodeFilter) throws DaoException;
}
