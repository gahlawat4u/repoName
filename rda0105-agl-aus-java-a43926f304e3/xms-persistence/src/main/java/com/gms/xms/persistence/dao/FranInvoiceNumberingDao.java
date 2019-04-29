package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.dto.FranInvoiceNumberingVo;
import com.gms.xms.persistence.config.SqlSessionClient;

import java.util.Map;

/**
 * Posted from Sep 28, 2016 9:53:06 AM
 * <p>
 * Author dattrinh
 */
public class FranInvoiceNumberingDao extends BaseDao<Object> {
    public FranInvoiceNumberingDao() {
        super();
    }

    public FranInvoiceNumberingDao(SqlSessionClient sessionClient) {
        super(sessionClient);
    }

    public FranInvoiceNumberingVo selectByFilter(FranInvoiceNumberingVo filter) throws DaoException {
        return (FranInvoiceNumberingVo) select(filter, "FranInvoiceNumbering.selectByFilter");
    }

    public Integer getCurrentCounter(FranInvoiceNumberingVo filter) throws DaoException {
        return (Integer) selectObject(filter, "FranInvoiceNumbering.getCurrentCounter");
    }

    public void insertCounter(Map<String, String> context, FranInvoiceNumberingVo numberingVo) throws DaoException {
        insert(context, numberingVo, "FranInvoiceNumbering.insertCounter");
    }
}
