package com.gms.xms.persistence.service.tradetype;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.TradeTypeVo;

import java.util.List;

/**
 * Posted from ITradeTypeService
 * <p>
 * Author HungNT Date Jul 11, 2015
 */
public interface ITradeTypeService {
    public List<TradeTypeVo> getTradeTypeList() throws DaoException;
}
