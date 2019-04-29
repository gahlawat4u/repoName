package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.ServiceMarkupRateFilter;
import com.gms.xms.persistence.dao.BaseDao;

/**
 * Posted from Apr 9, 2016 10:21:24 AM
 * <p>
 * Author dattrinh
 */
public class ServiceMarkupRateDao extends BaseDao<Object> {
    public Double getServiceMarkupRateByFranchiseCode(ServiceMarkupRateFilter filter) throws DaoException {
        return (Double) this.selectObject(filter, "ServiceMarkupRate.getServiceMarkupRateByFranchiseCode");
    }

    public Double getServiceMarkupRateByCustomerCode(ServiceMarkupRateFilter filter) throws DaoException {
        return (Double) this.selectObject(filter, "ServiceMarkupRate.getServiceMarkupRateByCustomerCode");
    }

    public Double getServiceMarkupRateByProfileId(ServiceMarkupRateFilter filter) throws DaoException {
        return (Double) this.selectObject(filter, "ServiceMarkupRate.getServiceMarkupRateByProfileId");
    }
}
