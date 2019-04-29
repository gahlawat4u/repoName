package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.txndb.vo.CarrierPostCodeFilter;
import com.gms.xms.txndb.vo.CarrierPostCodeVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CarrierPostCodeDao
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class CarrierPostCodeDao extends BaseDao<CarrierPostCodeVo> {
    /**
     * Get carrier cost post code by post code and carrier id
     *
     * @param {@link CarrierPostCodeFilter} carrierPostCodeFilter
     * @return {@link CarrierPostCodeVo} carrier post code
     * @throws DaoException
     */
    public CarrierPostCodeVo selectCarrierPostCodeByPostCodeAndCarrier(CarrierPostCodeFilter carrierPostCodeFilter) throws DaoException {
        return select(carrierPostCodeFilter, "CarrierPostCode.selectCarrierPostCodeByPostCodeAndCarrier");
    }

    public List<CarrierPostCodeVo> getTntPostCodes(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "CarrierPostCode.getTntPostCodes");
    }

    public long countTntPostCodes(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(filter, "CarrierPostCode.countTntPostCodes");
    }

    public void insertCarrierPostCode(Map<String, String> context, CarrierPostCodeVo carrierPostCodeVo) throws DaoException {
        insert(context, carrierPostCodeVo, "CarrierPostCode.insertCarrierPostCode");
    }
}
