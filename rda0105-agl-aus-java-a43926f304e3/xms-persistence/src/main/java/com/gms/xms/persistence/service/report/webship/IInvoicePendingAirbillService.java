package com.gms.xms.persistence.service.report.webship;

import com.gms.xms.filter.reports.webship.InvoicePendingAirbillFilter;
import com.gms.xms.txndb.vo.reports.webship.InvoicePendingAirbillVo;

import java.util.List;

/**
 * Posted from IInvoicePendingAirbillService
 * <p>
 * Author DatTV Dec 4, 2015
 */
public interface IInvoicePendingAirbillService {
    public List<InvoicePendingAirbillVo> getInvoicePendingAirbillReport(InvoicePendingAirbillFilter filter) throws Exception;

    public long countInvoicePendingAirbillReport(InvoicePendingAirbillFilter filter) throws Exception;
}
