package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.AdminEmailSettingVo;

import java.util.Map;

/**
 * Posted from AdminEmailSettingDao
 * <p>
 * Author TANDT
 */
public class AdminEmailSettingDao extends BaseDao<AdminEmailSettingVo> {

    public void delete(Map<String, String> context, Integer id) throws DaoException {
        this.delete(context, id, "AdminEmailSetting.delete");
    }

    public void insert(Map<String, String> context, AdminEmailSettingVo adminEmailSettingVo) throws DaoException {
        this.insert(context, adminEmailSettingVo, "AdminEmailSetting.insert");
    }

    public void updateStatus(Map<String, String> context, AdminEmailSettingVo adminEmailSettingVo) throws DaoException {
        this.update(context, adminEmailSettingVo, "AdminEmailSetting.updateStatus");
    }
}
