package com.gms.xms.persistence.service.webship.settings;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;
import com.gms.xms.txndb.vo.webship.settings.CustomerDefaultSettingDetailVo;

import java.util.Map;

/**
 * Posted from IUserSettingsService
 * <p>
 * Author DatTV Date Jul 14, 2015 10:44:08 AM
 */
public interface IUserSettingsService {
    public CustomerDefaultSettingVo selectByCustomerCode(Long customerCode) throws DaoException;

    public CustomerDefaultSettingDetailVo selectDetailByCustomerCode(Long customerCode) throws DaoException;

    public void insert(Map<String, String> context, CustomerDefaultSettingVo defaultSettingVo) throws DaoException;

    public void update(Map<String, String> context, CustomerDefaultSettingVo defaultSettingVo) throws DaoException;

    public String selectReferenceUserSetting(Long customerCode) throws DaoException;
}
