package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminSystemSettingFilter;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.persistence.dao.SystemSettingDefaultValueDao;
import com.gms.xms.txndb.vo.SystemSettingDefaultValueVo;
import com.gms.xms.txndb.vo.SystemSettingVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AdminEmailSettingServiceImp
 * <p>
 * Author TANDT
 */
public class AdminSystemSettingServiceImp implements IAdminSystemSettingService {

    @Override
    public List<SystemSettingVo> getSystemSettingAll(AdminSystemSettingFilter filter) throws DaoException {
        SystemSettingDao dao = new SystemSettingDao();
        return dao.getAllByFilter(filter);
    }

    @Override
    public Long getSystemSettingCount() throws DaoException {
        SystemSettingDao dao = new SystemSettingDao();
        return dao.getAllCount();
    }

    @Override
    public SystemSettingVo getSystemSettingById(Integer settingId) throws DaoException {
        SystemSettingDao dao = new SystemSettingDao();
        return dao.getSystemSettingById(settingId);
    }

    @Override
    public void update(Map<String, String> context, SystemSettingVo systemSettingVo) throws DaoException {
        SystemSettingDao dao = new SystemSettingDao();
        dao.update(context, systemSettingVo);
    }

    @Override
    public List<SystemSettingDefaultValueVo> selectAllSystemSetting(SystemSettingDefaultValueVo defaultValueVo) throws DaoException {
        SystemSettingDefaultValueDao dao = new SystemSettingDefaultValueDao();
        return dao.selectAllSystemSetting(defaultValueVo);
    }

}
