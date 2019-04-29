package com.gms.xms.persistence.service.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.MoveInvoiceDateFilter;
import com.gms.xms.filter.invoicing.RepairAirbillErrorFilter;
import com.gms.xms.txndb.vo.invoicing.AirbillErrorVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IRepairAirbillErrorService
 * <p>
 * Author dattrinh Mar 8, 2016 3:06:03 PM
 */
public interface IRepairAirbillErrorService {
    public List<AirbillErrorVo> getAirbillErrorByFilter(RepairAirbillErrorFilter filter) throws DaoException;

    public long countAirbillErrorByFilter(RepairAirbillErrorFilter filter) throws DaoException;

    public void moveInvoiceDate(Map<String, String> context, MoveInvoiceDateFilter filter) throws DaoException;
}
