package com.gms.xms.persistence.service.webship.settings;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CustomerDefaultSettingDao;
import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;
import com.gms.xms.txndb.vo.webship.settings.CustomerDefaultSettingDetailVo;

import java.util.Map;

/**
 * Posted from UserSettingsServiceImp
 * <p>
 * Author DatTV Date Jul 14, 2015 10:44:13 AM
 */
public class UserSettingsServiceImp implements IUserSettingsService {
    private CustomerDefaultSettingDao customerDefaultSettingDao = new CustomerDefaultSettingDao();

    @Override
    public CustomerDefaultSettingVo selectByCustomerCode(Long customerCode) throws DaoException {
        return customerDefaultSettingDao.selectByCustomerCode(customerCode);
    }

    @Override
    public void insert(Map<String, String> context, CustomerDefaultSettingVo defaultSettingVo) throws DaoException {
        customerDefaultSettingDao.insert(context, defaultSettingVo);
    }

    @Override
    public void update(Map<String, String> context, CustomerDefaultSettingVo defaultSettingVo) throws DaoException {
        customerDefaultSettingDao.update(context, defaultSettingVo);
    }

    @Override
    public CustomerDefaultSettingDetailVo selectDetailByCustomerCode(Long customerCode) throws DaoException {
        return customerDefaultSettingDao.selectDetailByCustomerCode(customerCode);
    }

    @Override
    public String selectReferenceUserSetting(Long customerCode) throws DaoException {
        return customerDefaultSettingDao.selectReferenceUserSetting(customerCode);
    }

}