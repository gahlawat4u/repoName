package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.InvoicePaymentVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from InvoicePaymentDao
 * <p>
 * Author DatTV Date Apr 17, 2015 9:19:51 PM
 */
public class InvoicePaymentDao extends BaseDao<InvoicePaymentVo> {

    public InvoicePaymentDao() {
        super();
    }

    public InvoicePaymentDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public int insert(Map<String, String> context, InvoicePaymentVo invoicePayment) throws DaoException {
        return insert(context, invoicePayment, "InvoicePayment.insert");
    }

    public int delete(Map<String, String> context, long invoicePaymentId) throws DaoException {
        return delete(context, invoicePaymentId, "InvoicePayment.deleteById");
    }

    public void update(Map<String, String> context, InvoicePaymentVo invoicePayment) throws DaoException {
        this.update(context, invoicePayment, "InvoicePayment.update");
    }

    public List<InvoicePaymentVo> selectInvoicePaymentByCustomerPaymentId(long cusPaymentId) throws DaoException {
        return selectList(cusPaymentId, "InvoicePayment.selectByCusPaymentId");
    }

    public List<InvoicePaymentVo> selectByInvoiceCode(String invoiceCode) throws DaoException {
        return this.selectList(invoiceCode, "InvoicePayment.selectByInvoiceCode");
    }

    public InvoicePaymentVo getInvoicePaymentById(long invoicePaymentId) throws DaoException {
        return this.select(invoicePaymentId, "InvoicePayment.getInvoicePaymentById");
    }

    public List<InvoicePaymentVo> selectPaymentByInvoiceId(Long invoiceId) throws DaoException {
        return this.selectList(invoiceId, "InvoicePayment.selectPaymentByInvoiceId");
    }
}