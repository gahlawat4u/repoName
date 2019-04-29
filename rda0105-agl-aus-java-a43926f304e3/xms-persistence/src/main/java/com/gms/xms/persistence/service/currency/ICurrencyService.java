package com.gms.xms.persistence.service.currency;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.CurrencyVo;

import java.util.List;

/**
 * Posted from ICurrencyService
 * <p>
 * Author HungNT Date Jul 21, 2015
 */
public interface ICurrencyService {
    public List<CurrencyVo> getCurrencyList() throws DaoException;
}
