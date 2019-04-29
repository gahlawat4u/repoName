package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.OverpaymentVo;
import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoFilter;

import java.util.List;
import java.util.Map;

/**
 * Posted from OverpaymentDao
 * <p>
 * Author DatTV Date Apr 10, 2015 3:37:45 PM
 */
public class OverpaymentDao extends BaseDao<OverpaymentVo> {
    public OverpaymentDao() {
        super();
    }

    public OverpaymentDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public OverpaymentVo selectByCustomerPaymentId(Long cusPaymentId) throws DaoException {
        return select(cusPaymentId, "Overpayment.selectByCustomerPaymentId");
    }

    /**
     * Gets list of over payment by customer code
     *
     * @param customerCode
     * @return List<{@link OverpaymentVo}>
     * @throws DaoException
     */
    public List<OverpaymentVo> selectByCustomerCode(long customerCode) throws DaoException {
        return selectList(customerCode, "Overpayment.selectByCustomerCode");
    }

    /**
     * Gets list of over payment by customer code and source
     *
     * @param filter
     * @return
     * @throws DaoException
     */
    public List<OverpaymentVo> selectByCustCodeAndSource(OverpaymentInfoFilter filter) throws DaoException {
        return selectList(filter, "Overpayment.selectByCustCodeAndSource");
    }

    public OverpaymentVo selectOverPayByCustomerPaymentId(Long cusPaymentId) throws DaoException {
        return select(cusPaymentId, "Overpayment.selectOverPayByCustomerPaymentId");
    }

    /**
     * Inserts new over payment
     *
     * @param overPayment
     * @throws DaoException
     */
    public void insert(Map<String, String> context, OverpaymentVo overPayment) throws DaoException {
        insert(context, overPayment, "Overpayment.insert");
    }

    /**
     * Deletes an over payment
     *
     * @param cusPaymentId
     * @throws DaoException
     */
    public void delete(Map<String, String> context, long cusPaymentId) throws DaoException {
        delete(context, cusPaymentId, "Overpayment.delete");
    }

    /**
     * Updates an over payment
     *
     * @param overpayment
     * @throws DaoException
     */
    public void update(Map<String, String> context, OverpaymentVo overpayment) throws DaoException {
        update(context, overpayment, "Overpayment.update");
    }

    /**
     * select CreditType by customerpaymentId
     *
     * @param cusPaymentid
     * @return null: customer payment,0: credit note, 1: carrier credit note
     * @throws DaoException
     */
    public Long selectCreditTypeByCustomerPaymentId(Long cusPaymentid) throws DaoException {
        return (Long) selectObject(cusPaymentid, "Overpayment.selectCreditTypeByCustomerPaymentId");
    }

}