package com.gms.xms.persistence.service.invoice;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.customers.manage.ManageCustomerInvoiceFilter;
import com.gms.xms.filter.invoicing.DuplicateAirbillFilter;
import com.gms.xms.filter.invoicing.manageinvoice.ViewEditInvoiceFilter;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.persistence.dao.InvoiceTermDao;
import com.gms.xms.persistence.dao.invoice.DuplicateAirbillDao;
import com.gms.xms.persistence.dao.invoice.ManageCustomerInvoiceDao;
import com.gms.xms.persistence.service.customer.IManageCustomerService;
import com.gms.xms.persistence.service.customer.ManageCustomerServiceImp;
import com.gms.xms.txndb.vo.InvoiceFilter;
import com.gms.xms.txndb.vo.InvoiceTermVo;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerInvoiceDetailVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerInvoiceVo;
import com.gms.xms.txndb.vo.invoicing.DuplicateAirbillVo;
import com.gms.xms.txndb.vo.invoicing.csvinvoices.CsvInvoicesVo;
import com.gms.xms.txndb.vo.webship.invoices.AirbillChargeVo;
import com.gms.xms.txndb.vo.webship.invoices.AirbillDetailVo;
import com.gms.xms.txndb.vo.webship.invoices.GSTSummaryVo;
import com.gms.xms.txndb.vo.webship.invoices.TaxInvoiceVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from InvoiceService
 * <p>
 * Author DatTV Sep 22, 2015
 */
public class InvoiceServiceImp implements IInvoiceService {

    @Override
    public List<CsvInvoicesVo> getListCsvInvoicesByFranchiseCode(String franchiseCode) throws DaoException {
        InvoiceDao dao = new InvoiceDao();
        return dao.selectCsvInvoicesByFranchiseCode(franchiseCode);
    }

    @Override
    public List<InvoiceVo> getInvoiceDates(InvoiceFilter filter) throws DaoException {
        InvoiceDao dao = new InvoiceDao();
        return dao.selectInvoicesDateByFranchiseCodeListAndStatus(filter);
    }

    @Override
    public List<InvoiceVo> getInvoiceDatesToUnfreeze(InvoiceFilter filter) throws DaoException {
        InvoiceDao dao = new InvoiceDao();
        return dao.getInvoiceDatesToUnfreeze(filter);
    }

    @Override
    public List<InvoiceTermVo> selectInvoiceTerms() throws DaoException {
        InvoiceTermDao termDao = new InvoiceTermDao();
        return termDao.selectAll();
    }

    @Override
    public List<ManageCustomerInvoiceVo> getCustomerInvoices(ManageCustomerInvoiceFilter filter) throws DaoException {
        ManageCustomerInvoiceDao customerInvoiceDao = new ManageCustomerInvoiceDao();
        return customerInvoiceDao.selectByCusCode(filter);
    }

    @Override
    public long getCustomerInvoiceCount(ManageCustomerInvoiceFilter filter) throws DaoException {
        ManageCustomerInvoiceDao customerInvoiceDao = new ManageCustomerInvoiceDao();
        return customerInvoiceDao.countByCusCode(filter);
    }

    @Override
    public ManageCustomerInvoiceDetailVo getByInvoiceDetailCode(String invoiceCode) throws DaoException {
        ManageCustomerInvoiceDao customerInvoiceDao = new ManageCustomerInvoiceDao();
        return customerInvoiceDao.selectByInvoiceCode(invoiceCode);
    }

    @Override
    public List<DuplicateAirbillVo> getDuplicateAirbillByFilter(DuplicateAirbillFilter filter) throws DaoException {
        DuplicateAirbillDao dao = new DuplicateAirbillDao();
        return dao.getDuplicateAirbillByFilter(filter);
    }

    @Override
    public long countDuplicateAirbillByFilter(DuplicateAirbillFilter filter) throws DaoException {
        DuplicateAirbillDao dao = new DuplicateAirbillDao();
        return dao.countDuplicateAirbillByFilter(filter);
    }

    @Override
    public long getOutstandingInvoicesCountByCustCode(InvoiceFilter filter) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        return invoiceDao.getOutstandingInvoicesCountByCustCode(filter);
    }

    @Override
    public long getPaidInvoicesCountByCustCode(InvoiceFilter filter) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        return invoiceDao.getPaidInvoicesCountByCustCode(filter);
    }

    @Override
    public List<InvoiceVo> getOutstandingInvoicesByCustCode(InvoiceFilter filter) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        return invoiceDao.getOutstandingInvoicesByCustCode(filter);
    }

    @Override
    public List<InvoiceVo> getPaidInvoicesByCustCode(InvoiceFilter filter) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        return invoiceDao.getPaidInvoicesByCustCode(filter);
    }

    @Override
    public InvoiceVo getOutstandingInvoiceTotalByCustCode(InvoiceFilter filter) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        return invoiceDao.getOutstandingInvoiceTotalByCustCode(filter);
    }

    @Override
    public InvoiceVo getPaidInvoiceTotalByCustCode(InvoiceFilter filter) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        return invoiceDao.getPaidInvoiceTotalByCustCode(filter);
    }

    @Override
    public TaxInvoiceVo getTaxInvoiceByCode(String invoiceCode) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        return invoiceDao.getTaxInvoiceByCode(invoiceCode);
    }

    @Override
    public GSTSummaryVo getGSTSummaryByInvoiceId(Long invoiceId) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        return invoiceDao.getGSTSummaryByInvoiceId(invoiceId);
    }

    @Override
    public List<AirbillDetailVo> getAirbillListByInvoiceCode(String invoiceCode) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        List<AirbillDetailVo> result = invoiceDao.getAirbillListByInvoiceCode(invoiceCode);
        ShipmentVo shipmentVo;
        for (AirbillDetailVo airbillDetailVo : result) {
            shipmentVo = new ShipmentVo();
            shipmentVo.setAirbillNumber(airbillDetailVo.getAirbillNumber());
            shipmentVo.setShipmentId(airbillDetailVo.getShipmentId());
            List<AirbillChargeVo> charges = invoiceDao.selectChargeListByAWB(shipmentVo);
            airbillDetailVo.setCharges(charges);
        }
        return result;
    }

    @Override
    public List<InvoiceVo> selectAllInvoiceForViewEdit(ViewEditInvoiceFilter filter) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        return invoiceDao.selectAllInvoiceForViewEdit(filter);
    }

    @Override
    public List<InvoiceVo> selectInvoiceByFilter(ViewEditInvoiceFilter filter) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        return invoiceDao.selectInvoiceByFilter(filter);
    }

    @Override
    public InvoiceVo selectByCode(String invoiceCode) throws DaoException {
        InvoiceDao invoiceDao = new InvoiceDao();
        return invoiceDao.selectByCode(invoiceCode);
    }

    @Override
    public List<InvoiceVo> getInvoicesByInvDatesAndStatus(InvoiceFilter filter) throws DaoException {
        InvoiceDao dao = new InvoiceDao();
        return dao.selectInvoicesByInvDatesAndStatus(filter);
    }

    @Override
    public void updateInvoiceStatus(Map<String, String> context, InvoiceVo invoiceVo) throws Exception {
        InvoiceDao dao = new InvoiceDao();
        dao.updateInvStatusByInvCode(context, invoiceVo);
        // Update customer activation date.
        IManageCustomerService manageCustomerService = new ManageCustomerServiceImp();
        manageCustomerService.updateCustomerActivationDate(context, String.valueOf(invoiceVo.getCustomerCode()));
    }
}
