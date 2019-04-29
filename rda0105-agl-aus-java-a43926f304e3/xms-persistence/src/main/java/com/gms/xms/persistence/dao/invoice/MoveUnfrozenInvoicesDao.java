package com.gms.xms.persistence.dao.invoice;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.MoveUnfrozenInvoicesFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.parameter.InvoiceIdParam;
import com.gms.xms.txndb.vo.InvoiceVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from MoveUnfrozenInvoicesDao
 * <p>
 * Author dattrinh Mar 10, 2016 4:56:55 PM
 */
public class MoveUnfrozenInvoicesDao extends BaseDao<Object> {
    public MoveUnfrozenInvoicesDao() {
        super();
    }

    public MoveUnfrozenInvoicesDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<Date> getUnfrozenInvoiceDates(String franchiseList) throws DaoException {
        return this.selectList(franchiseList, "MoveUnfrozenInvoices.getUnfrozenInvoiceDates");
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceVo> getMoveInvoices(MoveUnfrozenInvoicesFilter filter) throws DaoException {
        return (List<InvoiceVo>) (Object) this.selectList(filter, "MoveUnfrozenInvoices.getMoveInvoices");
    }

    public void updateInvoiceId4ShipmentInvoice(Map<String, String> context, InvoiceIdParam invoiceId) throws DaoException {
        this.update(context, invoiceId, "MoveUnfrozenInvoices.updateInvoiceId4ShipmentInvoice");
    }

    public Integer checkFrozenInvoiceByInvoiceCode(String invoiceCode) throws DaoException {
        return (Integer) this.selectObject(invoiceCode, "MoveUnfrozenInvoices.checkFrozenInvoiceByInvoiceCode");
    }
}