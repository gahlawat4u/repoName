package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.txndb.vo.MultiZoneFilter;
import com.gms.xms.txndb.vo.MultiZoneVo;

import java.util.List;

/**
 * Posted from MultiZoneDao
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class MultiZoneDao extends BaseDao<MultiZoneVo> {
    public MultiZoneVo selectDhlDomesticZone(MultiZoneFilter multiZoneFilter) throws DaoException {
        return select(multiZoneFilter, "MultiZone.selectDhlDomesticZone");
    }

    public List<MultiZoneVo> getDhlDomZones(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "MultiZone.getDhlDomZones");
    }

    public long countDhlDomZones(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(filter, "MultiZone.countDhlDomZones");
    }
}
