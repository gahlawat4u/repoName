package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.TntInternationalAuZoneVo;

import java.util.List;

/**
 * Posted from TntInternationalAuZoneDao
 * <p>
 * Author HungNT Date Jul 24, 2015
 */
public class TntInternationalAuZoneDao extends BaseDao<TntInternationalAuZoneVo> {
    public TntInternationalAuZoneVo getZoneByCountryName(String countryName) throws DaoException {
        return select(countryName, "TntInternationalAuZone.selectZoneByCountryName");
    }

    public List<TntInternationalAuZoneVo> getTntIntlZones(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "TntInternationalAuZone.getTntIntlZones");
    }

    public long countTntIntlZones(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(filter, "TntInternationalAuZone.countTntIntlZones");
    }
}
