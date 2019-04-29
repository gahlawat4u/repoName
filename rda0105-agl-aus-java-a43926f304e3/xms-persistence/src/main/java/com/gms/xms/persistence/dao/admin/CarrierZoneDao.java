package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.CarrierZoneVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from Apr 11, 2016 5:04:25 PM
 * <p>
 * Author dattrinh
 */
public class CarrierZoneDao extends BaseDao<Object> {
    public CarrierZoneDao() {
        super();
    }

    public CarrierZoneDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<CarrierZoneVo> getZoneByCarrier(Long carrier) throws DaoException {
        return this.selectList(carrier, "CarrierZone.getZoneByCarrier");
    }

    public CarrierZoneVo getZoneByCarrierAndCode(CarrierZoneVo filter) throws DaoException {
        return (CarrierZoneVo) this.select(filter, "CarrierZone.getZoneByCarrierAndCode");
    }

    public CarrierZoneVo getZoneByCarrierAndZoneNo(CarrierZoneVo filter) throws DaoException {
        return (CarrierZoneVo) this.select(filter, "CarrierZone.getZoneByCarrierAndZoneNo");
    }

    public Long checkCarrierZoneStartrack(CarrierZoneVo carrierZoneVo) throws DaoException {
        return (Long) this.select(carrierZoneVo, "CarrierZone.checkCarrierZoneStartrack");
    }

    public List<CarrierZoneVo> getTntZones(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "CarrierZone.getTntZones");
    }

    public long countTntZones(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(null, "CarrierZone.countTntZones");
    }

    public void saveCarrierZone(Map<String, String> context, CarrierZoneVo carrierZoneVo) throws DaoException {
        this.insert(context, carrierZoneVo, "CarrierZone.saveCarrierZone");
    }
}
