package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.TradeTypeVo;

import java.util.List;

/**
 * Posted from TradeTypeDaoService
 * <p>
 * Author DatTV Date Mar 27, 2015
 */
public class TradeTypeDao extends BaseDao<TradeTypeVo> {

    /**
     * Gets list of trade types
     *
     * @return List<{@link TradeTypeVo}>
     * @throws DaoException
     */
    public List<TradeTypeVo> getTradeTypeList() throws DaoException {
        return selectList(new TradeTypeVo(), "TradeType.getTradeTypeList");
    }
}