package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.InvoiceSettingFilter;
import com.gms.xms.persistence.dao.BaseDao;

public class InvoiceSettingDao extends BaseDao<Object> {
    public String getInvoiceSetting(InvoiceSettingFilter filter) throws DaoException {
        return (String) selectObject(filter, "InvoiceSetting.selectInvoiceSettingByFilter");
    }
}
