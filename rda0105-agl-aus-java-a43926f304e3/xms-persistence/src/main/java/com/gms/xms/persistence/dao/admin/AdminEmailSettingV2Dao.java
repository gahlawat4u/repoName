package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminEmailFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.AdminEmailSettingV2Vo;

import java.util.List;

/**
 * Posted from AdminEmailDao
 * <p>
 * Author TANDT
 */
public class AdminEmailSettingV2Dao extends BaseDao<AdminEmailSettingV2Vo> {
    public List<AdminEmailSettingV2Vo> selectAdminEmailSetting(AdminEmailFilter filter) throws DaoException {
        return this.selectList(filter, "AdminEmail.selectAdminEmailSetting");
    }
}
