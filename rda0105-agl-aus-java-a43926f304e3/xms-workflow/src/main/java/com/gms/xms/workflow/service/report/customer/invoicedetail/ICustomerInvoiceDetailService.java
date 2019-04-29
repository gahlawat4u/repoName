package com.gms.xms.workflow.service.report.customer.invoicedetail;

import com.gms.xms.filter.reports.customer.invoicedetail.CustomerCreditNoteDetailFilter;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerInvoiceDetailFilter;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerCreditNoteDetailVo;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerInvoiceDetailVo;

import java.util.List;

/**
 * Posted from ICustomerInvoiceDetailService.java
 * <p>
 * Author dattrinh 11:57:02 AM
 */
public interface ICustomerInvoiceDetailService {
    public List<CustomerInvoiceDetailVo> getInvoiceDetailReport(CustomerInvoiceDetailFilter filter) throws Exception;

    public CustomerInvoiceDetailVo sumInvoiceDetailReport(CustomerInvoiceDetailFilter filter) throws Exception;

    public long getInvoiceDetailCount(CustomerInvoiceDetailFilter filter) throws Exception;

    public List<CustomerCreditNoteDetailVo> getCreditNoteDetailReport(CustomerCreditNoteDetailFilter filter) throws Exception;

    public CustomerCreditNoteDetailVo sumCreditNoteDetailReport(CustomerCreditNoteDetailFilter filter) throws Exception;

    public long getCreditNoteDetailCount(CustomerCreditNoteDetailFilter filter) throws Exception;
}
