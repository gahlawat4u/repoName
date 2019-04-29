package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.dto.InvoiceNumberingVo;
import com.gms.xms.persistence.config.SqlSessionClient;

import java.util.Map;

/**
 * Posted from Aug 5, 2016 5:24:24 PM
 * <p>
 * Author dattrinh
 */
public class InvoiceNumberingDao extends BaseDao<Object> {
    public InvoiceNumberingDao() {
        super();
    }

    public InvoiceNumberingDao(SqlSessionClient sessionClient) {
        super(sessionClient);
    }

    public InvoiceNumberingVo selectByFilter(InvoiceNumberingVo filter) throws DaoException {
        return (InvoiceNumberingVo) select(filter, "InvoiceNumbering.selectByFilter");
    }

    public InvoiceNumberingVo selectCounter(InvoiceNumberingVo filter) throws DaoException {
        return (InvoiceNumberingVo) select(filter, "InvoiceNumbering.selectCounter");
    }

    public Integer selectMaxCounter(InvoiceNumberingVo filter) throws DaoException {
        return (Integer) selectObject(filter, "InvoiceNumbering.selectMaxByFilter");
    }

    public void insert(Map<String, String> context, InvoiceNumberingVo invoiceNumberingVo) throws DaoException {
        this.insert(context, invoiceNumberingVo, "InvoiceNumbering.insert");
    }

    public void update(Map<String, String> context, InvoiceNumberingVo invoiceNumberingVo) throws DaoException {
        this.update(context, invoiceNumberingVo, "InvoiceNumbering.update");
    }

    public InvoiceNumberingVo getByInvoiceCode(String invoiceCode) throws DaoException {
        return (InvoiceNumberingVo) select(invoiceCode, "InvoiceNumbering.getByInvoiceCode");
    }
}
