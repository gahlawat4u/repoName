package com.gms.xms.persistence.service.customeraddress;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CustomerAddressDao;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.CustomerCodeFilter;
import com.gms.xms.txndb.vo.CustomerFranchiseInfoVo;

/**
 * Posted from CustomerAddressServiceImp
 * <p>
 * Author HungNT Date Jul 11, 2015
 */
public class CustomerAddressServiceImp implements ICustomerAddressService {
    private CustomerAddressDao customerAddressDao = new CustomerAddressDao();

    @Override
    public CustomerAddressVo getCustomerAddressByCustomerCode(Long customerCode) throws Exception {
        CustomerAddressVo customerAddressVo = customerAddressDao.selectByCustomerCode(customerCode);
        return customerAddressVo;
    }

    @Override
    public CustomerFranchiseInfoVo selectCustomerAndFranchiseInfo(CustomerCodeFilter customerCodeFilter) throws DaoException {
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        return customerAddressDao.selectCustomerAndFranchiseInfo(customerCodeFilter);
    }
}
