package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.CurrencyVo;

import java.util.List;

/**
 * Posted from CurrencyDaoService
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class CurrencyDao extends BaseDao<CurrencyVo> {
    /**
     * Gets list of currencies
     *
     * @return List of currencies
     * @throws DaoException
     */
    public List<CurrencyVo> getCurrencyList() throws DaoException {
        return selectList(new CurrencyVo(), "Currency.getCurrencyList");
    }
}
