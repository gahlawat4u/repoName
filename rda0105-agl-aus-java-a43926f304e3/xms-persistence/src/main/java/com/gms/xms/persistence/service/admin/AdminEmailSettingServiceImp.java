package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.admin.AdminEmailSettingDao;
import com.gms.xms.txndb.vo.admin.AdminEmailSettingVo;

import java.util.Map;

/**
 * Posted from AdminEmailSettingServiceImp
 * <p>
 * Author TANDT
 */
public class AdminEmailSettingServiceImp implements IAdminEmailSettingService {

    @Override
    public void delete(Map<String, String> context, Integer id) throws DaoException {
        AdminEmailSettingDao dao = new AdminEmailSettingDao();
        dao.delete(context, id);
    }

    @Override
    public void insert(Map<String, String> context, AdminEmailSettingVo adminEmailSettingVo) throws DaoException {
        AdminEmailSettingDao dao = new AdminEmailSettingDao();
        dao.insert(context, adminEmailSettingVo);
    }

    @Override
    public void updateStatus(Map<String, String> context, AdminEmailSettingVo adminEmailSettingVo) throws DaoException {
        AdminEmailSettingDao dao = new AdminEmailSettingDao();
        dao.updateStatus(context, adminEmailSettingVo);
    }

}
