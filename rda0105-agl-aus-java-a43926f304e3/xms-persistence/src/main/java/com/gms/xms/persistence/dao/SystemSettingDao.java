package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminSystemSettingFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.SystemSettingVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from SystemSettingDao.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:22:26 PM
 */
public class SystemSettingDao extends BaseDao<SystemSettingVo> {

    public SystemSettingDao() {
        super();
    }

    public SystemSettingDao(SqlSessionClient sessionClient) {
        super(sessionClient);
    }

    // Get system setting by name
    public SystemSettingVo getSystemSettingByName(String name) throws DaoException {
        return select(name, "SystemSetting.getSystemSettingByName");
    }

    public List<SystemSettingVo> getAll() throws DaoException {
        return selectList(new SystemSettingVo(), "SystemSetting.getAll");
    }

    public List<SystemSettingVo> getAllByFilter(AdminSystemSettingFilter filter) throws DaoException {
        return selectList(filter, "SystemSetting.getAllByFilter");
    }

    public Long getAllCount() throws DaoException {
        return (long) selectObject(null, "SystemSetting.getAllCount");
    }

    public SystemSettingVo getSystemSettingById(Integer settingId) throws DaoException {
        return select(settingId, "SystemSetting.getSystemSettingById");
    }

    public void insert(Map<String, String> context, SystemSettingVo systemSettingVo) throws DaoException {
        this.update(context, systemSettingVo, "SystemSetting.insert");
    }

    public void update(Map<String, String> context, SystemSettingVo systemSettingVo) throws DaoException {
        this.update(context, systemSettingVo, "SystemSetting.update");
    }
}
