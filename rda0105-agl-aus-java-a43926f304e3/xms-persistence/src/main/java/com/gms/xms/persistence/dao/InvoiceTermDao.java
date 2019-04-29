package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.InvoiceTermVo;

import java.util.List;

/**
 * Posted from InvoiceTermDao
 * <p>
 * Author DatTV Sep 22, 2015
 */
public class InvoiceTermDao extends BaseDao<InvoiceTermVo> {
    public List<InvoiceTermVo> selectAll() throws DaoException {
        return selectList(null, "InvoiceTerm.selectAll");
    }
}
