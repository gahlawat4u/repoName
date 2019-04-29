package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoFilter;
import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Posted from OverpaymentInfoDao
 * <p>
 * Author DatTV Date Apr 23, 2015 11:56:50 AM
 */
public class OverpaymentInfoDao extends BaseDao<OverpaymentInfoVo> {
    /**
     * Gets list of over payment by filter
     *
     * @param filter
     * @return
     * @throws DaoException
     */
    public List<OverpaymentInfoVo> selectByFilter(OverpaymentInfoFilter filter) throws DaoException {
        return selectList(filter, "OverpaymentInfo.selectByFilter");
    }

    /**
     * Count the number of over payments by filter
     *
     * @param filter
     * @return
     * @throws DaoException
     */
    public long countByFilter(OverpaymentInfoFilter filter) throws DaoException {
        return (long) selectObject(filter, "OverpaymentInfo.countByFilter");
    }

    /**
     * Gets sum of total over payment by filter
     *
     * @param filter
     * @return
     * @throws DaoException
     */
    public BigDecimal sumByFilter(OverpaymentInfoFilter filter) throws DaoException {
        return (BigDecimal) selectObject(filter, "OverpaymentInfo.sumByFilter");
    }
}