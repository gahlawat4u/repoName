package com.gms.xms.persistence.service.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.MoveInvoiceDateFilter;
import com.gms.xms.filter.invoicing.RepairAirbillErrorFilter;
import com.gms.xms.persistence.dao.invoice.RepairAirbillErrorDao;
import com.gms.xms.txndb.vo.invoicing.AirbillErrorVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from RepairAirbillErrorServiceImp
 * <p>
 * Author dattrinh Mar 9, 2016 11:24:28 AM
 */
public class RepairAirbillErrorServiceImp implements IRepairAirbillErrorService {
    private RepairAirbillErrorDao repairAirbillErrorDao = new RepairAirbillErrorDao();

    @Override
    public List<AirbillErrorVo> getAirbillErrorByFilter(RepairAirbillErrorFilter filter) throws DaoException {
        return repairAirbillErrorDao.getAirbillErrorByFilter(filter);
    }

    @Override
    public long countAirbillErrorByFilter(RepairAirbillErrorFilter filter) throws DaoException {
        return repairAirbillErrorDao.countAirbillErrorByFilter(filter);
    }

    @Override
    public void moveInvoiceDate(Map<String, String> context, MoveInvoiceDateFilter filter) throws DaoException {
        repairAirbillErrorDao.moveInvoiceDate(context, filter);
    }
}