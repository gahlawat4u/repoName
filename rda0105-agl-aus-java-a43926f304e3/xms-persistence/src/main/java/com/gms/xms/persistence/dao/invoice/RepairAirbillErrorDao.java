package com.gms.xms.persistence.dao.invoice;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.MoveInvoiceDateFilter;
import com.gms.xms.filter.invoicing.RepairAirbillErrorFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.invoicing.AirbillErrorVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from RepairAirbillErrorDao
 * <p>
 * Author dattrinh Mar 7, 2016 4:44:38 PM
 */
public class RepairAirbillErrorDao extends BaseDao<AirbillErrorVo> {
    public List<AirbillErrorVo> getAirbillErrorByFilter(RepairAirbillErrorFilter filter) throws DaoException {
        return this.selectList(filter, "RepairAirbillError.getAirbillErrorByFilter");
    }

    public long countAirbillErrorByFilter(RepairAirbillErrorFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "RepairAirbillError.countAirbillErrorByFilter");
    }

    public void moveInvoiceDate(Map<String, String> context, MoveInvoiceDateFilter filter) throws DaoException {
        this.update(context, filter, "RepairAirbillError.moveInvoiceDate");
    }
}
