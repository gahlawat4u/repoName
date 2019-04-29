package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.TntRouteViewFilter;
import com.gms.xms.txndb.vo.TntRouteVo;

import java.util.List;

/**
 * Posted from TradeTypeDaoService
 * <p>
 * Author DatTV Date Mar 27, 2015
 */
public class TntRouteDao extends BaseDao<TntRouteVo> {

    /**
     * Gets list of trade types
     *
     * @return List<{@link TradeTypeVo}>
     * @throws DaoException
     */
    public List<TntRouteVo> selectByFilterView(TntRouteViewFilter filter) throws DaoException {
        return selectList(filter, "TntRoute.selectByFilterView");
    }
}