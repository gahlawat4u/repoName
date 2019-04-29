package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminEmailFilter;
import com.gms.xms.persistence.dao.admin.AdminEmailDao;
import com.gms.xms.persistence.dao.admin.AdminEmailDaoService;
import com.gms.xms.persistence.dao.admin.AdminEmailSettingV2Dao;
import com.gms.xms.txndb.vo.admin.AdminEmailSettingV2Vo;
import com.gms.xms.txndb.vo.admin.AdminEmailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AdminEmailImp
 * <p>
 * Author TANDT
 */
public class AdminEmailServiceImp implements IAdminEmailService {

    @Override
    public List<String> getAdminEmailsList(String settingName) throws DaoException {
        AdminEmailDao dao = new AdminEmailDao();
        return dao.selectAdminEmailsList(settingName);
    }

    @Override
    public List<AdminEmailVo> selectByFilter(AdminEmailFilter filter) throws DaoException {
        AdminEmailDao dao = new AdminEmailDao();
        return dao.selectByFilter(filter);
    }

    @Override
    public List<AdminEmailSettingV2Vo> selectAdminEmailSetting(AdminEmailFilter filter) throws DaoException {
        AdminEmailSettingV2Dao dao = new AdminEmailSettingV2Dao();
        return dao.selectAdminEmailSetting(filter);
    }

    @Override
    public void insert(Map<String, String> context, AdminEmailVo adminEmailVo) throws DaoException {
        AdminEmailDao dao = new AdminEmailDao();
        dao.insert(context, adminEmailVo);
    }

    @Override
    public void update(Map<String, String> context, AdminEmailVo adminEmailVo) throws DaoException {
        AdminEmailDao dao = new AdminEmailDao();
        dao.update(context, adminEmailVo);
    }

    @Override
    public int selectAdminEmailSettingCount(AdminEmailFilter filter) throws DaoException {
        AdminEmailDao dao = new AdminEmailDao();
        return dao.selectAdminEmailSettingCount(filter);
    }

    @Override
    public void delete(Map<String, String> context, Integer id) throws DaoException {
        AdminEmailDaoService dao = new AdminEmailDaoService();
        dao.deleteAdminEmail(context, id);
    }

    @Override
    public int checkEmail(String email) throws DaoException {
        AdminEmailDao dao = new AdminEmailDao();
        return dao.checkEmail(email);
    }
}
