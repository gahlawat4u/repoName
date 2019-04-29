package com.gms.xms.persistence.service.customer;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;

/**
 * @author TANDT
 */
public interface ICustomerDefaultSettingService {
    public CustomerDefaultSettingVo selectByCustomerCode(Long customerCode) throws DaoException;
}
