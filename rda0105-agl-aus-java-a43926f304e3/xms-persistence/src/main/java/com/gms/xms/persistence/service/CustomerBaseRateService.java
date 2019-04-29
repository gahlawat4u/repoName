package com.gms.xms.persistence.service;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.txndb.vo.CustomerBaseRateFilter;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;


/**
 * Posted from CustomerBaseRateDao
 * <p>
 * Author HungNT Date Apr 22, 2015
 */
public class CustomerBaseRateService {

    public CustomerBaseRateVo selectCustomerBaseRateByCustomerCodeAndShipmentTypeIdWithDefaultValue(CustomerBaseRateFilter customerBaseRateFilter) throws DaoException {
        CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
        CustomerBaseRateVo result = customerBaseRateDao.selectCustomerBaseRateByCustomerCodeAndShipmentTypeId(customerBaseRateFilter);
        if (result == null) {
            result = new CustomerBaseRateVo();
            result.setZoneCheck(false);
            result.setRate(0D);
            result.setRateType(2);//margin
        }
        return result;
    }

    public Double selectCustomerBaseRateByZone(CustomerBaseRateFilter customerBaseRateFilter) throws DaoException {
        CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
        return customerBaseRateDao.selectCustomerBaseRateByZone(customerBaseRateFilter);
    }
}
