package com.gms.xms.persistence.dao.admin.receivables.payments;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.receivables.payments.PaymentFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.receivables.payments.PaymentDetailVo;
import com.gms.xms.txndb.vo.admin.receivables.payments.PaymentTotalVo;

import java.util.List;

/**
 * Posted from PaymentDao
 * <p>
 * Author dattrinh Mar 17, 2016 3:11:21 PM
 */
public class PaymentDao extends BaseDao<Object> {
    public PaymentDao() {
        super();
    }

    public PaymentDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<PaymentDetailVo> getPaymentByFilter(PaymentFilter filter) throws DaoException {
        return this.selectList(filter, "Payment.getPaymentByFilter");
    }

    public long countPaymentByFilter(PaymentFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Payment.countPaymentByFilter");
    }

    public PaymentTotalVo sumPaymentByFilter(PaymentFilter filter) throws DaoException {
        return (PaymentTotalVo) this.select(filter, "Payment.sumPaymentByFilter");
    }

    public Double getPaymentForCredit(String invoiceCode) throws DaoException {
        return (Double) this.selectObject(invoiceCode, "Payment.getPaymentForCredit");
    }
}
