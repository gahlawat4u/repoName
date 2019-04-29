package com.gms.xms.persistence.service.customer;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CustomerDefaultSettingDao;
import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;

/**
 * @author TANDT
 */
public class CustomerDefaultSettingServiceImp implements ICustomerDefaultSettingService {

    @Override
    public CustomerDefaultSettingVo selectByCustomerCode(Long customerCode) throws DaoException {
        CustomerDefaultSettingDao dao = new CustomerDefaultSettingDao();
        return dao.selectByCustomerCode(customerCode);
    }

}