package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.ups.UpsZoneFilter;
import com.gms.xms.txndb.vo.ups.UpsZoneVo;

/**
 * Created by thinhdd
 * Date 3/29/2017.
 */
public class UpsZoneDao extends BaseDao<UpsZoneVo> {
    public UpsZoneVo getZoneByFilter(UpsZoneFilter upsZoneFilter) throws DaoException {
        return select(upsZoneFilter, "UpsZone.getUpsZoneByFilter");
    }
}
