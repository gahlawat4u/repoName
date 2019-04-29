package com.gms.xms.persistence.service.report.customer.inactivity;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.inactivity.CustomerInactivityFilter;
import com.gms.xms.txndb.vo.reports.customer.inactivity.CustomerInactivityVo;

import java.util.List;

/**
 * Posted from ICustomerInactivityService.java
 * <p>
 * Author dattrinh 8:50:19 AM
 */
public interface ICustomerInactivityService {
    public List<CustomerInactivityVo> getInactivityReport(CustomerInactivityFilter filter) throws DaoException;

    public long getInactivityCount(CustomerInactivityFilter filter) throws DaoException;
}
