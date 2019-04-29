package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdminEmailFilter;
import com.gms.xms.txndb.vo.admin.AdminEmailSettingV2Vo;
import com.gms.xms.txndb.vo.admin.AdminEmailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IAdminEmailService
 * <p>
 * Author TANDT
 */
public interface IAdminEmailService {
    public List<String> getAdminEmailsList(String settingName) throws DaoException;

    public List<AdminEmailVo> selectByFilter(AdminEmailFilter filter) throws DaoException;

    public List<AdminEmailSettingV2Vo> selectAdminEmailSetting(AdminEmailFilter filter) throws DaoException;

    public void insert(Map<String, String> context, AdminEmailVo adminEmailVo) throws DaoException;

    public void update(Map<String, String> context, AdminEmailVo adminEmailVo) throws DaoException;

    public int selectAdminEmailSettingCount(AdminEmailFilter filter) throws DaoException;

    public int checkEmail(String email) throws DaoException;

    public void delete(Map<String, String> context, Integer id) throws DaoException;
}
