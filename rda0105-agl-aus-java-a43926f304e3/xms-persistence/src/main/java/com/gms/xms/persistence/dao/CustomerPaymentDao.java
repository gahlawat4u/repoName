package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.CustomerPaymentVo;

import java.util.Map;

/**
 * Posted from CustomerPaymentDao
 * <p>
 * Author DatTV Date Apr 8, 2015 5:27:25 PM
 */
public class CustomerPaymentDao extends BaseDao<CustomerPaymentVo> {

    public CustomerPaymentDao() {
        super();
    }

    public CustomerPaymentDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public CustomerPaymentVo selectCustomerPayment(Long cusPaymentId) throws DaoException {
        return select(cusPaymentId, "CustomerPayment.selectCustomerPayment");
    }

    /**
     * Inserts a new customer payment record
     *
     * @param payment
     * @return number of rows affected
     * @throws DaoException
     */
    public int insert(Map<String, String> context, CustomerPaymentVo payment) throws DaoException {
        return insert(context, payment, "CustomerPayment.insert");
    }

    /**
     * Deletes a customer payment by id
     *
     * @param cusPaymentId
     * @return number of rows deleted
     * @throws DaoException
     */
    public int delete(Map<String, String> context, long cusPaymentId) throws DaoException {
        return delete(context, cusPaymentId, "CustomerPayment.deleteById");
    }
}