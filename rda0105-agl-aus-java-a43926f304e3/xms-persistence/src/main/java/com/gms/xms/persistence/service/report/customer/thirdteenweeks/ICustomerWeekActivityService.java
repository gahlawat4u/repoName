package com.gms.xms.persistence.service.report.customer.thirdteenweeks;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.thirdteenweeks.CustomerWeekActivityFilter;
import com.gms.xms.txndb.vo.reports.customer.thirdteenweeks.CustomerWeekActivityVo;

import java.util.List;

/**
 * Posted from ICustomerWeekActivityService.java
 * <p>
 * Author dattrinh 4:49:38 PM
 */
public interface ICustomerWeekActivityService {
    public List<CustomerWeekActivityVo> getWeekActivityReport(CustomerWeekActivityFilter filter) throws DaoException;

    public long getWeekActivityCount(CustomerWeekActivityFilter filter) throws DaoException;
}
