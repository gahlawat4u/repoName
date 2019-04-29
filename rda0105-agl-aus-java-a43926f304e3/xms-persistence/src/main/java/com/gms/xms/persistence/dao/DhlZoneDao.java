package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.dhl.DhlZoneFilter;
import com.gms.xms.txndb.vo.dhl.DhlZoneVo;

/**
 * Posted from DhlZoneDao
 * <p>
 * Author HungNT Date May 18, 2015
 */
public class DhlZoneDao extends BaseDao<DhlZoneVo> {

    public DhlZoneVo getDhlZoneByFilter(DhlZoneFilter dhlZoneFilter) throws DaoException {
        return select(dhlZoneFilter, "DhlZone.getDhlZoneByFilter");
    }
}
