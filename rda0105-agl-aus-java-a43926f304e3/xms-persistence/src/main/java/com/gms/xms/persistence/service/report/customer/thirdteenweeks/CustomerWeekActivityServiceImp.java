package com.gms.xms.persistence.service.report.customer.thirdteenweeks;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.thirdteenweeks.CustomerWeekActivityFilter;
import com.gms.xms.persistence.dao.report.CustomerWeekActivityDao;
import com.gms.xms.txndb.vo.reports.customer.thirdteenweeks.CustomerWeekActivityVo;

import java.util.List;

/**
 * Posted from CustomerWeekActivityServiceImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class CustomerWeekActivityServiceImp implements ICustomerWeekActivityService {
    @Override
    public List<CustomerWeekActivityVo> getWeekActivityReport(CustomerWeekActivityFilter filter) throws DaoException {
        CustomerWeekActivityDao activityDao = new CustomerWeekActivityDao();
        return activityDao.getWeekActivityReport(filter);
    }

    @Override
    public long getWeekActivityCount(CustomerWeekActivityFilter filter) throws DaoException {
        CustomerWeekActivityDao activityDao = new CustomerWeekActivityDao();
        return activityDao.getWeekActivityCount(filter);
    }
}
