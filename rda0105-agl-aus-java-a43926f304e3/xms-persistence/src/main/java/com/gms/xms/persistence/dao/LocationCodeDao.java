package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.LocationCodeVo;

import java.util.List;

/**
 * Posted from LocationCodeDao
 * <p>
 * Author HungNT Date Apr 25, 2015
 */
public class LocationCodeDao extends BaseDao<LocationCodeVo> {
    public List<LocationCodeVo> selectAllLocationCode() throws DaoException {
        return selectList(new LocationCodeVo(), "LocationCode.selectAllLocationCode");
    }

    public LocationCodeVo selectLocationCodeById(Integer locationCodeId) throws DaoException {
        return select(locationCodeId, "LocationCode.selectLocationCodeById");
    }
}
