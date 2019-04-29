package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.txndb.vo.webship.TollIpecZoneFilter;
import com.gms.xms.txndb.vo.webship.TollIpecZoneVo;

import java.util.List;

/**
 * @author tkvcl
 */
public class TollIpecZoneDao extends BaseDao<TollIpecZoneVo> {

    public TollIpecZoneVo selectTollIpecZone(TollIpecZoneFilter filter) throws DaoException {
        return select(filter, "TollIpecZone.selectTollIpecZone");
    }

    public List<TollIpecZoneVo> getTollIpecZones(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "TollIpecZone.getTollIpecZones");
    }

    public long countTollIpecZones(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(filter, "TollIpecZone.countTollIpecZones");
    }
}
