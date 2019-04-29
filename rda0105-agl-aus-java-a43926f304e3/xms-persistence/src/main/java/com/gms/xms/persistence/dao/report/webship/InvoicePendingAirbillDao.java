package com.gms.xms.persistence.dao.report.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.webship.InvoicePendingAirbillFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.webship.InvoicePendingAirbillVo;

import java.util.List;

/**
 * Posted from InvoicePendingAirbillDao
 * <p>
 * Author DatTV Dec 4, 2015
 */
public class InvoicePendingAirbillDao extends BaseDao<InvoicePendingAirbillVo> {
    public InvoicePendingAirbillDao() {
        super();
    }

    public InvoicePendingAirbillDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<InvoicePendingAirbillVo> getInvoicePendingAirbillReport(InvoicePendingAirbillFilter filter) throws DaoException {
        return this.selectList(filter, "InvoicePendingAirbill.getInvoicePendingAirbillReport");
    }

    public long countInvoicePendingAirbillReport(InvoicePendingAirbillFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "InvoicePendingAirbill.countInvoicePendingAirbillReport");
    }
}