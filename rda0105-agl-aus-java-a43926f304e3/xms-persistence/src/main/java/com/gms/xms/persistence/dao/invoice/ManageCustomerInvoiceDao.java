package com.gms.xms.persistence.dao.invoice;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.customers.manage.ManageCustomerInvoiceFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerInvoiceDetailVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerInvoiceVo;

import java.util.List;

/**
 * Posted from ManageCustomerInvoiceDao
 * <p>
 * Author DatTV Sep 24, 2015
 */
public class ManageCustomerInvoiceDao extends BaseDao<ManageCustomerInvoiceVo> {
    public List<ManageCustomerInvoiceVo> selectByCusCode(ManageCustomerInvoiceFilter filter) throws DaoException {
        return this.selectList(filter, "ManageCustomerInvoice.selectByCusCode");
    }

    public long countByCusCode(ManageCustomerInvoiceFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "ManageCustomerInvoice.countByCusCode");
    }

    public ManageCustomerInvoiceDetailVo selectByInvoiceCode(String invoiceCode) throws DaoException {
        return (ManageCustomerInvoiceDetailVo) this.selectObject(invoiceCode, "ManageCustomerInvoice.selectByInvoiceCode");
    }
}
