package com.gms.xms.persistence.dao.admin.payables.salesrep;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepSettingFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepSettingVo;

import java.util.List;

/**
 * Posted from SalesRepSettingDao
 * <p>
 * Author dattrinh Mar 21, 2016 12:04:13 PM
 */
public class SalesRepSettingDao extends BaseDao<Object> {
    public List<SalesRepSettingVo> getSalesRepSettingsByFilter(SalesRepSettingFilter filter) throws DaoException {
        return this.selectList(filter, "SalesRepSettings.getSalesRepSettingsByFilter");
    }

    public long countSalesRepSettingsByFilter(SalesRepSettingFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "SalesRepSettings.countSalesRepSettingsByFilter");
    }

    public SalesRepSettingVo getSalesRepSettingsBySaleRepId(Long saleRepId) throws DaoException {
        return (SalesRepSettingVo) this.select(saleRepId, "SalesRepSettings.getSalesRepSettingsBySaleRepId");
    }

    public SalesRepSettingVo getNewSalesRepSettingByUserId(Long userId) throws DaoException {
        return (SalesRepSettingVo) this.select(userId, "SalesRepSettings.getNewSalesRepSettingByUserId");
    }
}