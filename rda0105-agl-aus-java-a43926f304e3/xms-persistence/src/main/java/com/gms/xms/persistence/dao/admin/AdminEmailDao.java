package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminEmailFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.AdminEmailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AdminEmailDao
 * <p>
 * Author TANDT
 */
public class AdminEmailDao extends BaseDao<AdminEmailVo> {
    @SuppressWarnings("unchecked")
    public List<String> selectAdminEmailsList(String settingName) throws DaoException {
        return (List<String>) (Object) selectObjectList(settingName, "AdminEmail.selectAdminEmailsList");
    }

    public List<AdminEmailVo> selectByFilter(AdminEmailFilter filter) throws DaoException {
        return this.selectList(filter, "AdminEmail.selectByFilter");
    }

    public void insert(Map<String, String> context, AdminEmailVo adminEmailVo) throws DaoException {
        this.insert(context, adminEmailVo, "AdminEmail.insert");
    }

    public void update(Map<String, String> context, AdminEmailVo adminEmailVo) throws DaoException {
        this.insert(context, adminEmailVo, "AdminEmail.update");
    }

    public int selectAdminEmailSettingCount(AdminEmailFilter filter) throws DaoException {
        return (int) this.selectObject(filter, "AdminEmail.selectAdminEmailSettingCount");
    }

    public void delete(Map<String, String> context, Integer id) throws DaoException {
        this.delete(context, id, "AdminEmail.delete");
    }

    public int checkEmail(String email) throws DaoException {
        return (int) this.selectObject(email, "AdminEmail.checkEmail");
    }
}
