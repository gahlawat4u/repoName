package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.manageinvoice.ViewEditInvoiceFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.invoicing.csvinvoices.CsvInvoicesVo;
import com.gms.xms.txndb.vo.webship.invoices.AirbillChargeVo;
import com.gms.xms.txndb.vo.webship.invoices.AirbillDetailVo;
import com.gms.xms.txndb.vo.webship.invoices.GSTSummaryVo;
import com.gms.xms.txndb.vo.webship.invoices.TaxInvoiceVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from InvoiceDaoService
 * <p>
 * Author DatTV Date Mar 30, 2015
 */
public class InvoiceDao extends BaseDao<Object> {
    public InvoiceDao() {
        super();
    }

    public InvoiceDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public Double getTotalDueValue(String customerCode) throws DaoException {
        return (Double) selectObject(customerCode, "Invoice.selectTotalDueAmount");
    }

    @SuppressWarnings("unchecked")
    public List<CsvInvoicesVo> selectCsvInvoicesByFranchiseCode(String franchiseCode) throws DaoException {
        return (List<CsvInvoicesVo>) (Object) selectObjectList(franchiseCode, "Invoice.selectCsvInvoicesList");
    }

    public List<InvoiceVo> selectInvoicesByInvoiceDate(InvoiceFilter filter) throws DaoException {
        return selectList(filter, "Invoice.selectInvoicesByInvoiceDate");
    }

    public List<InvoiceVo> selectInvoicesDateByFranchiseCodeListAndStatus(InvoiceFilter filter) throws DaoException {
        return selectList(filter, "Invoice.selectInvoicesDateByFranchiseCodeListAndStatus");
    }

    public List<InvoiceVo> getInvoiceDatesToUnfreeze(InvoiceFilter filter) throws DaoException {
        return selectList(filter, "Invoice.getInvoiceDatesToUnfreeze");
    }

    /**
     * Gets list of outstanding invoices by customer code
     *
     * @param filter
     * @return List<{@link InvoiceVo}>
     * @throws DaoException
     */
    public List<InvoiceVo> getOutstandingInvoicesByCustCode(InvoiceFilter filter) throws DaoException {
        return selectList(filter, "Invoice.getOutstandingInvoicesByCustCode");
    }

    /**
     * Gets the number of outstanding invoices by customer code
     *
     * @param filter
     * @return The number of outstanding invoices
     * @throws DaoException
     */
    public long getOutstandingInvoicesCountByCustCode(InvoiceFilter filter) throws DaoException {
        return (long) selectObject(filter, "Invoice.getOutstandingInvoicesCountByCustCode");
    }

    /**
     * Gets list of paid invoices by customer code
     *
     * @param filter
     * @return List<{@link InvoiceVo}>
     * @throws DaoException
     */
    public List<InvoiceVo> getPaidInvoicesByCustCode(InvoiceFilter filter) throws DaoException {
        return selectList(filter, "Invoice.getPaidInvoicesByCustCode");
    }

    /**
     * Gets the number of paid invoices by customer code
     *
     * @param filter
     * @return The number of paid invoices by customer code
     * @throws DaoException
     */
    public Long getPaidInvoicesCountByCustCode(InvoiceFilter filter) throws DaoException {
        return (Long) selectObject(filter, "Invoice.getPaidInvoicesCountByCustCode");
    }

    /**
     * Gets outstanding invoice total record
     *
     * @param filter
     * @return InvoiceVo
     * @throws DaoException
     */
    public InvoiceVo getOutstandingInvoiceTotalByCustCode(InvoiceFilter filter) throws DaoException {
        return (InvoiceVo) select(filter, "Invoice.getOutstandingInvoiceTotalByCustCode");
    }

    /**
     * Gets paid invoice total record
     *
     * @param filter
     * @return InvoiceVo
     * @throws DaoException
     */
    public InvoiceVo getPaidInvoiceTotalByCustCode(InvoiceFilter filter) throws DaoException {
        return (InvoiceVo) select(filter, "Invoice.getPaidInvoiceTotalByCustCode");
    }

    /**
     * Gets list of outstanding invoices by customer/invoice code
     *
     * @param filter
     * @return List<{@link InvoiceVo}>
     * @throws DaoException
     */
    public List<InvoiceVo> getOutstandingInvoicesByCustOrInvoiceCode(InvoiceFilter filter) throws DaoException {
        return selectList(filter, "Invoice.selectOutstandingInvoicesByCustOrInvoiceCode");
    }

    /**
     * Gets invoice by code
     *
     * @param invoiceCode
     * @return InvoiceVo
     * @throws DaoException
     */
    public InvoiceVo selectByCode(String invoiceCode) throws DaoException {
        return (InvoiceVo) select(invoiceCode, "Invoice.selectByCode");
    }

    public InvoiceVo selectById(Long invoiceId) throws DaoException {
        return (InvoiceVo) select(invoiceId, "Invoice.selectById");
    }

    /**
     * Gets invoice by airbill number
     *
     * @param airbillNumber
     * @return InvoiceVo
     * @throws DaoException
     */
    public InvoiceVo selectByAirbillNumber(ShipmentVo shipmentVo) throws DaoException {
        return (InvoiceVo) select(shipmentVo, "Invoice.selectByAirbillNumber");
    }

    public TaxInvoiceVo getTaxInvoiceByCode(String invoiceCode) throws DaoException {
        return (TaxInvoiceVo) selectObject(invoiceCode, "Invoice.getTaxInvoiceByCode");
    }

    public GSTSummaryVo getGSTSummaryByInvoiceId(Long invoiceId) throws DaoException {
        return (GSTSummaryVo) selectObject(invoiceId, "Invoice.getGSTSummaryByInvoiceId");
    }

    @SuppressWarnings("unchecked")
    public List<AirbillDetailVo> getAirbillListByInvoiceCode(String invoiceCode) throws DaoException {
        return (List<AirbillDetailVo>) (Object) selectObjectList(invoiceCode, "Invoice.getAirbillListByInvoiceCode");
    }

    @SuppressWarnings("unchecked")
    public List<AirbillChargeVo> selectChargeListByAWB(ShipmentVo shipmentVo) throws DaoException {
        return (List<AirbillChargeVo>) (Object) selectObjectList(shipmentVo, "Invoice.selectChargeListByAWB");
    }

    public List<InvoiceVo> selectAllInvoiceForViewEdit(ViewEditInvoiceFilter filter) throws DaoException {
        return selectList(filter, "Invoice.selectAllInvoiceForViewEdit");
    }

    public List<InvoiceVo> selectInvoiceByFilter(ViewEditInvoiceFilter filter) throws DaoException {
        return selectList(filter, "Invoice.selectInvoiceByFilter");
    }

    public List<InvoiceVo> selectInvoicesByInvDatesAndStatus(InvoiceFilter filter) throws DaoException {
        return selectList(filter, "Invoice.selectInvoicesByInvDatesAndStatus");
    }

    public Long checkFreezedInvoiceByDate(Date invoiceDate) throws DaoException {
        return (Long) selectObject(invoiceDate, "Invoice.checkFreezedInvoiceByDate");
    }

    public ReceiveInvoicelistVo selectReceiveInvoicelistByInvoiceid(Long invoiceId) throws DaoException {
        return (ReceiveInvoicelistVo) selectObject(invoiceId, "Invoice.selectReceiveInvoicelistByInvoiceid");
    }

    public List<ShipmentDetailInvoiceVo> selectShipmentDetailForInvoice(Long invoiceId) throws DaoException {
        return selectList(invoiceId, "Invoice.selectShipmentDetailForInvoice");
    }

    public void updateInvStatusByInvCode(Map<String, String> context, InvoiceVo invoiceVo) throws DaoException {
        update(context, invoiceVo, "Invoice.updateInvoiceStatusByInvCode");
    }

    public void insert(Map<String, String> context, InvoiceVo invoiceVo) throws DaoException {
        insert(context, invoiceVo, "Invoice.insert");
    }

    public void deleteById(Map<String, String> context, Long invoiceId) throws DaoException {
        this.delete(context, invoiceId, "Invoice.deleteById");
    }

    public void update(Map<String, String> context, InvoiceVo invoice) throws DaoException {
        this.update(context, invoice, "Invoice.update");
    }

    public Date selectMinInvoiceDateByCustomerCode(Long customerCode) throws DaoException {
        return (Date) selectObject(customerCode, "Invoice.selectMinInvoiceDateByCustomerCode");
    }

    public MarkUpByAirbillShipmentVo selectMarkUpByAirbillShipment(ShipmentInvoiceVo shipmentInvoiceVo) throws DaoException {
        return (MarkUpByAirbillShipmentVo) selectObject(shipmentInvoiceVo, "Invoice.selectMarkUpByAirbillShipment");
    }

    public InvoiceVo getByCode(String invoiceCode) throws DaoException {
        return (InvoiceVo) select(invoiceCode, "Invoice.getByCode");
    }

    public Long countShipmentInvoiceByInvoiceId(Long invoiceId) throws DaoException {
        return (Long) selectObject(invoiceId, "Invoice.countShipmentInvoiceByInvoiceId");
    }

    public Date getMinInvoiceDateByCustCode(String customerCode) throws DaoException {
        return (Date) selectObject(customerCode, "Invoice.getMinInvoiceDateByCustCode");
    }

    public InvoiceVo getInvoiceByAirbill(AirbillAdjustmentVo adjustmentVo) throws DaoException {
        return (InvoiceVo) select(adjustmentVo, "Invoice.getInvoiceByAirbill");
    }
}