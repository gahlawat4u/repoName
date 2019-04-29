package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminSystemSettingFilter;
import com.gms.xms.txndb.vo.SystemSettingDefaultValueVo;
import com.gms.xms.txndb.vo.SystemSettingVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IAdminSystemSettingService
 * <p>
 * Author TANDT 15-10-2015
 */
public interface IAdminSystemSettingService {
    public List<SystemSettingVo> getSystemSettingAll(AdminSystemSettingFilter filter) throws DaoException;

    public Long getSystemSettingCount() throws DaoException;

    public SystemSettingVo getSystemSettingById(Integer settingId) throws DaoException;

    public void update(Map<String, String> context, SystemSettingVo systemSettingVo) throws DaoException;

    public List<SystemSettingDefaultValueVo> selectAllSystemSetting(SystemSettingDefaultValueVo defaultValueVo) throws DaoException;
}
