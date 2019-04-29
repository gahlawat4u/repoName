package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.admin.AdminEmailSettingVo;

import java.util.Map;

/**
 * Posted from IAdminEmailSettingService
 * <p>
 * Author TANDT
 */
public interface IAdminEmailSettingService {
    public void delete(Map<String, String> context, Integer id) throws DaoException;

    public void insert(Map<String, String> context, AdminEmailSettingVo adminEmailSettingVo) throws DaoException;

    public void updateStatus(Map<String, String> context, AdminEmailSettingVo adminEmailSettingVo) throws DaoException;
}
