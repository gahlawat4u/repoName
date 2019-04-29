package com.gms.xms.persistence.service.tradetype;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.TradeTypeDao;
import com.gms.xms.txndb.vo.webship.TradeTypeVo;

import java.util.List;

/**
 * Posted from TradeTypeServiceImp
 * <p>
 * Author HungNT Date Jul 11, 2015
 */
public class TradeTypeServiceImp implements ITradeTypeService {
    TradeTypeDao dao = new TradeTypeDao();

    @Override
    public List<TradeTypeVo> getTradeTypeList() throws DaoException {
        List<TradeTypeVo> tradeTypeVos = dao.getTradeTypeList();
        return tradeTypeVos;
    }
}
