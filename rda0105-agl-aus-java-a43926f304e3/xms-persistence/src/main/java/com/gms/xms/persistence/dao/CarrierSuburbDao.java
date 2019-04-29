package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.txndb.vo.CarrierSuburbFilter;
import com.gms.xms.txndb.vo.CarrierSuburbVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CarrierSuburbDao
 * <p>
 * Author HungNT Date Apr 21, 2015
 */
public class CarrierSuburbDao extends BaseDao<CarrierSuburbVo> {
    /**
     * Get total record count of carrier suburb
     *
     * @param {@link CarrierSuburbFilter}
     * @return {@link Integer} total record count
     * @throws DaoException
     */
    public Integer selectCountrCarrierSuburbBySuburdNameAndPostCode(CarrierSuburbFilter carrierSuburbFilter) throws DaoException {
        return (int) selectObject(carrierSuburbFilter, "CarrierSuburb.selectCountrCarrierSuburbBySuburdNameAndPostCode");
    }

    public List<CarrierSuburbVo> getTntSuburbs(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "CarrierSuburb.getTntSuburbs");
    }

    public long countTntSuburbs(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(filter, "CarrierSuburb.countTntSuburbs");
    }

    public void insertCarrierSuburb(Map<String, String> context, CarrierSuburbVo carrierSuburbVo) throws DaoException {
        insert(context, carrierSuburbVo, "CarrierSuburb.insertCarrierSuburb");
    }
}
