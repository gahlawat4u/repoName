package com.gms.xms.persistence.service.report.webship;

import com.gms.xms.filter.reports.webship.InvoicePendingAirbillFilter;
import com.gms.xms.persistence.dao.report.webship.InvoicePendingAirbillDao;
import com.gms.xms.txndb.vo.reports.webship.InvoicePendingAirbillVo;

import java.util.List;

/**
 * Posted from InvoicePendingAirbillServiceImp.java
 * <p>
 * Author dattrinh 11:31:35 AM
 */
public class InvoicePendingAirbillServiceImp implements IInvoicePendingAirbillService {
    private InvoicePendingAirbillDao dao = new InvoicePendingAirbillDao();

    @Override
    public List<InvoicePendingAirbillVo> getInvoicePendingAirbillReport(InvoicePendingAirbillFilter filter) throws Exception {
        return dao.getInvoicePendingAirbillReport(filter);
    }

    @Override
    public long countInvoicePendingAirbillReport(InvoicePendingAirbillFilter filter) throws Exception {
        return dao.countInvoicePendingAirbillReport(filter);
    }
}
