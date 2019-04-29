package com.gms.xms.persistence.service.invoice;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.customers.manage.ManageCustomerInvoiceFilter;
import com.gms.xms.filter.invoicing.DuplicateAirbillFilter;
import com.gms.xms.filter.invoicing.manageinvoice.ViewEditInvoiceFilter;
import com.gms.xms.txndb.vo.InvoiceFilter;
import com.gms.xms.txndb.vo.InvoiceTermVo;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerInvoiceDetailVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerInvoiceVo;
import com.gms.xms.txndb.vo.invoicing.DuplicateAirbillVo;
import com.gms.xms.txndb.vo.invoicing.csvinvoices.CsvInvoicesVo;
import com.gms.xms.txndb.vo.webship.invoices.AirbillDetailVo;
import com.gms.xms.txndb.vo.webship.invoices.GSTSummaryVo;
import com.gms.xms.txndb.vo.webship.invoices.TaxInvoiceVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IInvoiceService
 * <p>
 * Author DatTV Sep 22, 2015
 */
public interface IInvoiceService {

    public List<CsvInvoicesVo> getListCsvInvoicesByFranchiseCode(String franchiseCode) throws DaoException;

    public List<InvoiceVo> getInvoiceDates(InvoiceFilter filter) throws DaoException;

    public List<InvoiceVo> getInvoiceDatesToUnfreeze(InvoiceFilter filter) throws DaoException;

    public List<InvoiceTermVo> selectInvoiceTerms() throws DaoException;

    public List<ManageCustomerInvoiceVo> getCustomerInvoices(ManageCustomerInvoiceFilter filter) throws DaoException;

    public long getCustomerInvoiceCount(ManageCustomerInvoiceFilter filter) throws DaoException;

    public ManageCustomerInvoiceDetailVo getByInvoiceDetailCode(String invoiceCode) throws DaoException;

    public List<DuplicateAirbillVo> getDuplicateAirbillByFilter(DuplicateAirbillFilter filter) throws DaoException;

    public long countDuplicateAirbillByFilter(DuplicateAirbillFilter filter) throws DaoException;

    public long getOutstandingInvoicesCountByCustCode(InvoiceFilter filter) throws DaoException;

    public long getPaidInvoicesCountByCustCode(InvoiceFilter filter) throws DaoException;

    public List<InvoiceVo> getOutstandingInvoicesByCustCode(InvoiceFilter filter) throws DaoException;

    public List<InvoiceVo> getPaidInvoicesByCustCode(InvoiceFilter filter) throws DaoException;

    public InvoiceVo getOutstandingInvoiceTotalByCustCode(InvoiceFilter filter) throws DaoException;

    public InvoiceVo getPaidInvoiceTotalByCustCode(InvoiceFilter filter) throws DaoException;

    public TaxInvoiceVo getTaxInvoiceByCode(String invoiceCode) throws DaoException;

    public GSTSummaryVo getGSTSummaryByInvoiceId(Long invoiceId) throws DaoException;

    public List<AirbillDetailVo> getAirbillListByInvoiceCode(String invoiceCode) throws DaoException;

    public List<InvoiceVo> selectAllInvoiceForViewEdit(ViewEditInvoiceFilter filter) throws DaoException;

    public List<InvoiceVo> selectInvoiceByFilter(ViewEditInvoiceFilter filter) throws DaoException;

    public InvoiceVo selectByCode(String invoiceCode) throws DaoException;

    public List<InvoiceVo> getInvoicesByInvDatesAndStatus(InvoiceFilter filter) throws DaoException;

    public void updateInvoiceStatus(Map<String, String> context, InvoiceVo invoiceVo) throws Exception;
}
