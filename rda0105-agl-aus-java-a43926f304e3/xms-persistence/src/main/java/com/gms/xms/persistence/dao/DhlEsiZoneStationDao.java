package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.dhl.DhlEsiZoneStationFilter;
import com.gms.xms.txndb.vo.dhl.DhlEsiZoneStationVo;

/**
 * Posted from DhlEsiZoneStationDao
 * <p>
 * Author HungNT Date May 20, 2015
 */
public class DhlEsiZoneStationDao extends BaseDao<DhlEsiZoneStationVo> {

    /**
     * Get DHL ESI zone
     *
     * @param dhlEsiZoneStationVo
     * @return {@link DhlEsiZoneStationVo}
     * @throws DaoException
     */
    public DhlEsiZoneStationVo selectEsiZone(DhlEsiZoneStationFilter dhlEsiZoneStationFilter) throws DaoException {
        return select(dhlEsiZoneStationFilter, "DhlEsiZoneStation.selectEsiZone");
    }
}
