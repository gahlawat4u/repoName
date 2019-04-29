package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.SystemStatsListFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.txndb.vo.DhlCountryVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from DhlCountryDao
 * <p>
 * Author DatTV Sep 30, 2015
 */
public class DhlCountryDao extends BaseDao<DhlCountryVo> {
    public DhlCountryDao() {
        super();
    }

    public List<DhlCountryVo> selectAll(SystemStatsListFilter filter) throws DaoException {
        return selectList(filter, "DhlCountry.selectAll");
    }

    public Long selectCountAll(SystemStatsListFilter filter) throws DaoException {
        return (long) selectObject(filter, "DhlCountry.selectCountAll");
    }

    public DhlCountryVo selectDhlCountryByCountryName(String dhlCountryName) throws DaoException {
        return select(dhlCountryName, "DhlCountry.selectDhlCountryByCountryName");
    }

    public void insert(Map<String, String> context, DhlCountryVo dhlCountryVo) throws DaoException {
        this.insert(context, dhlCountryVo, "DhlCountry.insert");
    }

    public void update(Map<String, String> context, DhlCountryVo dhlCountryVo) throws DaoException {
        this.update(context, dhlCountryVo, "DhlCountry.update");
    }

    public DhlCountryVo selectDhlCountryByDhlCountryId(Long dhlCountryId) throws DaoException {
        return this.select(dhlCountryId, "DhlCountry.selectDhlCountryByDhlCountryId");
    }

    public DhlCountryVo selectDhlCountryByCountryCode(String countryCode) throws DaoException {
        return this.select(countryCode, "DhlCountry.selectDhlCountryByCountryCode");
    }

    public DhlCountryVo selectDhlCountryByApCode(String dhlApCode) throws DaoException {
        return this.select(dhlApCode, "DhlCountry.selectDhlCountryByApCode");
    }

    public DhlCountryVo selectDhlCountryByName(String dhlCountryName) throws DaoException {
        return this.select(dhlCountryName, "DhlCountry.selectDhlCountryByName");
    }

    public List<DhlCountryVo> getDhlCountryZones(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "DhlCountry.getDhlCountryZones");
    }

    public long countDhlCountryZones(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(filter, "DhlCountry.countDhlCountryZones");
    }
}