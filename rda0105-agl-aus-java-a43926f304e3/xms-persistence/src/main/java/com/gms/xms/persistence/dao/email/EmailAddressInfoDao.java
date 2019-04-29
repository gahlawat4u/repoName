package com.gms.xms.persistence.dao.email;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.dto.email.EmailAddressInfoVo;
import com.gms.xms.persistence.dao.BaseDao;

import java.util.List;

/**
 * Posted from Jul 26, 2016 2:35:03 PM
 * <p>
 * Author dattrinh
 */
public class EmailAddressInfoDao extends BaseDao<Object> {
    public EmailAddressInfoVo getEmailAddressInfoByCustCode(String customerCode) throws DaoException {
        return (EmailAddressInfoVo) select(customerCode, "EmailAddressInfo.getEmailAddressInfoByCustCode");
    }

    public List<String> getAdminEmailListByName(String name) throws DaoException {
        return selectList(name, "EmailAddressInfo.getAdminEmailListByName");
    }
}