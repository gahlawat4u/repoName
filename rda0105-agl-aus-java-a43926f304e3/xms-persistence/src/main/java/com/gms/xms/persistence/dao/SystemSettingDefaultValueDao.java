package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.SystemSettingDefaultValueVo;

import java.util.List;

/**
 * Posted from SystemSettingDefaultValueDao
 * <p>
 * Author TANDT 19-10-2015
 */
public class SystemSettingDefaultValueDao extends BaseDao<SystemSettingDefaultValueVo> {
    public SystemSettingDefaultValueDao() {
        super();
    }

    public List<SystemSettingDefaultValueVo> selectAllSystemSetting(SystemSettingDefaultValueVo defaultValueVo) throws DaoException {
        return this.selectList(defaultValueVo, "SystemSettingDefaultValue.selectAllSystemSetting");
    }

}