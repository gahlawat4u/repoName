package com.gms.xms.persistence.service.currency;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CurrencyDao;
import com.gms.xms.txndb.vo.CurrencyVo;

import java.util.List;

/**
 * Posted from CurrencyServiceImp
 * <p>
 * Author HungNT Date Jul 21, 2015
 */
public class CurrencyServiceImp implements ICurrencyService {
    private CurrencyDao dao = new CurrencyDao();

    @Override
    public List<CurrencyVo> getCurrencyList() throws DaoException {
        return dao.getCurrencyList();
    }
}
